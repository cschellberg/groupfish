package com.eliga.fishgrouper.repository;

import org.springframework.data.repository.CrudRepository;

import com.eliga.fishgrouper.model.Person;


public interface PersonRepository extends CrudRepository<Person, Long>{ 

}
