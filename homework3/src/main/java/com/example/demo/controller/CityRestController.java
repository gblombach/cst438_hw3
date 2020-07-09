/**
 * File: 		CityRestController.java
 * Description: Rest controller for city information
 * Bugs: 		none known
 * Purpose:		CST438 Homework 2
 * @author		George Blombach
 * @version     1.0
 * @see also
 */

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.domain.*;
import com.example.demo.service.CityService;

@RestController
public class CityRestController {

	@Autowired
	private CityService cityService;
	
	@GetMapping("/api/cities/{city}")
	public ResponseEntity<CityInfo> getWeather(@PathVariable("city") String cityName) {

		CityInfo cityInfo = cityService.getCityInfo(cityName);
		
		if(cityInfo != null) {
			return new ResponseEntity<CityInfo>(cityInfo, HttpStatus.OK);			
		}
		else{
			return new ResponseEntity<CityInfo>(HttpStatus.NOT_FOUND);
		}

	}

}
