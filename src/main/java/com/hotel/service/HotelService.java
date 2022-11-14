package com.hotel.service;

import java.util.List;

import com.hotel.model.Hotel;

public interface HotelService {
	List<Hotel> getAllHotels();

	List<Hotel> getHotelsByCity(Long cityId);

	Hotel createNewHotel(Hotel hotel);

	Hotel getHotelById(Long id);

	void deleteHotelById(Long id);

	List<Hotel> getNearestHotels(Long cityId);
}
