package com.project.corretor.imoveis.corretor.imoveis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "client")
public class Client {
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false, length = 30)
	private String name;
	
	@Column(name = "numberTelephone", unique = false, nullable = false, length = 30)
	@Size(min = 8)
	private String numberTelephone;
	
	@Column(name = "email", unique = true, nullable = false, length = 50)
	@Email
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "street_id", nullable = false)
	private Long streetId;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne(optional = false)
	@JoinColumn(name = "street_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnoreProperties({"client"})
	private Street street;
	
	@Column(name = "numberHouse", nullable = false, length = 6)
	private String numberHouse;
	
	@Column(name = "complement")
	private String complement;

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

	public String getNumberTelephone() {
		return numberTelephone;
	}

	public void setNumberTelephone(String numberTelephone) {
		this.numberTelephone = numberTelephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getNumberHouse() {
		return numberHouse;
	}

	public void setNumberHouse(String numberHouse) {
		this.numberHouse = numberHouse;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}
	
	

	public Long getStreetId() {
		return streetId;
	}

	public void setStreetId(Long streetId) {
		this.streetId = streetId;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", numberTelephone=" + numberTelephone + ", email=" + email
				+ ", streetId=" + streetId + ", street=" + street + ", numberHouse=" + numberHouse + ", complement="
				+ complement + "]";
	}

	

	

	
	
	
	
}
