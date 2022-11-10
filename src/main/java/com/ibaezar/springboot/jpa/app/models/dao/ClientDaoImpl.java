package com.ibaezar.springboot.jpa.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ibaezar.springboot.jpa.app.models.entities.Client;

@Repository
public class ClientDaoImpl implements IClientDao{

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Client> getAll() {
		return em.createQuery("from Client").getResultList();
	}

}
