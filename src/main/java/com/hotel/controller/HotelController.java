package com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.hotel.model.Hotel;
import com.hotel.service.HotelService;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
	private final HotelService hotelService;

	@Autowired
	public HotelController(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Hotel> getAllHotels() {
		return hotelService.getAllHotels();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Hotel createHotel(@RequestBody Hotel hotel) {
		return hotelService.createNewHotel(hotel);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Hotel getHotelById(@PathVariable(value = "id", required = true) Long id) {
		return hotelService.getHotelById(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteHotelById(@PathVariable(value = "id", required = true) Long id) {
		hotelService.deleteHotelById(id);
	}
	
	@GetMapping("/search/{cityId}")
	@ResponseStatus(HttpStatus.OK)
	public List<Hotel> getNearestHotels(@PathVariable(value = "cityId", required = true) Long cityId, @RequestParam(name= "sortBy", defaultValue = "distance", required = true) String sortBy) {
		return hotelService.getNearestHotels(cityId);
	}
}
