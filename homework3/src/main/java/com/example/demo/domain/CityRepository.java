/**
 * File: 		CityRepository.java
 * Description: java repository for querying database by city name
 * Bugs: 		none known
 * Purpose:		CST438 Homework 2
 * @author		George Blombach
 * @version     1.0
 * @see also
 */

package com.example.demo.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
	List<City> findByName(String name);
}
