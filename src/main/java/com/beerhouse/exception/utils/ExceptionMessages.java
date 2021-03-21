package com.beerhouse.exception.utils;

public class ExceptionMessages {

	public static String getBeerNotFoundExceptionMessage(Long id) {
		return "Beer with ID: " + id + " not found";
	}
}
