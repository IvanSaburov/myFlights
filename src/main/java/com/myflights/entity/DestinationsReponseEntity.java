package com.myflights.entity;


import lombok.Data;

import java.util.List;

@Data
public class DestinationsReponseEntity {

//  public Long getId (){return 0l;}
  private Boolean success;
  private List<Destination> data;
//  private Object data;
//  private LinkedHashMap<String,LinkedHashMap<String, String>> data;
  private String error;
  private String currency;

  public DestinationsReponseEntity() {
  }

  public List<Destination> getData() {
    return data;
  }
}
