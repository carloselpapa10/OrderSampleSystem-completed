package org.ordersample.orderviewservice.impl;

import org.ordersample.servicemodel.common.BusinessException;
import org.ordersample.servicemodel.customer.api.events.*;
import org.ordersample.servicemodel.customer.api.info.*;
import org.ordersample.orderviewservice.dao.CustomerService;
import org.ordersample.orderviewservice.model.*;
import org.ordersample.orderviewservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.util.Collections.singletonList;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;

@Component
@Transactional
public class CustomerServiceImpl implements CustomerService{

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("createCustomer(Customer customer) - CustomerServiceImpl - OrderViewService");
		return customerRepository.save(customer);
	}
				
	@Override
	public Customer findCustomer(String id) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("findCustomer(String id) - CustomerServiceImpl - OrderViewService");
		return customerRepository.findOne(id);
	}
			
	@Override
	public void updateCustomer(Customer customer) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("updateCustomer(Customer customer) - CustomerServiceImpl - OrderViewService");
		customerRepository.save(customer);
	}
			
	@Override
	public void deleteCustomer(String id) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("deleteCustomer(String id) - CustomerServiceImpl - OrderViewService");
		Customer customer = findCustomer(id);
		customerRepository.delete(customer);
	}
			
	@Override
	public List<Customer> findAll() throws BusinessException{
		log.info("findAll() - CustomerServiceImpl - OrderViewService");
		return customerRepository.findAll();
	}

}
