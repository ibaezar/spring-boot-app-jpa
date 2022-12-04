package com.ibaezar.springboot.jpa.app.controllers;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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

    @Autowired
    private MessageSource messageSource;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/detalle/{id}")
    public String detail(@PathVariable(value = "id") Long id, Model model, RedirectAttributes msg, Locale locale){

        //? Consulta usando fetch(inner join) para optimizar las consultas a la base de datos.
        //Invoice invoice = clientService.findInvoiceById(id);

        //? Consulta usando fetch(inner join) para optimizar las consultas a la base de datos.
        Invoice invoice = clientService.fetchByIdWithClientWithInvoiceItemsWithProduct(id);

        if(invoice == null){
            msg.addFlashAttribute("error", messageSource.getMessage("text.invoice.controller.error.noInvoice",null, locale));
            return "redirect:/clientes/listar";
        }

        model.addAttribute("invoice", invoice);
        model.addAttribute("title", messageSource.getMessage("text.invoice.controller.invoices",null, locale));
		model.addAttribute("subTitle", messageSource.getMessage("text.invoice.controller.invoiceDetail",null, locale).concat(": ").concat(invoice.getDescription()));

        return "invoices/detail";
    }
    
    @GetMapping("/form/{clientId}")
    public String create(@PathVariable(value = "clientId") Long clientId, 
        Map<String, Object> model, 
        RedirectAttributes msg,
        Locale locale){

        Client client = clientService.getById(clientId);

        if(client == null){
            msg.addFlashAttribute("error", messageSource.getMessage("text.invoice.controller.error.noInvoice",null, locale));
            return "redirect:/clientes/listar";
        }

        Invoice invoice = new Invoice();
        invoice.setClient(client);

        model.put("invoice", invoice);
        model.put("title", messageSource.getMessage("text.invoice.controller.registerInvoice",null, locale));
		model.put("subTitle", messageSource.getMessage("text.invoice.controller.registerNewInvoice",null, locale));

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
        SessionStatus sStatus,
        Locale locale){

        if(result.hasErrors()){
            model.addAttribute("title", messageSource.getMessage("text.invoice.controller.registerInvoice",null, locale));
		    model.addAttribute("subTitle", messageSource.getMessage("text.invoice.controller.registerNewInvoice",null, locale));
            return "invoices/form";
        }

        if(itemId == null || itemId.length == 0){
            model.addAttribute("title", messageSource.getMessage("text.invoice.controller.registerInvoice",null, locale));
		    model.addAttribute("subTitle", messageSource.getMessage("text.invoice.controller.registerNewInvoice",null, locale));
            model.addAttribute("error", messageSource.getMessage("text.invoice.controller.cannotBeEmpty",null, locale));
            return "invoices/form";
        }
        
        for (int i = 0; i < itemId.length; i++) {
            Product product = clientService.findProductById(itemId[i]);

            InvoiceItem line = new InvoiceItem();
            line.setQuantity(quantity[i]);
            line.setProduct(product);
            invoice.addItems(line);

            log.info("ID: "+ itemId[i].toString() + " " + messageSource.getMessage("text.invoice.controller.quantity",null, locale) + ": " + quantity[i].toString());
        }

        clientService.saveInvoice(invoice);
        sStatus.setComplete();
        msg.addFlashAttribute("success", messageSource.getMessage("text.invoice.controller.success.registerInvoice",null, locale));
        return "redirect:/clientes/detalle/" + invoice.getClient().getId();
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable(value = "id") Long id, RedirectAttributes msg, Locale locale){

        Invoice invoice = clientService.findInvoiceById(id);

        if(invoice != null){
            clientService.deleteInvoice(id);
            msg.addFlashAttribute("success", messageSource.getMessage("text.invoice.controller.success.deletedInvoice",null, locale));
            return "redirect:/clientes/detalle/" + invoice.getClient().getId();
        }else{
            msg.addFlashAttribute("error", messageSource.getMessage("text.invoice.controller.error.noInvoice",null, locale));
            return "redirect:/clientes/listar/";
        }
    }
}
