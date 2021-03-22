package com.beerhouse.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beerhouse.dto.BeerDto;
import com.beerhouse.dto.mapper.BeerMapper;
import com.beerhouse.exception.BeerNotFoundException;
import com.beerhouse.exception.utils.ExceptionMessages;
import com.beerhouse.repository.BeerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BeerService {
	
	@Autowired
	private BeerRepository beerRepository;
	
	 @Autowired
	 ObjectMapper objectMapper;
	
	private BeerMapper mapper = BeerMapper.getInstance();
	

	public BeerDto save(BeerDto beerDto) {
	    return mapper.beerToBeerDto(beerRepository.save(mapper.beerDtoToBeer(beerDto)));
	} 
	
	public BeerDto update(BeerDto beerDto) {
		this.findById(beerDto.getId());
		return mapper.beerToBeerDto(beerRepository.save(mapper.beerDtoToBeer(beerDto)));
	}
	
	public List<BeerDto> findAll(){
		return beerRepository.findAll()
				.stream()
				.map(mapper::beerToBeerDto)
				.collect(Collectors.toList());
	}
	
	public BeerDto findById(Long id) {
		return mapper.beerToBeerDto(beerRepository
									 .findById(id)
									 .orElseThrow(() -> new BeerNotFoundException(ExceptionMessages.getBeerNotFoundExceptionMessage(id))));
	}
	
	public void delete(Long id) {
		beerRepository.delete(beerRepository
							    .findById(id)
							    .orElseThrow(() -> new BeerNotFoundException(ExceptionMessages.getBeerNotFoundExceptionMessage(id))));
	}

	public BeerDto update(Long id, Map<String, Object> fields) {
		BeerDto beer = objectMapper.convertValue(fields, BeerDto.class);
	    BeerDto beerFindById = this.findById(id);
		if (Objects.nonNull(beer.getAlcoholContent())) {
			beerFindById.setAlcoholContent(beer.getAlcoholContent());
		} else if (Objects.nonNull(beer.getCategory())) {
			beerFindById.setCategory(beer.getCategory());
		} else if (Objects.nonNull(beer.getIngredients())) {
			beerFindById.setIngredients(beer.getIngredients());
		} else if (Objects.nonNull(beer.getName())) {
			beerFindById.setName(beer.getName());
		} else if (Objects.nonNull(beer.getPrice())) {
			beerFindById.setPrice(beer.getPrice());
		}
		return mapper.beerToBeerDto(beerRepository.save(mapper.beerDtoToBeer(beerFindById)));
	}
	
	
}
