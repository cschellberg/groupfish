package com.eliga.fishgrouper.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LOCATION",uniqueConstraints=
@UniqueConstraint(columnNames={"city", "province","country"}))
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String city;
	
	@NotNull
	private String province;
	
	private String country="PANAMA";
	
	@NotNull
	private Boolean active;

	@OneToMany
	private List<Boat> boats;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Boat> getBoats() {
		return boats;
	}

	public void setBoats(List<Boat> boats) {
		this.boats = boats;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", city=" + city + ", province=" + province + ", country=" + country + ", boats="
				+ boats + "]";
	}

	public void capitalize() {
		this.city=city.trim().toUpperCase();
		this.province=province.trim().toUpperCase();
		this.country=country.trim().toUpperCase();
	}

	public String displayName(){
		return city+", "+province+", "+country;
	}
}
