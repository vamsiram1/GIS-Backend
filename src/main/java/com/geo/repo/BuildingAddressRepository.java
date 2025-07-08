package com.geo.repo;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geo.entity.BuildingAddressEntity;
import com.geo.entity.BuildingEntity;

@Repository
public interface BuildingAddressRepository extends JpaRepository<BuildingAddressEntity, Integer> {

     Optional<BuildingAddressEntity> findByBuilding(BuildingEntity building);
	
}
