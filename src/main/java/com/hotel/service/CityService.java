package com.hotel.service;

import java.util.List;

import com.hotel.model.City;

public interface CityService {
  List<City> getAllCities();

  City getCityById(Long id);

  City createCity(City city);
}
