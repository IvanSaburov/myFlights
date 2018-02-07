package com.myflights.entity;


import java.util.Date;

public class Segment {
  City sourceCity;
  City destinationCity;
  Date flightDate;
  Double flightPrice;

  public Segment() {
  }

  public City getSourceCity() {
    return sourceCity;
  }

  public void setSourceCity(City sourceCity) {
    this.sourceCity = sourceCity;
  }

  public City getDestinationCity() {
    return destinationCity;
  }

  public void setDestinationCity(City destinationCity) {
    this.destinationCity = destinationCity;
  }

  public Date getFlightDate() {
    return flightDate;
  }

  public void setFlightDate(Date flightDate) {
    this.flightDate = flightDate;
  }

  public Double getFlightPrice() {
    return flightPrice;
  }

  public void setFlightPrice(Double flightPrice) {
    this.flightPrice = flightPrice;
  }
}
