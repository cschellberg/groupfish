package com.eliga.fishgrouper.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.eliga.fishgrouper.model.Location;

public interface LocationRepository  extends CrudRepository<Location, Long>{

	List<Location> findByOrderByCityAsc();

	Location findByCityAndProvinceAndCountry(String city, String province, String country);

}
