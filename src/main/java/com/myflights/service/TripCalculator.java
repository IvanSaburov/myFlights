package com.myflights.service;


import com.myflights.entity.City;
import com.myflights.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

public class TripCalculator {
    @Autowired
    ApiClient apiClient;

    public String calcTrip(Trip trip) {

        int segmentsQuantity = trip.getCities().size();
        int totalNightsQuantity = 0;
        Date startDt = trip.getStartDate();
        Date endDt = trip.getEndDate();
        Calendar calendar = Calendar.getInstance();
        HashMap<String, List<HashMap<String, Object>>> result = new HashMap<>();
        HashMap<String, City> cityCodeMap = new HashMap<>();
        for (int i = 0; i < segmentsQuantity; i++) {
            City segment = trip.getCities().get(i);
            cityCodeMap.put(segment.getCityCode(), segment);
            totalNightsQuantity += segment.getNightsQuantity();
            for (int j = 0; j < segmentsQuantity; j++) {
                if (j == i) {
                    continue;
                }
                City destination = trip.getCities().get(j);
                List<HashMap<String, Object>> t = result.get(segment.getCityName());
                //добавить сравнения месяцев начала и конца периода
                if (t != null) {
                    t.addAll(apiClient.getMonthPrices(segment.getCityCode(),
                            destination.getCityCode(), (new SimpleDateFormat("yyyy-MM-dd")).format(startDt)));
                    t.addAll(apiClient.getMonthPrices(segment.getCityCode(),
                            destination.getCityCode(), (new SimpleDateFormat("yyyy-MM-dd")).format(endDt)));
                } else {
                    t = apiClient.getMonthPrices(segment.getCityCode(),
                            destination.getCityCode(), (new SimpleDateFormat("yyyy-MM-dd")).format(startDt));
                    t.addAll(apiClient.getMonthPrices(segment.getCityCode(),
                            destination.getCityCode(), (new SimpleDateFormat("yyyy-MM-dd")).format(endDt)));
                }
                result.put(segment.getCityName(), t);

            }
        }
        if (!isPeriodValid(calendar, totalNightsQuantity, endDt)) {
            System.out.println("Общее количество ночей больше выбранного периода");
            return null;
        }

        calendar.setTime(startDt);
        System.out.println("=============================================================");
        HashMap<String, Object> test = new HashMap<>();
        List<String> validateList = new ArrayList<>();

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(endDt);
        calendar2.add(Calendar.DAY_OF_YEAR, -totalNightsQuantity);
        calendar1.setTime(startDt);
        Double minPrice;
        String wr ="";
        StringBuilder sbRes = new StringBuilder();
        while (calendar1.getTime().getTime() <= calendar2.getTime().getTime()) {
            System.out.println((new SimpleDateFormat("dd.MM.yyyy")).format(calendar1.getTime()));

            minPrice =  Double.MAX_VALUE;
            for (City seg : trip.getCities()) {
                StringBuilder sb = new StringBuilder("Итого: ").append("<br \\/>");
                Double price = 0.0;
                int nights = totalNightsQuantity;
                int count = 0;
                validateList.clear();
                List<HashMap<String, Object>> flights = result.get(seg.getCityName());

                calendar.setTime(calendar1.getTime());
                calendar.add(Calendar.DAY_OF_YEAR, seg.getNightsQuantity());

                validateList.add(seg.getCityCode());
                test = getMinPrice(flights, calendar, cityCodeMap, validateList);
                nights -= seg.getNightsQuantity();

                sb.append("Из " + seg.getCityName() + "в " + test).append("<br \\/>");
                count++;

                String cit = (String) test.get("city");
                validateList.add((String) test.get("code"));
                price += (Double) test.get("price");
                if (!isPeriodValid(calendar, nights, endDt)) {
                    break;
                }
                calendar.add(Calendar.DAY_OF_YEAR, cityCodeMap.get((String) test.get("code")).getNightsQuantity());
                nights -= cityCodeMap.get((String) test.get("code")).getNightsQuantity();
                while (count < segmentsQuantity - 1) {
                    if (!isPeriodValid(calendar, nights, endDt)) {
                        break;
                    }
                    HashMap<String, Object> iter = getMinPrice(result.get(cit), calendar, cityCodeMap, validateList);
                    if(iter.get("city")==null){
                        break;
                    }
                    sb.append("Из " + cit + "в " + iter).append("<br \\/>");

                    nights -= cityCodeMap.get(iter.get("code")).getNightsQuantity();
                    cit = (String) iter.get("city");
                    validateList.add((String) iter.get("code"));
                    price += (Double) iter.get("price");
                    calendar.add(Calendar.DAY_OF_YEAR, cityCodeMap.get(iter.get("code")).getNightsQuantity());
                    count++;
                }
                if(price<minPrice) {
                    minPrice = price;
                    sb.append("Общая сумма: " + price).append("<br \\/>=============================================================<br \\/>");
                    wr = sb.toString();
                    sbRes.append(wr).append("<br \\/>");
                }
            }

            calendar1.add(Calendar.DAY_OF_YEAR, 1);
            System.out.println(wr.toString());
        }
        return sbRes.toString();
    }

    private HashMap<String, Object> getMinPrice(List<HashMap<String, Object>> flights, Calendar calendar, HashMap<String, City> cityCodeMap,
                                                List<String> validateList) {
        Double min = Double.MAX_VALUE;
        HashMap<String, Object> result = new HashMap<>();
        String city = null;
        String code = null;
        String date = null;
        String calen = (new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime());
        for (HashMap<String, Object> map : flights) {
            if (map.get("depart_date").equals(calen)) {
                if (validateList != null && validateList.contains(map.get("destination"))) {
                    continue;
                }
                double price;
                if (map.get("value") instanceof Integer) {
                    price = ((Integer) map.get("value")).doubleValue();
                } else {
                    price = (Double) map.get("value");
                }
                if (price < min) {
                    min = price;
                    city = cityCodeMap.get( map.get("destination")).getCityName();
                    code = (String) map.get("destination");
                    date = (String) map.get("depart_date");
                }
            }
        }
        result.put("price", min);
        result.put("city", city);
        result.put("code", code);
        result.put("date", date);

        return result;
    }

    private boolean isPeriodValid(Calendar calendar, int totalNightsQuantity, Date endDt) {
        calendar.add(Calendar.DAY_OF_YEAR, totalNightsQuantity);
        if (calendar.getTime().getTime() <= endDt.getTime()) {
            calendar.add(Calendar.DAY_OF_YEAR, -totalNightsQuantity);
            return true;
        }
        calendar.add(Calendar.DAY_OF_YEAR, -totalNightsQuantity);
        return false;
    }

}
