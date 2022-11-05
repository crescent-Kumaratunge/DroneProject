package com.crescent.drone.exceptions;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crescent.drone.utils.HttpResponse;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
	
	@ExceptionHandler(DroneNotFoundException.class)
	public ResponseEntity<HttpResponse> droneNotFoundException(DroneNotFoundException exception){
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(DroneExistsException.class)
	public ResponseEntity<HttpResponse> droneExistsException(DroneExistsException exception){
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	@ExceptionHandler(DroneBatteryException.class)
	public ResponseEntity<HttpResponse> droneBatteryException(DroneBatteryException exception){
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	@ExceptionHandler(DroneWeightException.class)
	public ResponseEntity<HttpResponse> droneWeightException(DroneWeightException exception){
		return createHttpResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
	}
	
	private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
		return new ResponseEntity<HttpResponse>(new HttpResponse(httpStatus.value(), httpStatus,
				httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase()), httpStatus);
	}
}
