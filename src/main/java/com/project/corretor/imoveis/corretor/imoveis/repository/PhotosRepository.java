package com.project.corretor.imoveis.corretor.imoveis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.corretor.imoveis.corretor.imoveis.entity.Photos;

@Repository
public interface PhotosRepository extends JpaRepository<Photos, Long> {
	
	List<Photos> findByHouseId(Long houseId);

}
