package com.project.corretor.imoveis.corretor.imoveis.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.corretor.imoveis.corretor.imoveis.entity.Profile;

public class UserSS implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String userName;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSS() {
	
	}
	

	public UserSS(Long id, String userName, String password, Set<Profile> profile) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.authorities = profile.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}


	public Long getId() {
		return id;
	}
		
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
