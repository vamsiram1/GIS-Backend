package com.geo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geo.dto.CampusDto;
import com.geo.entity.BuildingEntity;
import com.geo.repo.BuildingAddressRepository;
import com.geo.repo.BuildingRepository;
import com.geo.repo.CampusRepository;
import com.geo.repo.CityRepository;
import com.geo.repo.CountryRepository;
import com.geo.repo.DistrictRepository;
import com.geo.repo.StateRepository;

@Service
public class LocationService {

//	@Autowired
//	private LocationRepo locRepo;
	
	
	
	@Autowired
	private BuildingAddressRepository buildingAddressRepo;
	
	@Autowired
	private BuildingRepository buildingRepo;
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private DistrictRepository districtRepo;
	
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired CampusRepository campusRepo;
	

	
	
	
	public CampusDto buildingAddToCampusDto(int building_address_id,String building_name, 
			Double latitude, Double longitude,String campus_name,String college_type){
		CampusDto campus = new CampusDto();
		campus.setBuilding_address_id(building_address_id);
		campus.setBuilding_name(building_name);
		campus.setLatitude(latitude);
		campus.setLongitude(longitude);
		campus.setCampus_name(campus_name);
		campus.setCollege_type(college_type);;
		return campus;
	}

//	public LocationEntity saveLocation(String name, double lat, double lon) {
//		LocationEntity loc = new LocationEntity();
//		loc.setName(name);
//		loc.setLatitude(lat);
//		loc.setLongitude(lon);
//		return locRepo.save(loc);
//	}

	
	
	
	public List<CampusDto> getAllBuildingAddress(){
		List<BuildingEntity> buildings = buildingRepo.findAll();
		
		List<CampusDto> campuses = new ArrayList<>();
		
		for(BuildingEntity building: buildings) {
        	buildingAddressRepo.findByBuilding(building).ifPresent(buildingAdd ->{
				CampusDto campusAdd = buildingAddToCampusDto(
						 buildingAdd.getBuilding_address_id(),
						 building.getBuilding_name(),
			             buildingAdd.getLatitude(),
			             buildingAdd.getLongitude(),
			             buildingAdd.getBuilding().getCampus().getCampusName(),
			             buildingAdd.getBuilding().getCampus().getCollege_type()

			             );
				campuses.add(campusAdd);
			});
		}
		
		return campuses;
	}
	
	
	
//	public List<LocationEntity> getAll() {
//		return locRepo.findAll();
//	}

	
	

}
