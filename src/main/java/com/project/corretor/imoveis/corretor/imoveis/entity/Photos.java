package com.project.corretor.imoveis.corretor.imoveis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "photos")
public class Photos {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "discretion")
	private String discretion;
	
	@Column(name = "url")
	private String url;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "house_id", nullable = false)
	private Long houseId;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "house_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnoreProperties({"photos"})
	private House house;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiscretion() {
		return discretion;
	}

	public void setDiscretion(String discretion) {
		this.discretion = discretion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	@Override
	public String toString() {
		return "Photos [id=" + id + ", discretion=" + discretion + ", url=" + url + ", houseId=" + houseId + ", house="
				+ house + "]";
	}

	
	
	

}
