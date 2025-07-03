package com.geo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geo.entity.LocationEntity;
import com.geo.repo.LocationRepo;

@Service
public class LocationService {

	@Autowired
	private LocationRepo locRepo;


	public LocationEntity saveLocation(String name, double lat, double lon) {
		LocationEntity loc = new LocationEntity();
		loc.setName(name);
		loc.setLatitude(lat);
		loc.setLongitude(lon);
		return locRepo.save(loc);
	}

	public List<LocationEntity> getAll() {
		return locRepo.findAll();
	}

	
	

}
