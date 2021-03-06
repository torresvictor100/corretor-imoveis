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
	@Column(name = "streethouse_id", nullable = false)
	private Long streetHouseId;
	

	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "streethouse_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnoreProperties({"houses"})
	private Street streetHouse;

	
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "client_id", nullable = false)
	private Long clientId;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "client_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnoreProperties({"clienthouses"})
	private Client client;
	
	@Column(name = "numberHouse", length = 6)
	private String numberHouse;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "house")
	private List<Photo> photos;

	@Column(name = "complement")
	private String complement;

	@Column(name = "Room")
	private String Room;

	@Column(name = "value")
	private String value;

	@Column(name = "suites")
	private String suites;

	@Column(name = "garage")
	private Boolean garage;

	@Column(name = "area")
	private String area;

	@Column(name = "buildingarea")
	private String buildingarea;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Street getStreetHouse() {
		return streetHouse;
	}

	public void setStreetHouse(Street streetHouse) {
		this.streetHouse = streetHouse;
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

	public Long getStreetHouseId() {
		return streetHouseId;
	}

	public void setStreetHouseId(Long streetId) {
		this.streetHouseId = streetId;
	}


	public String getRoom() {
		return Room;
	}

	public void setRoom(String room) {
		Room = room;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSuites() {
		return suites;
	}

	public void setSuites(String suites) {
		this.suites = suites;
	}

	public Boolean getGarage() {
		return garage;
	}

	public void setGarage(Boolean garage) {
		this.garage = garage;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBuildingarea() {
		return buildingarea;
	}

	public void setBuildingarea(String buildingarea) {
		this.buildingarea = buildingarea;
	}
	
	

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	private String HaveGaragem(Boolean garage) {
		if(garage) {
			return "tem garagem";
		}else {
			return"n??o tem garagem";
		}
	}

	@Override
	public String toString() {
		return "Bem vindo"+client +"a imobiliaria Sousa le??o. Sua casa foi cadastrada dados:" + ", Rua" + streetHouse + 
				  "numero" + numberHouse + ","
				  		+ "complemento=" + complement
				+ ",Quartos" + Room + ", valor=" + value + ", suites=" + suites + ", garage=" + HaveGaragem(garage) + ", area total do terreno=" + area
				+ ", area contruida =" + buildingarea + "]"+ "Imobiliaria Sousa Le??o voc?? confia a gente vende";
	}

	

}
