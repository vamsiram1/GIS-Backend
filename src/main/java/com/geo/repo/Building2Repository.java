package com.geo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geo.entity.Building;
import com.geo.entity.Campus;



@Repository
public interface Building2Repository extends JpaRepository<Building, Integer> {

	List<Building> findByCampus(Campus campus);
	
}
