package org.ordersample.customerservice.controller;

import org.ordersample.customerservice.impl.*;
import org.ordersample.customerservice.model.*;
import org.ordersample.customerservice.webapi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CustomerServiceController {

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceController.class);

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@PostMapping("/customer")
	public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
		log.info("createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) - CustomerServiceController - CustomerService");
		
		if(customerServiceImpl.findCustomer(createCustomerRequest.getId()) == null) {
			Customer customer = customerServiceImpl.createCustomer(new Customer(createCustomerRequest.getId(), createCustomerRequest.getName()));
			return new CreateCustomerResponse(customer.getId());
		}
		
		return null;
	}
			
	@GetMapping("/retrieve/{customerId}")
	public Customer findCustomer(@RequestParam String id){
		log.info("findCustomer(String id) - CustomerServiceController - CustomerService");		
		return customerServiceImpl.findCustomer(id);
	} 			

	@PutMapping("/update/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
		log.info("updateCustomer(@RequestBody Customer customer) - CustomerServiceController - CustomerService");

		if(customerServiceImpl.findCustomer(customer.getId()) != null) {
			customerServiceImpl.updateCustomer(customer);
			return ResponseEntity.ok(customer);
		}		
		return null;
	}
 			
	@DeleteMapping("/delete/{customerId}")
	public String deleteCustomer(@RequestParam String id){
		log.info("deleteCustomer(String id) - CustomerServiceController - CustomerService");

		Customer customer = customerServiceImpl.findCustomer(id);
		if(customer != null) {
			customerServiceImpl.deleteCustomer(customer);
			return "Customer is being deleted...";
		}
		return "Id customer does not exist!";		
	} 
			
	@GetMapping("/retrieve/Customers")
	public List<Customer> findAllCustomers(){
		/*Auto-Generated*/
		log.info("findAll() - CustomerServiceController - CustomerService");
		return customerServiceImpl.findAll();
	}

}


