package com.project.corretor.imoveis.corretor.imoveis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.corretor.imoveis.corretor.imoveis.entity.User;
import com.project.corretor.imoveis.corretor.imoveis.repository.UserRepository;
import com.project.corretor.imoveis.corretor.imoveis.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName).orElse(null);
		if(user == null) {
			throw new UsernameNotFoundException(userName);
		}
		return new UserSS(user.getId(), user.getUserName(), user.getPassword(), user.getProfile());
	}
	

}
