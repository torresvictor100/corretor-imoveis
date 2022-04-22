package com.project.corretor.imoveis.corretor.imoveis.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "district")
public class District {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "city_id", nullable = false)
	private Long cityId;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "city_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnoreProperties({"district"})
	private City city;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "district")
    private List<Street> street;

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	

	public List<Street> getStreet() {
		return street;
	}

	public void setStreet(List<Street> street) {
		this.street = street;
	}
	

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "District [id=" + id + ", name=" + name + ", cityId=" + cityId + ", city=" + city + ", street=" + street
				+ "]";
	}

	
	
	


	
	

}
