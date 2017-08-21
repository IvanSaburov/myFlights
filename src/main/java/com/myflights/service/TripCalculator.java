package com.myflights.service;


import com.myflights.entity.Segment;
import com.myflights.entity.Trip;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TripCalculator {
    @Autowired
    ApiClient apiClient;

    public String calcTrip(Trip trip){

        int segmentsQuantity = trip.getSegments().size();
        Date startDt = trip.getStartDate();
        Date endDt = trip.getEndDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDt);
        HashMap<String, List<HashMap<String, Object>>> result = new HashMap<>();
        for(int i = 0; i < segmentsQuantity; i++){
            Segment segment = trip.getSegments().get(i);
            for(int j = 0; j < segmentsQuantity; j++){
                if(j==i){
                    continue;
                }
                Segment destination = trip.getSegments().get(j);
                result.put(segment.getCityName(), apiClient.getMonthPrices(segment.getCityCode(),
                        destination.getCityCode(), (new SimpleDateFormat("yyyy-MM-dd")).format(startDt)));

            }
        }

        for(String item : result.keySet()){
            List<HashMap<String, Object>> flights = result.get(item);
            Double min = Double.MAX_VALUE;
            HashMap<String, Object> minMap = null;
            for(HashMap<String, Object> map : flights){
                double price;
                if(map.get("value")instanceof Integer){
                    price = ((Integer)map.get("value")).doubleValue();
                }else{
                    price =(Double) map.get("value");
            }
                try {
                    if(price<min && (new SimpleDateFormat("yyyy-MM-dd")).parse((String) map.get("depart_date")).before(endDt)){
                        min = price;
                        minMap = map;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(minMap);
            System.out.println(min);
        }
//        List<HashMap<String, Object>> data = apiClient.
        return null;
    }



}
