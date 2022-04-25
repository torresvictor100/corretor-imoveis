package com.project.corretor.imoveis.corretor.imoveis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.corretor.imoveis.corretor.imoveis.entity.Profile;
import com.project.corretor.imoveis.corretor.imoveis.entity.User;
import com.project.corretor.imoveis.corretor.imoveis.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User findByUserName(String UserName) {
		return userRepository.findByUserName(UserName).orElse(null);
	}
	
	public User save(User user)  {
		
		user.setId(null);
		user.addProfile(Profile.ADMIN);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	public User update(User user) {
		return userRepository.save(user);
	}
	
	public void deleteById(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		}
	}
	
	
	public void deleteAll() {
		userRepository.deleteAllInBatch();
	}

}
