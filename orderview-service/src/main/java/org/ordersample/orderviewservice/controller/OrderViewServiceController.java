package org.ordersample.orderviewservice.controller;

import org.ordersample.orderviewservice.impl.*;
import org.ordersample.orderviewservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class OrderViewServiceController {

	private static final Logger log = LoggerFactory.getLogger(OrderViewServiceController.class);

	@Autowired
	private OrderServiceImpl orderServiceImpl;
				
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
				
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;
				
	@GetMapping("/retrieve/{orderId}")
	public Order findOrder(@RequestParam String id){
		log.info("findOrder(String id) - OrderViewServiceController - OrderViewService");
		return orderServiceImpl.findOrder(id);
	}

	@GetMapping("/retrieve/Orders")
	public List<Order> findAllOrders(){
		/*Auto-Generated*/
		log.info("findAllOrders() - OrderViewServiceController - OrderViewService");
		return orderServiceImpl.findAll();
	}

	@GetMapping("/retrieve/{customerId}")
	public Customer findCustomer(@RequestParam String id){
		log.info("findCustomer(String id) - OrderViewServiceController - OrderViewService");
		return customerServiceImpl.findCustomer(id);
	}

	@GetMapping("/retrieve/Customers")
	public List<Customer> findAllCustomers(){
		/*Auto-Generated*/
		log.info("findAllCustomers() - OrderViewServiceController - OrderViewService");
		return customerServiceImpl.findAll();
	}

	@GetMapping("/retrieve/{invoiceId}")
	public Invoice findInvoice(@RequestParam String id){
		log.info("findInvoice(String id) - OrderViewServiceController - OrderViewService");
		return invoiceServiceImpl.findInvoice(id);
	}

	@GetMapping("/retrieve/Invoices")
	public List<Invoice> findAllInvoices(){
		/*Auto-Generated*/
		log.info("findAllInvoices() - OrderViewServiceController - OrderViewService");
		return invoiceServiceImpl.findAll();
	}

}
