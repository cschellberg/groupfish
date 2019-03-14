package com.eliga.fishgrouper.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany
	private List<Person> passengers;
	
	@NotNull
	@ManyToOne
	private Location location;
	
	@NotNull
	private Date tripDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Person> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Person> passengers) {
		this.passengers = passengers;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getTripDate() {
		return tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", passengers=" + passengers + ", location=" + location + ", tripDate=" + tripDate
				+ "]";
	}

	
}
