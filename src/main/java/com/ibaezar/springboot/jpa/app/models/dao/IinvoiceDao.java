package com.ibaezar.springboot.jpa.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ibaezar.springboot.jpa.app.models.entities.Invoice;

public interface IinvoiceDao extends CrudRepository<Invoice, Long> {
    
}
