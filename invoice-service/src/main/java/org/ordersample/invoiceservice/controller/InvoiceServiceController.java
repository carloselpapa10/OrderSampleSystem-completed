package org.ordersample.invoiceservice.controller;

import org.ordersample.invoiceservice.impl.*;
import org.ordersample.invoiceservice.model.*;
import org.ordersample.invoiceservice.webapi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class InvoiceServiceController {

	private static final Logger log = LoggerFactory.getLogger(InvoiceServiceController.class);

	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;

	@GetMapping("/retrieve/{invoiceId}")
	public Invoice findInvoice(@RequestParam String id){
		log.info("findInvoice(String id) - InvoiceServiceController - InvoiceService");		
		return invoiceServiceImpl.findInvoice(id);
	} 			

	@GetMapping("/retrieve/Invoices")
	public List<Invoice> findAllInvoices(){
		/*Auto-Generated*/
		log.info("findAll() - InvoiceServiceController - InvoiceService");
		return invoiceServiceImpl.findAll();
	}

}


