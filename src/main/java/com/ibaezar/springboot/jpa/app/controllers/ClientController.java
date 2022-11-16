package com.ibaezar.springboot.jpa.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibaezar.springboot.jpa.app.models.entities.Client;
import com.ibaezar.springboot.jpa.app.services.IClientService;
import com.ibaezar.springboot.jpa.app.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
@RequestMapping("/clientes")
public class ClientController {
	
	@Autowired
	private IClientService clienteService;
	
	@GetMapping("/listar")
	public String getAll(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Client> clients = clienteService.findAll(pageRequest);

		PageRender<Client> pageRender = new PageRender<>("/clientes/listar", clients);

		model.addAttribute("title", "Listado de clientes");
		model.addAttribute("subTitle", "Clientes encontrados");
		model.addAttribute("link", "listar");
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);
		return "clients/toList";
	}
	
	@GetMapping("/form")
	public String create(Model model) {
		Client client = new Client();
		model.addAttribute("title", "Formulario de clientes");
		model.addAttribute("subTitle", "Registrar un nuevo cliente");
		model.addAttribute("link", "crear");
		model.addAttribute("client", client);
		return "clients/create";
	}
	
	@PostMapping("/form")
	public String save(@Valid Client client, BindingResult result, RedirectAttributes msg, Model model, SessionStatus sStatus) {
		if(result.hasErrors()) {
			model.addAttribute("title", "Formulario de clientes");
			model.addAttribute("subTitle", "Registrar un nuevo cliente");
			return "clients/create";
		}
		clienteService.save(client);
		msg.addFlashAttribute("success", "El cliente ha sido guardado correctamente");
		sStatus.setComplete();
		return "redirect:/clientes/listar";
	}
	
	@GetMapping("/form/{id}")
	public String update(@PathVariable(value="id") Long id, RedirectAttributes msg, Model model) {
		model.addAttribute("title", "Formulario de clientes");
		model.addAttribute("subTitle", "Editar datos del cliente");
		Client client = null;
		if(id > 0) {
			client = clienteService.getById(id);
			if(client == null) {
				msg.addFlashAttribute("error", "El cliente no existe en la base de datos");
				return "redirect:/clientes/listar";
			}
		}else {
			msg.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/clientes/listar";
		}
		model.addAttribute("client", client);
		return "clients/create";
	}
	
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable(value="id") Long id, RedirectAttributes msg, Model model) {
		if(id > 0) {
			clienteService.delete(id);
			msg.addFlashAttribute("success", "El cliente ha sido eliminado correctamente");
		}
		return "redirect:/clientes/listar";
	}
}
