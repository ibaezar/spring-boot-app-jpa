package com.ibaezar.springboot.jpa.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ibaezar.springboot.jpa.app.models.entities.Invoice;

public interface IinvoiceDao extends CrudRepository<Invoice, Long> {

    //? Consulta usando fetch(inner join) para optimizar las consultas a la base de datos.
    @Query("select i from Invoice i join fetch i.client c join fetch i.items it join fetch it.product where i.id = ?1")
    public Invoice fetchByIdWithClientWithInvoiceItemsWithProduct(Long id);
    
}
