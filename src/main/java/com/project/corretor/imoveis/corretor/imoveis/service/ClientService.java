package com.project.corretor.imoveis.corretor.imoveis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.corretor.imoveis.corretor.imoveis.entity.Client;
import com.project.corretor.imoveis.corretor.imoveis.entity.Street;
import com.project.corretor.imoveis.corretor.imoveis.repository.ClientRepository;

@Service
public class ClientService {
	
	private final ClientRepository clientRepository;
	
	private final StreetService streetService;

	
	
	public ClientService(ClientRepository clientRepository, StreetService streetService) {
		super();
		this.clientRepository = clientRepository;
		this.streetService = streetService;
	}

	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public Client findById(Long id) {
		return clientRepository.findById(id).orElse(null);
	}
	
	public Client findByName(String name) {
		return clientRepository.findByName(name).orElse(null);
		
	}
	
	public List<Client> findByStreetId(Long streetId){
		return clientRepository.findByStreetId(streetId);
	}
	
	public Client save(Client client) {
		client.setId(null);

		return saveInternal(client);
	}
	
	
	public Client update(Client client) {
		Long id = client.getId();
		if (id != null && clientRepository.existsById(id)) {
			return saveInternal(client);
		} else {
			return null;
		}
	}
	
	public void deleteById(Long id) {
		if (clientRepository.existsById(id)) {
			clientRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		clientRepository.deleteAllInBatch();
	}
	
	private Client saveInternal(Client client) {
		client = clientRepository.save(client);
		
		Street street = streetService.findById(client.getStreetId());
		client.setStreet(street);
		
		return client;
	}
}
