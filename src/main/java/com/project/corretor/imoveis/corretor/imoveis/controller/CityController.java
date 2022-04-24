package com.project.corretor.imoveis.corretor.imoveis.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.corretor.imoveis.corretor.imoveis.entity.City;
import com.project.corretor.imoveis.corretor.imoveis.service.CityService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/city")
public class CityController {
	
	private final CityService cityService;

	public CityController(CityService cityService) {
		super();
		this.cityService = cityService;
	}
	
	@ApiOperation(value = "Find all city")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<City>> findAll(@RequestParam(name = "name", required = false) String name) {
		List<City> city = cityService.findAll(name);
		return new ResponseEntity<>(city, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find a city by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(path = "/{city_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<City> findById(@PathVariable(name = "city_id") Long id) {
		City city = cityService.findById(id);
		if (city == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(city, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Find a city by name")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(path = "/name/{city_name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<City> findByName(@PathVariable(name = "city_name") String name) {
		City city = cityService.findByName(name);
		if (city == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(city, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Save a city")
	@ApiResponses({ @ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<City> save(@RequestBody City city) {
		try {
			city = cityService.save(city);
			return new ResponseEntity<>(city, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Update a city")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(path = "/{city_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<City> update(@PathVariable(name = "city_id") Long id,
			@RequestBody City city) {
		city.setId(id);
		try {
			city = cityService.update(city);
			if (city == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(city, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Delete a city")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping(path = "/{city_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "city_id") Long id) {
		cityService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Delete all city")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		cityService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
