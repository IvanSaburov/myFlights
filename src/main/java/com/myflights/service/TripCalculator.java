package com.myflights.service;


import com.myflights.entity.Segment;
import com.myflights.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TripCalculator {
    @Autowired
    ApiClient apiClient;

    public String calcTrip(Trip trip) {

        int segmentsQuantity = trip.getSegments().size();
        int totalNightsQuantity = 0;
        Date startDt = trip.getStartDate();
        Date endDt = trip.getEndDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDt);
        HashMap<String, List<HashMap<String, Object>>> result = new HashMap<>();
        HashMap<String, String> cityCodeMap = new HashMap<>();
        for (int i = 0; i < segmentsQuantity; i++) {
            Segment segment = trip.getSegments().get(i);
            cityCodeMap.put(segment.getCityCode(), segment.getCityName());
            totalNightsQuantity = +segment.getNightsQuantity();
            for (int j = 0; j < segmentsQuantity; j++) {
                if (j == i) {
                    continue;
                }
                Segment destination = trip.getSegments().get(j);
                List<HashMap<String, Object>> t = result.get(segment.getCityName());
                if(t!=null){
                    t.addAll(apiClient.getMonthPrices(segment.getCityCode(),
                            destination.getCityCode(), (new SimpleDateFormat("yyyy-MM-dd")).format(startDt)));
                }else{
                    t = apiClient.getMonthPrices(segment.getCityCode(),
                            destination.getCityCode(), (new SimpleDateFormat("yyyy-MM-dd")).format(startDt));
                }
                result.put(segment.getCityName(), t);

            }
        }
        StringBuilder sb = new StringBuilder("Итого: ");
        HashMap<String, Object> test = new HashMap<>();
        List<String> validateList = new ArrayList<>();
        int count = 0;
            for (Segment seg : trip.getSegments()) {
                List<HashMap<String, Object>> flights = result.get(seg.getCityName());
                calendar.add(Calendar.DAY_OF_YEAR, seg.getNightsQuantity());
//                while(calendar.getTime().before(endDt)){

//                calendar.add(Calendar.DAY_OF_YEAR, -totalNightsQuantity);
                validateList.add(seg.getCityCode());
               test = getMinPrice(flights, calendar, cityCodeMap, validateList);

                sb.append("Из "+seg.getCityName() + "в "+test).append("\n");
                count++;
                break;
            }
        String cit = (String) test.get("city");
        validateList.add((String) test.get("code"));
        while (count<segmentsQuantity-1){
            HashMap<String, Object> iter = getMinPrice(result.get(cit), calendar, cityCodeMap, validateList);
            sb.append("Из "+cit + "в "+iter).append("\n");
            cit = (String) iter.get("city");
            validateList.add((String) iter.get("code"));
            count++;
        }
        System.out.println(sb.toString());

        return null;
    }

    private HashMap<String,Object> getMinPrice(List<HashMap<String, Object>> flights,Calendar calendar, HashMap<String, String> cityCodeMap,
                                               List<String> validateList){
        Double min = Double.MAX_VALUE;
        HashMap<String,Object> result = new HashMap<>();
        String city = null;
        String code = null;
        for (HashMap<String, Object> map : flights) {
            if (map.get("depart_date").equals((new SimpleDateFormat("yyyy-MM-dd")).format(calendar.getTime()))) {
                if(validateList!=null && validateList.contains(map.get("destination"))){
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
                    city = cityCodeMap.get((String) map.get("destination"));
                    code = (String) map.get("destination");
                }
            }
        }
        result.put("price", min);
        result.put("city", city);
        result.put("code", code);

        return result;
    }


}
