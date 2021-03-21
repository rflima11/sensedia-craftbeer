package com.beerhouse.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.beerhouse.dto.BeerDto;
import com.beerhouse.dto.mapper.BeerMapper;
import com.beerhouse.exception.BeerNotFoundException;
import com.beerhouse.model.Beer;
import com.beerhouse.repository.BeerRepository;

@RunWith(MockitoJUnitRunner.class)
public class BeerServiceTest {

	@InjectMocks
	private BeerService beerService;
	
	@Mock
	private BeerRepository beerRepository;
	
	@Mock
	private BeerMapper mapper;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		when(mapper.beerDtoToBeer(Mockito.any())).thenReturn(this.oneBeer());
		when(mapper.beerToBeerDto(Mockito.any())).thenReturn(this.oneBeerDto());
		when(beerRepository.findAll()).thenReturn(this.listOfBeers());
		when(beerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(this.oneBeer()));
	}
	
	
	@Test
	public void shouldSucessWhenSaveABeer() {
	BeerDto beerSaved = beerService.save(this.oneBeerDto());
	
	assertNotNull(beerSaved);
	assertTrue(beerSaved instanceof BeerDto);
	}
	
	@Test
	public void shouldSucessWhenFindAllBeers() {
		List<BeerDto> list = beerService.findAll();
		
		assertNotNull(list);
		assertFalse(list.isEmpty());
		assertTrue(list.get(0) instanceof BeerDto);		
	}
	
	@Test
	public void shouldSucessWhenFindABeerById() {
		BeerDto beerDto = beerService.findById(1L);
		
		assertNotNull(beerDto);
		assertTrue(beerDto instanceof BeerDto);
	}
	
	@Test
	public void shouldSucessWhenDeletingABeer() {
		beerService.delete(1L);
		
		verify(beerRepository).delete(Mockito.any(Beer.class));
	}
	
	@Test
	public void shouldSucessWhenUpdatingABeer() {
		BeerDto updatedBeer = beerService.update(oneBeerDto());
		
		assertNotNull(updatedBeer);
		assertTrue(updatedBeer instanceof BeerDto);
	}
	
	@Test
	public void shouldThrowExceptionWhenNotFindingBeer() {
		BeerDto beerDto = this.oneBeerDto();
		beerDto.setId(null);
		exception.expect(BeerNotFoundException.class);
		beerService.update(beerDto);
	}

	
	
	 private BeerDto oneBeerDto() {
		 BeerDto b = new BeerDto();
		 b.setId(6L);
		 b.setName("Beer for the test");
		 b.setIngredients("Barley");
		 b.setPrice(new BigDecimal(10d));
		 b.setAlcoholContent("9.65%");
		 b.setCategory("IPA");
		 return b;
	 }
	 
	 private Beer oneBeer() {
		 Beer b = new Beer();
		 b.setId(6L);
		 b.setName("Beer for the test");
		 b.setIngredients("Barley");
		 b.setPrice(new BigDecimal(10d));
		 b.setAlcoholContent("9.65%");
		 b.setCategory("IPA");
		 return b;
	 }
	 
	 private List<BeerDto> listOfBeersDto() {
		 List<BeerDto> beers = new ArrayList<>();
		 beers.add(oneBeerDto());
		 beers.add(oneBeerDto());
		 return beers;
	 }
	 
	 private List<Beer> listOfBeers() {
		 List<Beer> beers = new ArrayList<>();
		 beers.add(oneBeer());
		 beers.add(oneBeer());
		 return beers;
	 }
	
	
	
}
