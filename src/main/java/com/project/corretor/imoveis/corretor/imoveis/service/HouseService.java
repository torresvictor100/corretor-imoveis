package com.project.corretor.imoveis.corretor.imoveis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.corretor.imoveis.corretor.imoveis.entity.Client;
import com.project.corretor.imoveis.corretor.imoveis.entity.House;
import com.project.corretor.imoveis.corretor.imoveis.entity.Photo;
import com.project.corretor.imoveis.corretor.imoveis.entity.Street;
import com.project.corretor.imoveis.corretor.imoveis.repository.HouseRepository;
import com.project.corretor.imoveis.corretor.imoveis.repository.PhotoRepository;

@Service
public class HouseService {
	
	private final HouseRepository houseRepository;
	private final PhotoRepository photosRepository;
	private final StreetService streetService;
	private final ClientService clientService;	

	
	

	public HouseService(HouseRepository houseRepository, PhotoRepository photosRepository, StreetService streetService,
			ClientService clientService) {
		super();
		this.houseRepository = houseRepository;
		this.photosRepository = photosRepository;
		this.streetService = streetService;
		this.clientService = clientService;
	}

	public List<House> findAll() {
		return houseRepository.findAll();
	}
	
	public List<House> findByStreetId(Long streetId){
		return houseRepository.findByStreetHouseId(streetId);
	}
	
	public House findById(Long id) {
		return houseRepository.findById(id).orElse(null);
	}
	
	@Autowired
	private EmailService emailService;
	
	public House save(House house) {
		house.setId(null);
		
		emailService.sendOrderConfirmationEmail(house);
		
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
		
		Street street = streetService.findById(house.getStreetHouseId());
		house.setStreetHouse(street);
		
		Client client = clientService.findById(house.getClientId());
		house.setClient(client);
		
		List<Photo> photos = photosRepository.findByHouseId(house.getId());
		house.setPhotos(photos);
		
		
		return house;
	}

}
