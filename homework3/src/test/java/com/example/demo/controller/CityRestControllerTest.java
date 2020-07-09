/**
 * File: 		CityRestControllerTest.java
 * Description: JUnit test for rest controller
 * Bugs: 		none known
 * Purpose:		CST438 Homework 2
 * @author		George Blombach
 * @version     1.0
 * @see also
 */

package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.example.demo.domain.*;
import com.example.demo.service.CityService;

@RunWith(SpringRunner.class)
@WebMvcTest(CityRestController.class)
public class CityRestControllerTest {

    @MockBean
    private CityService cityService;

    @Autowired
    private MockMvc mvc;

    
    private JacksonTester<CityInfo> json;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void getCityInfo() throws Exception {
        
    	// setup
        CityInfo cityInfo = new CityInfo(1, "Mildendo","LPT","Lilliput", "Gulliver District",7777, 85.0, "07:58 PM");
        
        // mock
        given(cityService.getCityInfo("Mildendo")).willReturn(cityInfo);
        MockHttpServletResponse response = mvc.perform(get("/api/cities/Mildendo")).andReturn().getResponse();

        // assert
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        // parse JSON
        CityInfo actualValue = json.parseObject(response.getContentAsString());

        // assert
        assertEquals(cityInfo, actualValue);
    }

}