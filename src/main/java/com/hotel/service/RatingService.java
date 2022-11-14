package com.hotel.service;

import java.util.List;

import com.hotel.dto.RatingReportDto;
import com.hotel.model.Hotel;

public interface RatingService {
    RatingReportDto getRatingAverage(Long cityId);

    RatingReportDto getRatingAverage(List<Hotel> hotels);
}
