/**
 * File: 		CityService.java
 * Description: Controller for city info and reservations 
 * Bugs: 		none known
 * Purpose:		CST438 Homework 3
 * @author		George Blombach
 * @version     1.0
 * @see also
 */
package com.example.demo.service;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.demo.domain.*;


@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private WeatherService weatherService;
	
	public CityInfo getCityInfo(String cityName) {
     
		List<City> city = cityRepository.findByName(cityName);
		
		if(city.size() > 0) {
			Country country = countryRepository.findByCode(city.get(0).getCountryCode());
			TimeAndTemp timeAndTemp = weatherService.getTimeAndTemp(cityName);
			return new CityInfo(city.get(0),country.getName(), timeAndTemp.getFahrenheit(), timeAndTemp.getLocalTime());
		}
		else {
			return null;
		}
    }
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
    @Autowired
    private FanoutExchange fanout;

    public void requestReservation(String cityName,String level,String email) {
		String msg  = "{\"cityName\": \""+ cityName + 
               "\" \"level\": \""+level+
               "\" \"email\": \""+email+"\"}" ;
		System.out.println("Sending message:"+msg);
		rabbitTemplate.convertSendAndReceive(fanout.getName(), "", msg);  // routing key none.
                
	}

}

