package com.project.corretor.imoveis.corretor.imoveis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.corretor.imoveis.corretor.imoveis.entity.Client;
import com.project.corretor.imoveis.corretor.imoveis.entity.District;
import com.project.corretor.imoveis.corretor.imoveis.entity.House;
import com.project.corretor.imoveis.corretor.imoveis.entity.Street;
import com.project.corretor.imoveis.corretor.imoveis.repository.ClientRepository;
import com.project.corretor.imoveis.corretor.imoveis.repository.HouseRepository;
import com.project.corretor.imoveis.corretor.imoveis.repository.StreetRepository;

@Service
public class StreetService {
	
	private final StreetRepository streetRepository;
	private final ClientRepository clientRepository;
	private final HouseRepository houseRepository;
	private final DistrictService districtService;
	
	

	public StreetService(StreetRepository streetRepository, ClientRepository clientRepository,
			HouseRepository houseRepository, DistrictService districtService) {
		super();
		this.streetRepository = streetRepository;
		this.clientRepository = clientRepository;
		this.houseRepository = houseRepository;
		this.districtService = districtService;
	}

	public List<Street> findAll() {
		return streetRepository.findAll();
	}
	
	public List<Street> findByDistrictId(Long districtId){
		return streetRepository.findByDistrictId(districtId);
	}
	
	public Street findById(Long id) {
		return streetRepository.findById(id).orElse(null);
	}
	
	public Street findByName(String name) {
		return streetRepository.findByName(name).orElse(null);
		
	}
	
	public Street save(Street street) {
		street.setId(null);

		return saveInternal(street);
	}
	
	public Street update(Street street) {
		Long id = street.getId();
		if (id != null && streetRepository.existsById(id)) {
			return saveInternal(street);
		} else {
			return null;
		}
	}
	
	public void deleteById(Long id) {
		if (streetRepository.existsById(id)) {
			streetRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		streetRepository.deleteAllInBatch();
	}
	
	
	
	private Street saveInternal(Street street) {
		street = streetRepository.save(street);
		
		District district = districtService.findById(street.getDistrictId());
		street.setDistrict(district);
		
		List<House> houses = houseRepository.findByStreetHouseId(street.getId());
		street.setHouses(houses);
		

        return street;
	}
}
