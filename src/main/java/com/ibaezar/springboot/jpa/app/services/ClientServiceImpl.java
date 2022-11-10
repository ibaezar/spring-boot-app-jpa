package com.ibaezar.springboot.jpa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibaezar.springboot.jpa.app.models.dao.IClientDao;
import com.ibaezar.springboot.jpa.app.models.entities.Client;

@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	private IClientDao clientDao;

	@Override
	@Transactional(readOnly = true)
	public List<Client> getAll() {
		return clientDao.getAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Client getById(Long id) {
		return clientDao.getById(id);
	}

	@Override
	@Transactional
	public void save(Client client) {
		clientDao.save(client);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clientDao.delete(id);
	}

}
