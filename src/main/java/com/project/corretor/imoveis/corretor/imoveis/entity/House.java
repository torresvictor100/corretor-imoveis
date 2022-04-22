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
@Table(name = "house")
public class House {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "street_id", nullable = false)
	private Long streetId;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "street_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnoreProperties({"house"})
	private Street street;
	
	@Column(name = "numberHouse", length = 6)
	private String numberHouse;
	
	@Column(name = "complement")
	private String complement;
	
	@Column(name = "Room")
	private Integer Room;
	
	@Column(name = "value")
	private Double value;
	
	@Column(name = "suites")
	private Integer suites;
	
	@Column(name = "garage")
	private Boolean garage;
	
	@Column(name = "area")
	private Double area;
	
	@Column(name = "buildingarea")
	private Double buildingarea;
	
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "house")
    private List<Photos> photos;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public String getNumberHouse() {
		return numberHouse;
	}

	public void setNumberHouse(String numberHouse) {
		this.numberHouse = numberHouse;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public Long getStreetId() {
		return streetId;
	}

	public void setStreetId(Long streetId) {
		this.streetId = streetId;
	}

	public List<Photos> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}

	public Integer getRoom() {
		return Room;
	}

	public void setRoom(Integer room) {
		Room = room;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getSuites() {
		return suites;
	}

	public void setSuites(Integer suites) {
		this.suites = suites;
	}

	public Boolean getGarage() {
		return garage;
	}

	public void setGarage(Boolean garage) {
		this.garage = garage;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getBuildingarea() {
		return buildingarea;
	}

	public void setBuildingarea(Double buildingarea) {
		this.buildingarea = buildingarea;
	}

	@Override
	public String toString() {
		return "House [id=" + id + ", streetId=" + streetId + ", street=" + street + ", numberHouse=" + numberHouse
				+ ", complement=" + complement + ", Room=" + Room + ", value=" + value + ", suites=" + suites
				+ ", garage=" + garage + ", area=" + area + ", buildingarea=" + buildingarea + ", photos=" + photos
				+ "]";
	}

	

	
	


}
