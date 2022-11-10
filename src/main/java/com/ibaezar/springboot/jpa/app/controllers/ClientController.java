package com.ibaezar.springboot.jpa.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibaezar.springboot.jpa.app.models.dao.IClientDao;
import com.ibaezar.springboot.jpa.app.models.entities.Client;

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
	
	@GetMapping("/form")
	public String create(Model model) {
		Client client = new Client();
		model.addAttribute("title", "Formulario de clientes");
		model.addAttribute("subTitle", "Registrar un nuevo cliente");
		model.addAttribute("client", client);
		return "clients/create";
	}
	
	@PostMapping("/form")
	public String save(Client client) {
		clienteDao.save(client);
		return "redirect:/clientes/listar";
	}
}
