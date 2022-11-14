package com.hotel.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.exception.BadRequestException;
import com.hotel.exception.ElementNotFoundException;
import com.hotel.model.City;
import com.hotel.model.Hotel;
import com.hotel.repository.HotelRepository;
import com.hotel.service.CityService;
import com.hotel.service.HotelService;
import com.hotel.util.HotelUtility;

@Service
class DefaultHotelService implements HotelService {
	private final HotelRepository hotelRepository;

	@Autowired
	private CityService cityService;

	@Autowired
	DefaultHotelService(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public List<Hotel> getHotelsByCity(Long cityId) {
		return hotelRepository.findAll().stream().filter((hotel) -> cityId.equals(hotel.getCity().getId()))
				.collect(Collectors.toList());
	}

	@Override
	public Hotel createNewHotel(Hotel hotel) {
		if (hotel.getId() != null) {
			throw new BadRequestException("The ID must not be provided when creating a new Hotel");
		}

		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel getHotelById(Long id) {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		if (hotel.isPresent()) {
			return hotel.get();
		} else {
			throw new ElementNotFoundException("No hotel found for the given ID");
		}
	}

	@Override
	public void deleteHotelById(Long id) {
		Optional<Hotel> hotel = hotelRepository.findById(id);
		if (hotel.isPresent()) {
			Hotel hotelObjectToBeDeleted = hotel.get();
			hotelObjectToBeDeleted.setDeleted(true);
			hotelRepository.save(hotelObjectToBeDeleted);
		} else {
			throw new ElementNotFoundException("No hotel found for the given ID");
		}
	}

	@Override
	public List<Hotel> getNearestHotels(Long cityId) {

		City city = cityService.getCityById(cityId);
		Map<Long, Double> distanceMap = new HashMap<>();
		List<Hotel> hotelList = getAllHotels();
		Map<Long, Hotel> hotelMap = new HashMap<>();

		populateDistanceMap(city, distanceMap, hotelList, hotelMap);

		distanceMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> distanceMap.put(x.getKey(), x.getValue()));

		List<Hotel> nearestHotelList = new ArrayList<Hotel>();
		for (Map.Entry<Long, Double> entry : distanceMap.entrySet()) {
			
			nearestHotelList.add(hotelMap.get(entry.getKey()));
		}

		return nearestHotelList.stream().limit(3).collect(Collectors.toList());
	}

	private void populateDistanceMap(City city, Map<Long, Double> distanceMap, List<Hotel> hotelList, Map<Long, Hotel> hotelMap) {
		for (Hotel hotel : hotelList) {
			distanceMap.put(hotel.getId(), HotelUtility.getDistanceByHaversine(city.getCityCentreLatitude(),
					city.getCityCentreLongitude(), hotel.getLatitude(), hotel.getLongitude()));
			
			hotelMap.put(hotel.getId(), hotel);
		}
	}

}
