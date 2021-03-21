package com.beerhouse.controller;

import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.beerhouse.dto.BeerDto;
import com.beerhouse.model.Beer;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTest {

 @Autowired
 private MockMvc mockMvc;
 
 @Autowired
 ObjectMapper objectMapper;
 
 private final String URI = "/beers";
 
 @Test
 public void shouldSucessWhenFindAllBeers() throws Exception {
  mockMvc
   .perform(get(URI))
   .andExpect(status()
     .isOk());
 }
 
 
 @Test
 public void shouldSucessWhenFindABeerById() throws Exception {
	 mockMvc
	 .perform(get(URI + "/1"))
	 		 .andExpect(status()
	 				 .isOk())
	 		 .andDo(print()); 
 }
 
 @Test
 public void shouldSucessWhenDeleteABeerById() throws Exception {
	mockMvc
		.perform(delete(URI + "/5"))
		.andExpect(status()
				.isNoContent());
 }
 
 @Test
 public void shouldSucessWhenAddingANewBeer() throws Exception {
	 mockMvc
	 	.perform(post(URI)
	 			.content(objectMapper.writeValueAsString(this.oneBeer()))
	 			.contentType(MediaType.APPLICATION_JSON))
	 			.andExpect(status()
	 					.isCreated()); 	
 }
 
 @Test
 public void shouldReturn404WhenTryToFindANonExistingBeer() throws Exception {
	 mockMvc
	 .perform(get(URI + "/999"))
	 .andExpect(status()
			 .isNotFound());
 }
 
 @Test
 public void shouldReturn200WhenUpdateABeerWithPatchMethod() throws Exception {
	 String oldName = this.getNameOfBeer(1L);
	 mockMvc
	 	.perform(patch(URI + "/1")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.content(objectMapper.writeValueAsString(this.partialBeer())))
	 			.andReturn()
	 			.getResponse()
	 			.getContentAsString();
	 String newName = this.getNameOfBeer(1L);
	 assertNotEquals(oldName, newName);	 	
 }
 
 
 
 private Beer oneBeer() {
	 Beer b = new Beer();
	 b.setName("Beer for the test");
	 b.setIngredients("Barley");
	 b.setPrice(new BigDecimal(10d));
	 b.setAlcoholContent("9.65%");
	 b.setCategory("IPA");
	 return b;
 }
 
 private Beer partialBeer() {
	 Beer b = new Beer();
	 b.setName("Beer with just a name");
	 return b;
 }
 
 private String getNameOfBeer(Long id) throws UnsupportedEncodingException, Exception {
	 String getByIdContent = mockMvc
	 			.perform(get(URI + "/" + id))
	 			.andReturn()
	 			.getResponse()
	 			.getContentAsString();
	 return objectMapper.readValue(getByIdContent, BeerDto.class).getName();
 } 
  
}