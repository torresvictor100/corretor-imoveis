package com.project.corretor.imoveis.corretor.imoveis.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "city")
public class City {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", unique = true, nullable = false,length = 50)
	private String name;
	
	@Column(name = "stade", nullable = false,length = 50)
	private String stade;
	
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "city")
	private List<District> districts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStade() {
		return stade;
	}

	public void setStade(String stade) {
		this.stade = stade;
	}
	

	public List<District> getDistricts() {
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", stade=" + stade + "]";
	}
	
	

}
