package com.ibaezar.springboot.jpa.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibaezar.springboot.jpa.app.models.entities.Client;
import com.ibaezar.springboot.jpa.app.services.IClientService;
import com.ibaezar.springboot.jpa.app.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
@RequestMapping({"/clientes", "/"})
public class ClientController {
	
	@Autowired
	private IClientService clienteService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping(value="/detalle/{id}")
	public String detail(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes msg, Locale locale){
		
		//? Consulta usando fetch(inner join) para optimizar las consultas a la base de datos.
		//Client client = clienteService.getById(id);

		//? Consulta usando fetch(inner join) para optimizar las consultas a la base de datos.
		Client client = clienteService.fetchByIdWithInvoices(id);

		if(client == null){
			msg.addFlashAttribute("error", messageSource.getMessage("text.client.controller.error.noCustomer", null, locale));
			return "redirect:/clientes/listar";
		}

		model.put("client", client);
		model.put("title", messageSource.getMessage("text.client.controller.clientDetail", null, locale));
		model.put("subTitle", messageSource.getMessage("text.client.controller.client", null, locale) + " " + client.getName());
		
		return "clients/detail";
	}
	
	@GetMapping({"/listar", "/"})
	public String getAll(@RequestParam(name = "page", defaultValue = "0") int page, Model model, Locale locale) {

		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Client> clients = clienteService.findAll(pageRequest);

		PageRender<Client> pageRender = new PageRender<>("/clientes/listar", clients);

		model.addAttribute("title", messageSource.getMessage("text.client.controller.clientList", null, locale));
		model.addAttribute("subTitle", messageSource.getMessage("text.client.controller.customersFound", null, locale));
		model.addAttribute("link", messageSource.getMessage("text.client.controller.link.list", null, locale));
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);
		return "clients/toList";
	}
	
	@GetMapping("/form")
	public String create(Model model, Locale locale) {
		Client client = new Client();
		model.addAttribute("title", messageSource.getMessage("text.client.controller.clientForm", null, locale));
		model.addAttribute("subTitle", messageSource.getMessage("text.client.controller.registerCustomer", null, locale));
		model.addAttribute("link", messageSource.getMessage("text.client.controller.link.create", null, locale));
		model.addAttribute("client", client);
		return "clients/create";
	}
	
	@PostMapping("/form")
	public String save(@Valid Client client, BindingResult result, RedirectAttributes msg, Model model, @RequestParam("file") MultipartFile photo, SessionStatus sStatus, Locale locale) {
		if(result.hasErrors()) {
			model.addAttribute("title", messageSource.getMessage("text.client.controller.clientForm", null, locale));
			model.addAttribute("subTitle", messageSource.getMessage("text.client.controller.registerCustomer", null, locale));
			return "clients/create";
		}

		if(!photo.isEmpty()){
			/*
			TODO -> Esta configuración es para guardar las imagenes dentro del proyecto (NO RECOMENDADO)
			
			Path myDirectory = Paths.get("src//main//resources//static//uploads");
			String rootPath = myDirectory.toFile().getAbsolutePath();
			*/

			//TODO -> confifurar directorio externo (En el controlador y una clase config).
			String rootPath = "C://java//temp//uploads";

			//? -> En el caso de una ruta para linux sería EJ:
			//String rootPath = "/opt/java/temp/uploads";

			try {
				byte[] bytes = photo.getBytes();
				Path pathComplete = Paths.get(rootPath + "//" + photo.getOriginalFilename());
				Files.write(pathComplete, bytes);
				msg.addFlashAttribute("success", messageSource.getMessage("text.client.controller.image", null, locale));

				client.setPhoto(photo.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		clienteService.save(client);
		msg.addFlashAttribute("success", messageSource.getMessage("text.client.controller.success.registeredCustomer", null, locale));
		sStatus.setComplete();
		return "redirect:/clientes/listar";
	}
	
	@GetMapping("/form/{id}")
	public String update(@PathVariable(value="id") Long id, RedirectAttributes msg, Model model, Locale locale) {
		model.addAttribute("title", messageSource.getMessage("text.client.controller.clientForm", null, locale));
		model.addAttribute("subTitle", messageSource.getMessage("text.client.controller.updateCustomer", null, locale));
		Client client = null;
		if(id > 0) {
			client = clienteService.getById(id);
			if(client == null) {
				msg.addFlashAttribute("error", messageSource.getMessage("text.client.controller.error.noCustomer", null, locale));
				return "redirect:/clientes/listar";
			}
		}else {
			msg.addFlashAttribute("error", messageSource.getMessage("text.client.controller.error.noCustomer", null, locale));
			return "redirect:/clientes/listar";
		}
		model.addAttribute("client", client);
		return "clients/create";
	}
	
	@GetMapping("/eliminar/{id}")
	public String delete(@PathVariable(value="id") Long id, RedirectAttributes msg, Model model, Locale locale) {
		if(id > 0) {
			clienteService.delete(id);
			msg.addFlashAttribute("success", messageSource.getMessage("text.client.controller.success.deletedCustomer", null, locale));
		}
		return "redirect:/clientes/listar";
	}
}
