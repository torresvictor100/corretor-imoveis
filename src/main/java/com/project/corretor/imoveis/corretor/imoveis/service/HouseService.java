package com.project.corretor.imoveis.corretor.imoveis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.corretor.imoveis.corretor.imoveis.entity.House;
import com.project.corretor.imoveis.corretor.imoveis.entity.Photos;
import com.project.corretor.imoveis.corretor.imoveis.entity.Street;
import com.project.corretor.imoveis.corretor.imoveis.repository.HouseRepository;
import com.project.corretor.imoveis.corretor.imoveis.repository.PhotosRepository;

@Service
public class HouseService {
	
	private final HouseRepository houseRepository;
	private final PhotosRepository photosRepository;
	private final StreetService streetService;

	
	public HouseService(HouseRepository houseRepository, PhotosRepository photosRepository,
			StreetService streetService) {
		super();
		this.houseRepository = houseRepository;
		this.photosRepository = photosRepository;
		this.streetService = streetService;
	}

	public List<House> findAll() {
		return houseRepository.findAll();
	}
	
	public List<House> findByStreetId(Long streetId){
		return houseRepository.findByStreetId(streetId);
	}
	
	public House findById(Long id) {
		return houseRepository.findById(id).orElse(null);
	}
	
	public House save(House house) {
		house.setId(null);

		return saveInternal(house);
	}
	
	public House update(House house) {
		Long id = house.getId();
		if (id != null && houseRepository.existsById(id)) {
			return saveInternal(house);
		} else {
			return null;
		}
	}
	
	public void deleteById(Long id) {
		if (houseRepository.existsById(id)) {
			houseRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		houseRepository.deleteAllInBatch();
	}

	private House saveInternal(House house) {
		house = houseRepository.save(house);
		
		Street street = streetService.findById(house.getId());
		house.setStreet(street);
		
		List<Photos> photos = photosRepository.findByHouseId(house.getId());
		house.setPhotos(photos);
		
		return house;
	}

}
