package com.eliga.fishgrouper.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.eliga.fishgrouper.model.Trip;

public interface TripRepository extends CrudRepository<Trip, Long>{

	public List<Trip> findByLocationCityAndLocationProvinceAndLocationCountry(String city, String province,String country);

	public void deleteByTripDateLessThan(Date cutoffDate);

	public List<Trip> findByLocationId(Long locationId); 

}
