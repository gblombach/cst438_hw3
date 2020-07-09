/**
 * File: 		CityController.java
 * Description: Controller for user input of city to search 
 * Bugs: 		none known
 * Purpose:		CST438 Homework 2
 * @author		George Blombach
 * @version     1.0
 * @see also
 */

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.service.CityService;
import com.example.demo.domain.*;

@Controller
public class CityController {

	@Autowired
	private CityService cityService;
	
	@GetMapping("/cities/{city}")
	public String getCityInfo(@PathVariable("city") String cityName, Model model) {
		
		CityInfo cityInfo = cityService.getCityInfo(cityName);
		
		if (cityInfo != null) {
			model.addAttribute("cityInfo", cityInfo);
			return "city_info";
		}
		else {
			model.addAttribute("time", new java.util.Date().toString());
			return "error";
		}
     }
	
	@PostMapping("/cities/reservation")
	public String createReservation(
			@RequestParam("city") String cityName, 
			@RequestParam("level") String level, 
			@RequestParam("email") String email, 
			Model model) {
		
		model.addAttribute("city", cityName);
		model.addAttribute("level", level);
		model.addAttribute("email", email);
		cityService.requestReservation(cityName, level, email);
		return "request_reservation";
	}

}
