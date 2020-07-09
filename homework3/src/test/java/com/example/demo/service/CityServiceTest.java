/**
 * File: 		CityServiceTest.java
 * Description: JUnit test for service
 * Bugs: 		none known
 * Purpose:		CST438 Homework 2
 * @author		George Blombach
 * @version     1.0
 * @see also
 */

package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import com.example.demo.domain.*;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class CityServiceTest {

	@MockBean
	private WeatherService weatherService;
	
	@Autowired
	private CityService cityService;
	
	@MockBean
	private CityRepository cityRepository;
	
	@MockBean
	private CountryRepository countryRepository;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testCityOK() throws Exception {

		// setup
		Country country = new Country("LPT","Lilliput");
		City city = new City(1, "Mildendo","LPT","Gulliver District",7777);
		List<City> cities = new ArrayList<>();
		cities.add(city);

		// mocks
		given(cityRepository.findByName("Mildendo")).willReturn(cities);
		given(countryRepository.findByCode("LPT")).willReturn(country);
		given(weatherService.getTimeAndTemp("Mildendo")).willReturn(new TimeAndTemp(302.594444, 9999999,7777));

		// get values
 		CityInfo actualValue = cityService.getCityInfo("Mildendo");
 		CityInfo expectedValue = new CityInfo(city, country.getName(),85.0, "07:56 PM");

 		// assert 
		assertEquals(expectedValue,actualValue);

	}
	
	@Test 
	public void testCityNull() {
		
		// setup
		Country country = new Country("LPT","Lilliput");
		City city = new City(1, "Mildendo","LPT","Gulliver District",7777);
		List<City> cities = new ArrayList<>();
		cities.add(city);

		// mocks
		given(cityRepository.findByName("Mildendo")).willReturn(cities);
		given(countryRepository.findByCode("LPT")).willReturn(country);
		given(weatherService.getTimeAndTemp("Mildendo")).willReturn(new TimeAndTemp(302.594444, 9999999,7777));
		
		// get values
		CityInfo actualValue = cityService.getCityInfo("Blefuscu");
		CityInfo expectedValue = null;

		// Assert test
		assertEquals(expectedValue,actualValue);
	}
	
	@Test 
	public void testCities() {

		//setup
		Country country1 = new Country("LPT","Lilliput");
		Country country2 = new Country("BLF","Blefuscu");
		Country country3 = new Country("MDD","Mildendo");

		City city1 = new City(1, "Mildendo","LPT","Gulliver District 1",123);
		City city2 = new City(2, "Mildendo","BLF","Gulliver District 2",456);
		City city3 = new City(3, "Mildendo","MDD","Gulliver District 3",789);

		List<City> cities = new ArrayList<>();
		cities.add(city1);
		cities.add(city2);
		cities.add(city3);
		
		// mocks
		given(cityRepository.findByName("Mildendo")).willReturn(cities);
		given(countryRepository.findByCode("LPT")).willReturn(country1);
		given(countryRepository.findByCode("BLF")).willReturn(country2);
		given(countryRepository.findByCode("MDD")).willReturn(country3);
		given(weatherService.getTimeAndTemp("Mildendo")).willReturn(new TimeAndTemp(302.594444, 9999999,7777));
		
		//get values
		CityInfo actualValue = cityService.getCityInfo("Mildendo");
		CityInfo expectedValue = new CityInfo(city1, country1.getName(),85.0, "07:56 PM");
		
		// assert
		assertEquals(expectedValue,actualValue);
	}

}