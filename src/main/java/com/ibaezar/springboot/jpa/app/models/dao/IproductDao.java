package com.ibaezar.springboot.jpa.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ibaezar.springboot.jpa.app.models.entities.Product;

public interface IproductDao extends CrudRepository<Product, Long> {

    /*
    TODO -> metodo creando la query de forma manual
    @Query("select p from Product p where p.name like %?1%")
    public List<Product> findByName(String text);
    */

    //TODO -> metodo usando la creación de querys de data JPA
    public List<Product> findByNameLikeIgnoreCase(String text);
    
}
