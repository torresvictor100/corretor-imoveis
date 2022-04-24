package com.project.corretor.imoveis.corretor.imoveis.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "client")
	private List<House> clienthouses;

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

	

	public List<House> getClienthouses() {
		return clienthouses;
	}

	public void setClienthouses(List<House> clienthouses) {
		this.clienthouses = clienthouses;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", numberTelephone=" + numberTelephone + ", email=" + email
				+ "]";
	}

	

	

	
	
	
	
}
