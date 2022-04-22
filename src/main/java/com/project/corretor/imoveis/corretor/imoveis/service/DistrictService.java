package com.project.corretor.imoveis.corretor.imoveis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.corretor.imoveis.corretor.imoveis.entity.City;
import com.project.corretor.imoveis.corretor.imoveis.entity.District;
import com.project.corretor.imoveis.corretor.imoveis.entity.Street;
import com.project.corretor.imoveis.corretor.imoveis.repository.DistrictRepository;
import com.project.corretor.imoveis.corretor.imoveis.repository.StreetRepository;

@Service
public class DistrictService {
	
	private final DistrictRepository districtRepository;
	
	private final CityService cityService;
	
	private final StreetRepository streetRepository;


	

	public DistrictService(DistrictRepository districtRepository, CityService cityService,
			StreetRepository streetRepository) {
		super();
		this.districtRepository = districtRepository;
		this.cityService = cityService;
		this.streetRepository = streetRepository;
	}

	public List<District> findAll() {
		return districtRepository.findAll();
	}
	
	public District findById(Long id) {
		return districtRepository.findById(id).orElse(null);
	}
	
	public District findByName(String name) {
		return districtRepository.findByName(name).orElse(null);
		
	}
	
	public District save(District district) {
		district.setId(null);

		return saveInternal(district);
	}
	
	public District update(District district) {
		Long id = district.getId();
		if (id != null && districtRepository.existsById(id)) {
			return saveInternal(district);
		} else {
			return null;
		}
	}
	
	public List<District> findByCityId(Long districtId){
		return districtRepository.findByCityId(districtId);
	}
	
	public void deleteById(Long id) {
		if (districtRepository.existsById(id)) {
			districtRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		districtRepository.deleteAllInBatch();
	}
	
	private District saveInternal(District district) {
		district = districtRepository.save(district);
		
		City city = cityService.findById(district.getCityId());
		district.setCity(city);
		
		List<Street> streets = streetRepository.findByDistrictId(district.getId());
		district.setStreet(streets);
	
        return district;
	}
}
