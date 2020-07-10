/**
 * File: 		CountryRepository.java
 * Description: Java repository for querying database by country code
 * Bugs: 		none known
 * Purpose:		CST438 Homework 3
 * @author		George Blombach
 * @version     1.0
 * @see also
 */
package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String>{
	Country findByCode(String code);
}
