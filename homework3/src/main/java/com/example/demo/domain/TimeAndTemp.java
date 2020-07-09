/**
 * File: 		TimeAndTemp.java
 * Description: helper class to format time and convert Kelvin to Fahrenheit
 * Bugs: 		none known
 * Purpose:		CST438 Homework 2
 * @author		George Blombach
 * @version     1.0
 * @see also
 */

package com.example.demo.domain;


import java.time.*;
import java.time.format.*;

public class TimeAndTemp {

	public double temp;
	public long time;
	public int timezone;
	
	public TimeAndTemp(double temp, long time, int timezone){
		this.temp = temp;
		this.time = time;
		this.timezone = timezone;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public long getTime() {
		return time;
	}

	public String getTimeString() {
		return time +"";
	}
	public void setTime(long time) {
		this.time = time;
	}

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	/**
	* Returns temperature as Fahrenheit degrees
	*
	* @param	none
	* @return	double
	* 
	*/
	public double getFahrenheit(){
		double temp =  (double) Math.round(((this.temp - 273.15) * 9.0 / 5.0 + 32.0) * 100) / 100;
		return (temp);
	}


	/* method to return local time 
	 * 
	 */
	public String getLocalTime(){
		Instant instant = Instant.ofEpochSecond(this.time);
		ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(this.timezone);

		OffsetDateTime offsetDate = instant.atOffset(zoneOffset);

		String timeFormat = "hh:mm a";
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(timeFormat);

		return offsetDate.format(dateTimeFormatter);
	}
}

