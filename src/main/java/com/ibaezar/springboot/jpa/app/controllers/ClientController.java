package com.ibaezar.springboot.jpa.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibaezar.springboot.jpa.app.models.dao.IClientDao;

@Controller
@RequestMapping("/clientes")
public class ClientController {
	
	@Autowired
	private IClientDao clienteDao;
	
	@GetMapping("/listar")
	public String getAll(Model model) {
		model.addAttribute("title", "Listado de clientes");
		model.addAttribute("subTitle", "Clientes encontrados");
		model.addAttribute("clients", clienteDao.getAll());
		return "clients/toList";
	}
}
