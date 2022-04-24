package com.project.corretor.imoveis.corretor.imoveis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.corretor.imoveis.corretor.imoveis.entity.House;
import com.project.corretor.imoveis.corretor.imoveis.entity.Photo;
import com.project.corretor.imoveis.corretor.imoveis.repository.PhotoRepository;

@Service
public class PhotoService {
	
	private final PhotoRepository photosRepository;
	
	private final HouseService houseService;

	
	
	public PhotoService(PhotoRepository photosRepository, HouseService houseService) {
		super();
		this.photosRepository = photosRepository;
		this.houseService = houseService;
	}

	public List<Photo> findAll() {
		return photosRepository.findAll();
	}
	
	public Photo findById(Long id) {
		return photosRepository.findById(id).orElse(null);
	}
	
	public Photo save(Photo photos) {
		photos.setId(null);

		return saveInternal(photos);
	}
	
	
	public Photo update(Photo photos) {
		Long id = photos.getId();
		if (id != null && photosRepository.existsById(id)) {
			return saveInternal(photos);
		} else {
			return null;
		}
	}
	
	public List<Photo> findByHouseId(Long houseId){
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
	
	private Photo saveInternal(Photo photos) {
		photos = photosRepository.save(photos);
		
		House house = houseService.findById(photos.getId());
		photos.setHouse(house);
		
		return photos;
	}

	
}
