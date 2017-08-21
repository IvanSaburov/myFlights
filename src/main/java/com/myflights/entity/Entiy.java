package com.myflights.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Entiy {
  private ArrayList<HashMap<String, Object>> queries;

  public Entiy() {


  }

  public ArrayList<HashMap<String, Object>> getQueries() {
    return queries;
  }

  public void setQueries(ArrayList<HashMap<String, Object>> queries) {
    this.queries = queries;
  }
}
