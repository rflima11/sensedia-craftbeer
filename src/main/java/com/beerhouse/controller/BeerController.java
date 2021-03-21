package com.beerhouse.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.beerhouse.dto.BeerDto;
import com.beerhouse.service.BeerService;



@RestController
@RequestMapping("/beers")
public class BeerController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BeerController.class);
	
	@Autowired
	private BeerService beerService;
	
	@Transactional
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody BeerDto beer, UriComponentsBuilder uriBuilder) {
		BeerDto beerDto = beerService.save(beer);	
		URI uri = uriBuilder.path("/beers/{id}").buildAndExpand(beerDto.getId()).toUri();
		LOG.info("ADICIONADA CERVEJA: " + beerDto.getName() + " ID: " + beerDto.getId());
		return ResponseEntity.created(uri).build();
 
	}
	
	@Transactional
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody BeerDto beerDto, @PathVariable Long id) {
		beerDto.setId(id);
		BeerDto beerUpdate = beerService.update(beerDto);
		LOG.info("ATUALIZING BEER ID: " + beerUpdate.getId());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@Transactional
	@PatchMapping("/{id}")
	public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		BeerDto beerUpdate = beerService.update(id, fields);
		LOG.info("ATUALIZING BEER ID: " + beerUpdate.getId());
		return ResponseEntity.noContent().build();
	}
		
	@GetMapping
	public ResponseEntity<List<BeerDto>> findAll() {
		List<BeerDto> beers = beerService.findAll();
		beers.forEach(x -> LOG.info("Find beer: " + x.getId()));
		return ResponseEntity.status(HttpStatus.OK).build();
	}	    
	
	@GetMapping("/{id}")
	public ResponseEntity<BeerDto> findBeerById(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(beerService.findById(id));
	}	
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBeerById(@PathVariable Long id) {
		beerService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	
}
