package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.model.City;

public interface CityRepository extends JpaRepository<City, Long> {}
