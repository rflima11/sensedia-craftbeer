package com.beerhouse.exception;

public class BeerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2224167911997035296L;

	  public BeerNotFoundException(String errorMessage, Throwable err) {
	        super(errorMessage, err);
	    }
	  
	  public BeerNotFoundException(String errorMessage) {
		  super(errorMessage);
	  }
}
