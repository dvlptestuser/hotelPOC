package com.hotel.fixtures;

import org.springframework.stereotype.Component;

import com.hotel.model.Hotel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class HotelDataFixtures {
  public List<Hotel> getHotelsWithRatingValues(Double... ratings) {
    return Stream.of(ratings)
        .map(rating -> Hotel.builder().setRating(rating).build())
        .collect(Collectors.toList());
  }
}
