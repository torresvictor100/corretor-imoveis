package com.project.corretor.imoveis.corretor.imoveis.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.corretor.imoveis.corretor.imoveis.entity.Photo;
import com.project.corretor.imoveis.corretor.imoveis.service.PhotoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/photos")
public class PhotoController {
	
	
	private final PhotoService photosService;

	public PhotoController(PhotoService photosService) {
		super();
		this.photosService = photosService;
	}
	
	@ApiOperation(value = "Find all photos")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Photo>> findAll() {
		List<Photo> photos = photosService.findAll();
		return new ResponseEntity<>(photos, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find a photos by id")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(path = "/{photos_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Photo> findById(@PathVariable(name = "photos_id") Long id) {
		Photo photos = photosService.findById(id);
		if (photos == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(photos, HttpStatus.OK);
		}
	}
	
	
	
	@RequestMapping(value="/photo", method=RequestMethod.POST)
	public ResponseEntity<Void> savePhoto(@RequestParam(name="file") MultipartFile multipartFile) {

			URI uri = photosService.savePhoto(multipartFile);
			return ResponseEntity.created(uri).build();
	}
	
	
	
	@ApiOperation(value = "Save a photos")
	@ApiResponses({ @ApiResponse(code = 201, message = "Created"), @ApiResponse(code = 400, message = "Bad Request") })
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Photo> save(@RequestBody Photo photos) {
		try {
			photos = photosService.save(photos);
			return new ResponseEntity<>(photos, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Update a photos")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(path = "/{photos_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Photo> update(@PathVariable(name = "photos_id") Long id, @RequestBody Photo photos) {
		photos.setId(id);
		try {
			photos = photosService.update(photos);
			if (photos == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(photos, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Delete a photos")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping(path = "/{photos_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "photos_id") Long id) {
		photosService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "Delete all photos")
	@ApiResponses({ @ApiResponse(code = 204, message = "No Content") })
	@DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		photosService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
