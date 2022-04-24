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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.corretor.imoveis.corretor.imoveis.entity.House;
import com.project.corretor.imoveis.corretor.imoveis.service.HouseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/houses")
public class HouseController {
	
	private final HouseService houseService;

	
	
	public HouseController(HouseService houseService) {
		super();
		this.houseService = houseService;
	}



	@ApiOperation(value = "Find all house")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<House>> findAll() {
		List<House> house = houseService.findAll();
		return new ResponseEntity<>(house, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find a house by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(path = "/{house_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<House> findById(@PathVariable(name = "house_id") Long id) {
		House house = houseService.findById(id);
		if (house == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(house, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Find house by street")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request") })
	@GetMapping(path = "street/{street_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<House>> findByIdCity(@PathVariable(name = "street_id") Long id) {
		List<House> house = houseService.findByStreetId(id);
		if (house == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(house, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Save a house")
	@ApiResponses({ @ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<House> save(@RequestBody House house) {
		try {
			house = houseService.save(house);
			return new ResponseEntity<>(house, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Update a house")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(path = "/{house_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<House> update(@PathVariable(name = "house_id") Long id, @RequestBody House house) {
		house.setId(id);
		try {
			house = houseService.update(house);
			if (house == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(house, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Delete a house")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping(path = "/{house_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "house_id") Long id) {
		houseService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Delete all house")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		houseService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
