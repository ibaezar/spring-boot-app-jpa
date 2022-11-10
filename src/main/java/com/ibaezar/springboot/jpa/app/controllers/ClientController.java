package com.ibaezar.springboot.jpa.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ibaezar.springboot.jpa.app.models.entities.Client;
import com.ibaezar.springboot.jpa.app.services.IClientService;

@Controller
@SessionAttributes("client")
@RequestMapping("/clientes")
public class ClientController {
	
	@Autowired
	private IClientService clienteService;
	
	@GetMapping("/listar")
	public String getAll(Model model) {
		model.addAttribute("title", "Listado de clientes");
		model.addAttribute("subTitle", "Clientes encontrados");
		model.addAttribute("clients", clienteService.getAll());
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
	public String save(@Valid Client client, BindingResult result, Model model, SessionStatus sStatus) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Formulario de clientes");
			model.addAttribute("subTitle", "Registrar un nuevo cliente");
			return "clients/create";
		}
		clienteService.save(client);
		sStatus.setComplete();
		return "redirect:/clientes/listar";
	}
	
	@GetMapping("/form/{id}")
	public String update(@PathVariable(value="id") Long id, Model model) {
		model.addAttribute("title", "Formulario de clientes");
		model.addAttribute("subTitle", "Editar datos del cliente");
		Client client = null;
		if(id > 0) {
			client = clienteService.getById(id);
			model.addAttribute("client", client);
		}else {
			return "redirect:/clientes/listar";
		}
		return "clients/create";
	}
	
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable(value="id") Long id, Model model) {
		if(id > 0) {
			clienteService.delete(id);
		}
		return "redirect:/clientes/listar";
	}
}
