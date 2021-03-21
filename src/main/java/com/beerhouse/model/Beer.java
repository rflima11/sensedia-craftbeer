package com.beerhouse.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BEER")
public class Beer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "INGREDIENTS")
	private String ingredients;
	@Column(name = "ALCOHOL_CONTENT")
	private String alcoholContent;
	@Column(name = "PRICE", precision = 10, scale = 2)
	private BigDecimal price;
	@Column(name = "CATEGORY")
	private String category;
	

}
