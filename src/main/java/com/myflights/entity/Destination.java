package com.myflights.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Destination {
  private Boolean show_to_affiliates;
  private Integer trip_class;
  private String origin;
  private String destination;
  @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date depart_date;
  private String return_date;
  private String gate;
  private Double duration;
  private Integer number_of_changes;
  private Long value;
  private String found_at;
  private Double distance;
  private Boolean actual;

  public Destination() {
  }

  public Integer getTrip_class() {
    return trip_class;
  }

  public void setTrip_class(Integer trip_class) {
    this.trip_class = trip_class;
  }

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public Date getDepart_date() {
    return depart_date;
  }

  public void setDepart_date(Date depart_date) {
    this.depart_date = depart_date;
  }

  public String getReturn_date() {
    return return_date;
  }

  public void setReturn_date(String return_date) {
    this.return_date = return_date;
  }

  public Integer getNumber_of_changes() {
    return number_of_changes;
  }

  public void setNumber_of_changes(Integer number_of_changes) {
    this.number_of_changes = number_of_changes;
  }

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value * 100;
  }

  public String getFound_at() {
    return found_at;
  }

  public void setFound_at(String found_at) {
    this.found_at = found_at;
  }

  public Double getDistance() {
    return distance;
  }

  public void setDistance(Double distance) {
    this.distance = distance;
  }

  public Boolean getActual() {
    return actual;
  }

  public void setActual(Boolean actual) {
    this.actual = actual;
  }

  public Boolean getShow_to_affiliates() {
    return show_to_affiliates;
  }

  public void setShow_to_affiliates(Boolean show_to_affiliates) {
    this.show_to_affiliates = show_to_affiliates;
  }

  public String getGate() {
    return gate;
  }

  public void setGate(String gate) {
    this.gate = gate;
  }

  public Double getDuration() {
    return duration;
  }

  public void setDuration(Double duration) {
    this.duration = duration;
  }
}
