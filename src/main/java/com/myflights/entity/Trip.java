package com.myflights.entity;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Trip {
  private List<Segment> segments = new ArrayList<Segment>();
  private Date startDate;
  private Date endDate;

  public List<Segment> getSegments() {
    return segments;
  }

  public void setSegments(List<Segment> segments) {
    this.segments = segments;
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
