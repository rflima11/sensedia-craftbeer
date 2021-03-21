package com.beerhouse.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.beerhouse.model.Beer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BeerRepositoryTest {
	
	@Autowired
	private BeerRepository beerRepository;
	
	@Test
	public void shouldSucessWhenSaveBeer() {
		Beer beer = beerRepository.save(getBeer());
		assertTrue(beerRepository.findById(beer.getId()).isPresent());
		System.out.println("Se liga: " + beerRepository.findById(beer.getId()).get().getId());
		assertEquals(beer.getName(), beerRepository.findById(beer.getId()).get().getName());
	}
	
	@Test
	public void shouldSucessWhenFindAllBeers() {
		List<Beer> findAll = beerRepository.findAll();
	    assertTrue(!findAll.isEmpty());
	}
	
	@Test
	public void shouldSucessWhenFindABeerById() {
		Optional<Beer> beer = beerRepository.findById(1L);
		assertTrue(beer.isPresent());
	}
	
	@Test
	public void shouldSucessWhenDeletingABeer() {
		beerRepository.deleteById(1L);
		assertFalse(beerRepository.findById(1L).isPresent());
	}
	
	
	private Beer getBeer() {
		Beer b = new Beer();
		b.setAlcoholContent("6%");
		b.setIngredients("Barley");
		b.setName("Beer for test");
		b.setPrice(new BigDecimal(9d));
		b.setCategory("Lager");
		return b;
	}
	
}
