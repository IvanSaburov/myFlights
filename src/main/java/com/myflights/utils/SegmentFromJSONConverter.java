package com.myflights.utils;


import com.myflights.entity.Segment;
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
            Segment segment = new Segment();
            segment.setCityName((String) input.get("cityName" + i));
            segment.setCityCode(segment.getCityName().substring(segment.getCityName().indexOf("[") + 1, segment.getCityName().indexOf("]")));
            segment.setNightsQuantity(Integer.valueOf((String) input.get("nightsQuantity" + i)));
            segment.setIsFirstCity(getBooleanChbValue((String) input.get("startChb" + i)));
            segment.setIsLastCity(getBooleanChbValue((String) input.get("endChb" + i)));
            trip.getSegments().add(segment);
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
