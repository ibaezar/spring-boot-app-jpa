// ! Esta clase ha sido deshabilidara para el uso de la interfaz CrudRepository

/*

package com.ibaezar.springboot.jpa.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ibaezar.springboot.jpa.app.models.entities.Client;

@Repository
public class ClientDaoImpl implements IClientDao{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAll() {
		return em.createQuery("from Client").getResultList();
	}
	
	@Override
	public Client getById(Long id) {
		return em.find(Client.class, id);
	}

	@Override
	public void save(Client client) {
		if(client.getId() != null && client.getId() > 0) {
			em.merge(client);
		}else {
			em.persist(client);
		}
	}

	@Override
	public void delete(Long id) {
		em.remove(getById(id));
	}

}

*/
