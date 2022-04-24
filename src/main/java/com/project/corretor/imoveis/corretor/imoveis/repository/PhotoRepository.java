package com.project.corretor.imoveis.corretor.imoveis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.corretor.imoveis.corretor.imoveis.entity.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
	
	List<Photo> findByHouseId(Long houseId);

}
