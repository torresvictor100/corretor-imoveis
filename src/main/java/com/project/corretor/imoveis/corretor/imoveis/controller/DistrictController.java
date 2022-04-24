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

import com.project.corretor.imoveis.corretor.imoveis.entity.District;
import com.project.corretor.imoveis.corretor.imoveis.service.DistrictService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/districts")
public class DistrictController {
	
	private final DistrictService districtService;

	public DistrictController(DistrictService districtService) {
		super();
		this.districtService = districtService;
	}
	
	@ApiOperation(value = "Find all district")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<District>> findAll(@RequestParam(name = "name", required = false) String name) {
		List<District> district = districtService.findAll(name);
		return new ResponseEntity<>(district, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find a district by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(path = "/{district_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<District> findById(@PathVariable(name = "district_id") Long id) {
		District district = districtService.findById(id);
		if (district == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(district, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Find a district by name")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(path = "/name/{district_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<District> findByName(@PathVariable(name = "district_name") String name) {
		District district = districtService.findByName(name);
		if (district == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(district, HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "Save a district")
	@ApiResponses({ @ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<District> save(@RequestBody District district) {
		try {
			district = districtService.save(district);
			return new ResponseEntity<>(district, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation(value = "Update a district")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(path = "/{district_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<District> update(@PathVariable(name = "district_id") Long id, @RequestBody District district) {
		district.setId(id);
		try {
			district = districtService.update(district);
			if (district == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(district, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Delete a district")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping(path = "/{district_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "professor_id") Long id) {
		districtService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Delete all district")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		districtService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}
