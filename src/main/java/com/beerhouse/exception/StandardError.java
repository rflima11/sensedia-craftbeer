package com.beerhouse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {

	private int status;
	private Long timestamp;	
	private String message;
	
}
