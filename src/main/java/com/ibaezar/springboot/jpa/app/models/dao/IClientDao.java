package com.ibaezar.springboot.jpa.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ibaezar.springboot.jpa.app.models.entities.Client;
//import java.util.List;

public interface IClientDao extends CrudRepository<Client, Long>{
	
	// TODO: Comentamos este codigo para usar la interfaz CrudRepository.

	// public List<Client> getAll();
	
	// public void save(Client client);
	
	// public Client getById(Long id);
	
	// public void delete(Long id);
}
