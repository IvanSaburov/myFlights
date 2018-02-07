package com.myflights.service;

import com.myflights.entity.DestinationsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Component
@PropertySource("/resources/application.properties")
@EnableConfigurationProperties
public class ApiClient {
  @Autowired
  RestTemplate restTemplate;

//  private String urlTemplate = "http://api.travelpayouts.com/v2/prices/latest?currency=rub&period_type=year&page=1&limit=30&show_to_affiliates=true&sorting=price&trip_class=0&token=c5fef5045153cc47b67557bfa948b1fe";
//  private String urlTemplate = "http://api.travelpayouts.com/v1/prices/calendar?depart_date=2017-08&return_date=&origin={origin}&destination={destination}&calendar_type=departure_date&token=c5fef5045153cc47b67557bfa948b1fe";
  private String urlTemplate = "http://api.travelpayouts.com/v2/prices/month-matrix?currency=rub&origin={origin}&destination={destination}&month={month}&show_to_affiliates=true&token=c5fef5045153cc47b67557bfa948b1fe";
  private String urlMonth = "http://api.travelpayouts.com/v2/prices/month-matrix?currency=rub&month={month}&origin={origin}&destination={destination}&show_to_affiliates=true&token=c5fef5045153cc47b67557bfa948b1fe";
  private String urlMonth1 = "http://api.travelpayouts.com/v2/prices/month-matrix?currency=rub&month=2017-08-22&origin=MOW&destination=PEE&show_to_affiliates=true&token=c5fef5045153cc47b67557bfa948b1fe";
//  @Value("${application.urlTemplate}")
//  private void setUrlTemplate(String urlTemplate) {
//    this.urlTemplate = urlTemplate;
//  }

//  public String getPrices(@NonNull Trip trip){
//    String origin = trip.getSegments().get(0).getCityCode();
//    String destination = trip.getSegments().get(1).getCityCode();
//    String month = (new SimpleDateFormat(("yyyy-MM-dd"))).format(trip.getStartDate());
//    org.springframework.http.ResponseEntity<ResponseEntity> test = null;
//    try {
//      test = restTemplate.getForEntity(urlTemplate, ResponseEntity.class,origin,destination,month);
//    }catch(Throwable t){
//      t.printStackTrace();
//    }
//    ResponseEntity res = test.getBody();
//
////    return "response";
//    return res.getData().toString();
//  }
public List<HashMap<String, Object>> getMonthPrices(String origin, String destination, String month){
//  String origin = trip.getSegments().get(0).getCityCode();
//  String destination = trip.getSegments().get(1).getCityCode();
//  String month = (new SimpleDateFormat(("yyyy-MM-dd"))).format(trip.getStartDate());
  ResponseEntity<DestinationsEntity> test = null;
  try {
    test = restTemplate.getForEntity(urlMonth, DestinationsEntity.class,month,origin, destination);
  }catch(Throwable t){
    t.printStackTrace();
  }
  DestinationsEntity res = test.getBody();

//    return "response";
  return res.getData();
}
}
