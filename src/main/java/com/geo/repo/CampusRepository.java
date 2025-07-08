package com.geo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geo.entity.CampusEntity;

@Repository
public interface CampusRepository extends JpaRepository<CampusEntity, Integer> {

	CampusEntity findByCampusName(String campus_name);
}
