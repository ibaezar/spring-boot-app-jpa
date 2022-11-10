package com.ibaezar.springboot.jpa.app.services;

import java.util.List;

import com.ibaezar.springboot.jpa.app.models.entities.Client;

public interface IClientService {
	
	public List<Client> getAll();
	
	public void save(Client client);
	
	public Client getById(Long id);
	
	public void delete(Long id);
}
