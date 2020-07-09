package com.example.demo.service;

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
}

