package com.myflights.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Trip {
  private List<City> cities = new ArrayList<City>();
  private Date startDate;
  private Date endDate;

  public List<City> getCities() {
    return cities;
  }

  public void setCities(List<City> cities) {
    this.cities = cities;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
}
