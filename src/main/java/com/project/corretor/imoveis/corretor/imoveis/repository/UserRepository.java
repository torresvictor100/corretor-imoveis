package com.project.corretor.imoveis.corretor.imoveis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.corretor.imoveis.corretor.imoveis.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
		
		
		Optional<User> findByUserName(String UserName);
		


}
