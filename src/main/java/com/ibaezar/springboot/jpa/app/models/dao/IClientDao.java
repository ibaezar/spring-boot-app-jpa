package com.ibaezar.springboot.jpa.app.models.dao;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ibaezar.springboot.jpa.app.models.entities.Client;
//import java.util.List;

public interface IClientDao extends PagingAndSortingRepository<Client, Long>{
	
	// TODO: Comentamos este codigo para usar la interfaz CrudRepository.

	// public List<Client> getAll();
	
	// public void save(Client client);
	
	// public Client getById(Long id);
	
	// public void delete(Long id);
}
