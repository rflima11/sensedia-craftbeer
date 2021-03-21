package com.beerhouse.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.beerhouse.dto.BeerDto;
import com.beerhouse.model.Beer;

@Mapper
public interface BeerMapper {
	
	static BeerMapper getInstance() {
		return Mappers.getMapper(BeerMapper.class);
	}
	
	BeerDto beerToBeerDto(Beer beer);
	
	Beer beerDtoToBeer(BeerDto beerDto);
	
    List<BeerDto> map(List<Beer> employees);

}
