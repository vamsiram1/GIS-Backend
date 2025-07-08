package com.geo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.geo.entity.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

	CountryEntity findByCountryName(String countryName);
}
