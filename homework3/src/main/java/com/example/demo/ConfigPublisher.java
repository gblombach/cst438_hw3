/**
 * File: 		ConfigPublisher.java
 * Description: class to create message exchange
 * Bugs: 		none known
 * Purpose:		CST438 Homework 3
 * @author		George Blombach
 * @version     1.0
 * @see also
 */
package com.example.demo;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigPublisher {
	@Bean
	public FanoutExchange fanout() {
		return new FanoutExchange("city-reservation");
	}
}

