package com.geo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geo.dto.BuildingDto;
import com.geo.dto.CampusDto;
import com.geo.entity.Building;
import com.geo.entity.BuildingEntity;
import com.geo.entity.Campus;
import com.geo.repo.Building2Repository;
import com.geo.repo.BuildingAddressRepository;
import com.geo.repo.BuildingRepository;
import com.geo.repo.Campus2Repository;
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
	
	@Autowired 
	private CampusRepository campusRepo;
	
	@Autowired
	private Building2Repository buildingRepo2;
	
	@Autowired
	private Campus2Repository campusRepo2;
	

	
	
	
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

	
	
	public List<BuildingDto> getByBuidlingAddress(){
		List<Campus> campuses = campusRepo2.findAll();
		
		List<BuildingDto> buildingIds = new ArrayList<>();
		
		for(Campus campus: campuses) {
			List<Building> buildings =  buildingRepo2.findByCampus(campus);
			for(Building building : buildings) {
				BuildingDto buildingAdd = new BuildingDto();
				buildingAdd.setBuilding_address(building.getBuilding_address());
				buildingAdd.setBuilding_name(building.getBuildingName());
				buildingAdd.setBuilding_purpose(building.getBuilding_purpose());
				buildingAdd.setLatitude(building.getLatitude());
				buildingAdd.setLongitude(building.getLongitude());
				buildingAdd.setLand_mark(building.getLand_mark());
				buildingAdd.setStreet(building.getStreet());
				buildingAdd.setCampus_name(campus.getCampusName());
				buildingAdd.setCampus_type(campus.getCampus_type());
				buildingAdd.setCollege_type(campus.getCollege_type());
				buildingIds.add(buildingAdd);
			}
		}
		
		return buildingIds;
		
	}
	
	

}
