package com.ibaezar.springboot.jpa.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ibaezar.springboot.jpa.app.models.entities.Client;
import com.ibaezar.springboot.jpa.app.models.entities.Invoice;
import com.ibaezar.springboot.jpa.app.models.entities.Product;

public interface IClientService {
	
	public List<Client> getAll();

	public Page<Client> findAll(Pageable pageable);
	
	public void save(Client client);
	
	public Client getById(Long id);

	//? Consulta usando fetch(inner join) para optimizar las consultas a la base de datos.
	public Client fetchByIdWithInvoices(Long id);
	
	public void delete(Long id);

	public List<Product> findByName(String text);

	public void saveInvoice(Invoice invoice);

	public Product findProductById(long id);

	public Invoice findInvoiceById(Long id);

	public Invoice fetchByIdWithClientWithInvoiceItemsWithProduct(Long id);

	public void deleteInvoice(Long id);
}
