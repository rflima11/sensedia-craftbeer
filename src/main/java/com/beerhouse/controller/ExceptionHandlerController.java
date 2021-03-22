package com.beerhouse.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.beerhouse.exception.BeerNotFoundException;
import com.beerhouse.exception.StandardError;

@ControllerAdvice
public class ExceptionHandlerController {
	
	
	
	private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

	@ExceptionHandler(BeerNotFoundException.class)
	public ResponseEntity<StandardError> handleBeerNotFoundException(HttpServletRequest req, BeerNotFoundException e) {
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(), e.getMessage());
		LOG.error("Request: " + req.getRequestURL() + " raised " + e);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err); 
	} 
	 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException e) {		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(), e.getFieldError().getDefaultMessage());
		LOG.error("Request: " + req.getRequestURL() + " raised " + e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
