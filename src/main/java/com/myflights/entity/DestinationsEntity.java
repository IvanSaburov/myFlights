package com.myflights.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Data
public class DestinationsEntity {

//  public Long getId (){return 0l;}
  private Boolean success;
  private List<HashMap<String,Object>> data;
//  private Object data;
//  private LinkedHashMap<String,LinkedHashMap<String, String>> data;
  private String error;
  private String currency;

  public DestinationsEntity() {
  }

  public List<HashMap<String, Object>> getData() {
    return data;
  }
}
