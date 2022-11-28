package com.ibaezar.springboot.jpa.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibaezar.springboot.jpa.app.models.entities.Client;
import com.ibaezar.springboot.jpa.app.models.entities.Invoice;
import com.ibaezar.springboot.jpa.app.models.entities.InvoiceItem;
import com.ibaezar.springboot.jpa.app.models.entities.Product;
import com.ibaezar.springboot.jpa.app.services.IClientService;

@Controller
@RequestMapping("/facturas")
@SessionAttributes("invoice")
public class InvoiceController {

    @Autowired
    private IClientService clientService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/detalle/{id}")
    public String detail(@PathVariable(value = "id") Long id, Model model, RedirectAttributes msg){

        //? Consulta usando fetch(inner join) para optimizar las consultas a la base de datos.
        //Invoice invoice = clientService.findInvoiceById(id);

        //? Consulta usando fetch(inner join) para optimizar las consultas a la base de datos.
        Invoice invoice = clientService.fetchByIdWithClientWithInvoiceItemsWithProduct(id);

        if(invoice == null){
            msg.addFlashAttribute("error", "La factura no existe en la base de datos");
            return "redirect:/clientes/listar";
        }

        model.addAttribute("invoice", invoice);
        model.addAttribute("title", "Facturas");
		model.addAttribute("subTitle", "Detalle de factura: ".concat(invoice.getDescription()));

        return "invoices/detail";
    }
    
    @GetMapping("/form/{clientId}")
    public String create(@PathVariable(value = "clientId") Long clientId, 
        Map<String, Object> model, 
        RedirectAttributes msg){

        Client client = clientService.getById(clientId);

        if(client == null){
            msg.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/clientes/listar";
        }

        Invoice invoice = new Invoice();
        invoice.setClient(client);

        model.put("invoice", invoice);
        model.put("title", "Registrar factura");
		model.put("subTitle", "Registrar una nueva factura para el cliente");

        return "invoices/form";
    }

    @GetMapping(value = "/cargar-productos/{text}", produces = {"application/json"})
    public @ResponseBody List<Product> loadProducts(@PathVariable String text){
        return clientService.findByName(text);
    }

    @PostMapping("/form")
    public String save(@Valid Invoice invoice,
        BindingResult result,
        Model model,
        @RequestParam(name = "item_id[]", required = false) Long[] itemId,
        @RequestParam(name = "quantity[]", required = false) Integer[] quantity,
        RedirectAttributes msg,
        SessionStatus sStatus){

        if(result.hasErrors()){
            model.addAttribute("title", "Registrar factura");
		    model.addAttribute("subTitle", "Registrar una nueva factura para el cliente");
            return "invoices/form";
        }

        if(itemId == null || itemId.length == 0){
            model.addAttribute("title", "Registrar factura");
		    model.addAttribute("subTitle", "Registrar una nueva factura para el cliente");
            model.addAttribute("error", "Las lineas de factura no pueden estár vacías");
            return "invoices/form";
        }
        
        for (int i = 0; i < itemId.length; i++) {
            Product product = clientService.findProductById(itemId[i]);

            InvoiceItem line = new InvoiceItem();
            line.setQuantity(quantity[i]);
            line.setProduct(product);
            invoice.addItems(line);

            log.info("ID: "+ itemId[i].toString() + " cantidad: " + quantity[i].toString());
        }

        clientService.saveInvoice(invoice);
        sStatus.setComplete();
        msg.addFlashAttribute("success", "Factura creada con éxito");
        return "redirect:/clientes/detalle/" + invoice.getClient().getId();
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable(value = "id") Long id, RedirectAttributes msg){

        Invoice invoice = clientService.findInvoiceById(id);

        if(invoice != null){
            clientService.deleteInvoice(id);
            msg.addFlashAttribute("success", "Factura eliminada con éxito");
            return "redirect:/clientes/detalle/" + invoice.getClient().getId();
        }else{
            msg.addFlashAttribute("error", "Factura no existe en la base de datos");
            return "redirect:/clientes/listar/";
        }
    }
}
