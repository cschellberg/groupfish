package com.eliga.fishgrouper.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eliga.fishgrouper.model.Location;
import com.eliga.fishgrouper.model.Trip;
import com.eliga.fishgrouper.repository.LocationRepository;
import com.eliga.fishgrouper.repository.TripRepository;
import com.eliga.fishgrouper.utils.DateUtils;

@Component
@RequestMapping(value = "/fncs")
public class FishGrouperController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FishGrouperController.class);

	private Date lastPurgeDate = new Date();

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private TripRepository tripRepository;

	@RequestMapping(value = "/locations/add", method = RequestMethod.POST)
	public @ResponseBody Boolean addLocation(@RequestBody(required = true) Location location) throws Exception {
		try {
			location.capitalize();
			locationRepository.save(location);
		} catch (Exception ex) {
			LOGGER.error("Cannot add location {} because {}", location, ex.getMessage());
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@RequestMapping(value = "/trips/add", method = RequestMethod.POST)
	public @ResponseBody Boolean addTrip(@RequestBody(required = true) Trip trip) throws Exception {
		try {
			tripRepository.save(trip);
		} catch (Exception ex) {
			LOGGER.error("Cannot add trip {} because {}", trip, ex.getMessage());
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	@RequestMapping(value = "/trips", method = RequestMethod.GET)
	public @ResponseBody List<Trip> listTrips(@RequestParam(required = true) Long locationId) throws Exception {
		List<Trip> retList = null;
		try {
			if (System.currentTimeMillis() - lastPurgeDate.getTime() > TimeUnit.HOURS.toMillis(1)) {
				Date cutoffDate = DateUtils.getFutureDate("-1d");
				tripRepository.deleteByTripDateLessThan(cutoffDate);
			}
			retList = tripRepository.findByLocationId(locationId);
			retList = checkAndAddForNextTwoWeeks(retList, locationId);
		} catch (Exception ex) {
			LOGGER.error("Cannot list trips  because {}", ex.getMessage());
		}
		return retList;
	}

	private List<Trip> checkAndAddForNextTwoWeeks(List<Trip> retList, Long locationId) {
		Set<String> tripSet = retList.stream().map(trip -> DateUtils.getJulianDate(trip.getTripDate()))
				.collect(Collectors.toSet());
		Optional<Location> optLocation = null;
		for (int ii = 0; ii < 14; ii++) {
			Date tmpDate = DateUtils.getDateAtMidnight(DateUtils.getDate(ii));
			String julianDate = DateUtils.getJulianDate(tmpDate);
			if (!tripSet.contains(julianDate)) {
				optLocation = locationRepository.findById(locationId);
				if (optLocation.isPresent()) {
					Trip trip = new Trip();
					trip.setTripDate(tmpDate);
					trip.setLocation(optLocation.get());
					tripRepository.save(trip);
				} else {
					LOGGER.error("Location with id {} not found", locationId);
				}
			}
		}
		return tripRepository.findByLocationId(locationId);
	}

	@RequestMapping(value = "/locations/addAll", method = RequestMethod.POST)
	public @ResponseBody String addLocations(@RequestBody(required = true) List<Location> locationList)
			throws Exception {
		for (Location location : locationList) {
			try {
				location.capitalize();
				locationRepository.save(location);
			} catch (Exception ex) {
				LOGGER.error("Cannot add location {} because {}", location, ex.getMessage());
			}
		}
		return "SUCCESS";
	}

	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public @ResponseBody List<Location> getLocations() throws Exception {
		return locationRepository.findByOrderByCityAsc();
	}

}
