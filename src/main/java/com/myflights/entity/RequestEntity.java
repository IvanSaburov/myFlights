package com.myflights.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class RequestEntity {

//  @JsonProperty("Roll Number")
  private HashMap<String, Object> params;

  public HashMap<String, Object> getParams() {
    return params;
  }

  public RequestEntity() {
  }

}