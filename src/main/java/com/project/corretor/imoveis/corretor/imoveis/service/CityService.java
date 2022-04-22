package com.project.corretor.imoveis.corretor.imoveis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.corretor.imoveis.corretor.imoveis.entity.City;
import com.project.corretor.imoveis.corretor.imoveis.entity.District;
import com.project.corretor.imoveis.corretor.imoveis.repository.CityRepository;
import com.project.corretor.imoveis.corretor.imoveis.repository.DistrictRepository;

@Service
public class CityService {
	
	private final CityRepository cityRepository;
	
	private final DistrictRepository districtRepository;

	
	
	public CityService(CityRepository cityRepository, DistrictRepository districtRepository) {
		super();
		this.cityRepository = cityRepository;
		this.districtRepository = districtRepository;
	}

	public List<City> findAll() {
		return cityRepository.findAll();
	}
	
	public City findById(Long id) {
		return cityRepository.findById(id).orElse(null);
	}
	
	public City findByName(String name) {
		return cityRepository.findByName(name).orElse(null);
		
	}
	
	public City save(City city) {
		city.setId(null);

		return saveInternal(city);
	}
	
	public City update(City city) {
		Long id = city.getId();
		if (id != null && cityRepository.existsById(id)) {
			return saveInternal(city);
		} else {
			return null;
		}
	}
	
	public void deleteById(Long id) {
		if (cityRepository.existsById(id)) {
			cityRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		cityRepository.deleteAllInBatch();
	}
	
	
	private City saveInternal(City city) {
		city = cityRepository.save(city);
		
		List<District> district = districtRepository.findByCityId(city.getId());
		city.setDistrict(district);

        return city;
	}
}
