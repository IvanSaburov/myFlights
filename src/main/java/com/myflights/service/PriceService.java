package com.myflights.service;

import com.myflights.entity.RequestEntity;
import com.myflights.entity.Trip;
import com.myflights.utils.CitiesFromJSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceService {
  @Autowired
  TripCalculator tripCalculator;

  public String findCheapestRoute(RequestEntity req){
    Trip trip = CitiesFromJSONConverter.convert(req.getParams());
    return tripCalculator.calcTrip(trip);
  }
}
