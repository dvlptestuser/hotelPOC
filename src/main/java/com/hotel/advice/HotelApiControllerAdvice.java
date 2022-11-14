package com.hotel.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hotel.constant.HotelApiConstant;
import com.hotel.exception.BadRequestException;
import com.hotel.exception.ElementNotFoundException;
import com.hotel.model.ErrorResponse;

@ControllerAdvice
public class HotelApiControllerAdvice {

	@ExceptionHandler(ElementNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleException(ElementNotFoundException ex) {
		ErrorResponse response = new ErrorResponse(ex.getMessage(), HotelApiConstant.DATA_NOT_FOUND);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ErrorResponse> handleException(BadRequestException ex) {
		ErrorResponse response = new ErrorResponse(ex.getMessage(), HotelApiConstant.BAD_REQUEST);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
