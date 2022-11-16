package com.ibaezar.springboot.jpa.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ibaezar.springboot.jpa.app.models.entities.Client;

public interface IClientService {
	
	public List<Client> getAll();

	public Page<Client> findAll(Pageable pageable);
	
	public void save(Client client);
	
	public Client getById(Long id);
	
	public void delete(Long id);
}
