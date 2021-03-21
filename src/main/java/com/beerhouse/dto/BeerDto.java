package com.beerhouse.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerDto {

	private Long id;
	@NotNull(message = "Name cannot be null")
	@NotEmpty(message = "Name cannot be empty")
	private String name;
	@NotNull
	@NotEmpty(message = "Ingredients cannot be empty")
	private String ingredients;
	@NotNull
	@NotEmpty(message = "Alcohol Content cannot be empty")
	private String alcoholContent;
	@NotNull
	@DecimalMax("1000.00") 
	private BigDecimal price;
	@NotNull
	@NotEmpty(message = "Category cannot be empty")
	private String category;
	

}
