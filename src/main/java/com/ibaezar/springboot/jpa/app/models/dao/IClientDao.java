package com.ibaezar.springboot.jpa.app.models.dao;

import java.util.List;

import com.ibaezar.springboot.jpa.app.models.entities.Client;

public interface IClientDao {
	
	public List<Client> getAll();
	
	public void save(Client client);
	
}
