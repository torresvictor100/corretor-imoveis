package com.project.corretor.imoveis.corretor.imoveis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.corretor.imoveis.corretor.imoveis.entity.House;
import com.project.corretor.imoveis.corretor.imoveis.entity.Photos;
import com.project.corretor.imoveis.corretor.imoveis.repository.PhotosRepository;

@Service
public class PhotosService {
	
	private final PhotosRepository photosRepository;
	
	private final HouseService houseService;

	
	
	public PhotosService(PhotosRepository photosRepository, HouseService houseService) {
		super();
		this.photosRepository = photosRepository;
		this.houseService = houseService;
	}

	public List<Photos> findAll() {
		return photosRepository.findAll();
	}
	
	public Photos findById(Long id) {
		return photosRepository.findById(id).orElse(null);
	}
	
	public Photos save(Photos photos) {
		photos.setId(null);

		return saveInternal(photos);
	}
	
	
	public Photos update(Photos photos) {
		Long id = photos.getId();
		if (id != null && photosRepository.existsById(id)) {
			return saveInternal(photos);
		} else {
			return null;
		}
	}
	
	public List<Photos> findByHouseId(Long houseId){
		return photosRepository.findByHouseId(houseId);
	}
	
	public void deleteById(Long id) {
		if (photosRepository.existsById(id)) {
			photosRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		photosRepository.deleteAllInBatch();
	}
	
	private Photos saveInternal(Photos photos) {
		photos = photosRepository.save(photos);
		
		House house = houseService.findById(photos.getId());
		photos.setHouse(house);
		
		return photos;
	}

	
}
