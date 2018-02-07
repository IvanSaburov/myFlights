package com.myflights.controllers;

import com.myflights.entity.RequestEntity;
import com.myflights.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Configuration
@Controller
public class CalcController {
  @Autowired
  PriceService priceService;

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String printHome(ModelMap model) {
    model.addAttribute("message", "Hello world!");
    return "index";
  }

  @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded; charset=cp1251")
  public String printWelcome(@RequestParam Map<String, Object> body) {
    String st = "";
    return "hello";
  }

  @RequestMapping(value = "/calc", method = RequestMethod.POST, consumes = "application/json; charset=UTF-8",
          produces = "application/json; charset=UTF-8")
  @ResponseBody
  public String printCome(@RequestBody RequestEntity req) {
    String res = priceService.findCheapestRoute(req);
    String json = "{\"test\" : \""+res+"\"}";
    return json;
  }


}