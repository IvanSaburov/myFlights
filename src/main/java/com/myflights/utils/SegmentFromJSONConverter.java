package com.myflights.utils;


import com.myflights.entity.City;
import com.myflights.entity.Trip;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SegmentFromJSONConverter {

    public static Trip convert(HashMap<String, Object> input) {
        Trip trip = new Trip();
        int segmentQuantity = 0;
        for (String key : input.keySet()) {
            if (key.contains("cityName")) {
                segmentQuantity++;
            }
        }
        for (int i = 1; i <= segmentQuantity; i++) {
            City city = new City();
            city.setCityName((String) input.get("cityName" + i));
            city.setCityCode(city.getCityName().substring(city.getCityName().indexOf("[") + 1, city.getCityName().indexOf("]")));
            city.setNightsQuantity(Integer.valueOf((String) input.get("nightsQuantity" + i)));
            city.setIsFirstCity(getBooleanChbValue((String) input.get("startChb" + i)));
            city.setIsLastCity(getBooleanChbValue((String) input.get("endChb" + i)));
            trip.getCities().add(city);
        }
        trip.setStartDate(getDateFromString((String) input.get("startDt")));
        trip.setEndDate(getDateFromString((String) input.get("endDt")));

        return trip;
    }

    private static Date getDateFromString(String date) {
        try {
            return (new SimpleDateFormat("yyyy-MM-dd")).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Boolean getBooleanChbValue(String value) {
        if (value == null) {
            return false;
        }
        if (("on").equals(value.toLowerCase().trim())) {
            return true;
        } else {
            return false;
        }
    }
}
