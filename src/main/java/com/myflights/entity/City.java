package com.myflights.entity;


public class City {

  private String cityName;
  private String cityCode;
  private Integer nightsQuantity;
  private boolean isFirstCity;
  private boolean isLastCity;

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public Integer getNightsQuantity() {
    return nightsQuantity;
  }

  public void setNightsQuantity(Integer nightsQuantity) {
    this.nightsQuantity = nightsQuantity;
  }

  public boolean isFirstCity() {
    return isFirstCity;
  }

  public void setIsFirstCity(Boolean isFirstCity) {
      this.isFirstCity = isFirstCity;
  }

  public String getCityCode() {
    return cityCode;
  }

  public void setCityCode(String cityCode) {
    this.cityCode = cityCode;
  }

  public boolean isLastCity() {
    return isLastCity;
  }

  public void setIsLastCity(Boolean isLastCity) {
      this.isLastCity = isLastCity;

  }
}
