package org.ordersample.customerservice.impl;

import io.micrometer.core.instrument.MeterRegistry;
import org.ordersample.servicemodel.common.BusinessException;
import org.ordersample.servicemodel.customer.api.events.*;
import org.ordersample.servicemodel.customer.api.info.*;
import org.ordersample.customerservice.dao.CustomerService;
import org.ordersample.customerservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.util.Collections.singletonList;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;

@Component
@Transactional
public class CustomerServiceImpl implements CustomerService{

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

	private final CustomerRepository customerRepository;
	
	private final CustomerDomainEventPublisher customerAggregateEventPublisher;

	private Optional<MeterRegistry> meterRegistry;

	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerDomainEventPublisher customerAggregateEventPublisher, Optional<MeterRegistry> meterRegistry) {
		this.customerRepository = customerRepository;
		this.customerAggregateEventPublisher = customerAggregateEventPublisher;
		this.meterRegistry = meterRegistry;
	}

	@Override
	public Customer createCustomer(Customer customer) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("createCustomer(Customer customer) - CustomerServiceImpl - CustomerService");
				
		List<CustomerDomainEvent> events = singletonList(new CustomerCreatedEvent(new CustomerInfo(customer.getId(), customer.getName())));
		ResultWithDomainEvents<Customer, CustomerDomainEvent> customerAndEvents = new ResultWithDomainEvents<>(customer, events);		
		
		customer = customerRepository.save(customer);
		customerAggregateEventPublisher.publish(customer, customerAndEvents.events);

		meterRegistry.ifPresent(mr -> mr.counter("created_customers").increment());

		return customer;
	}
				
	@Override
	public Customer findCustomer(String id) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("findCustomer(String id) - CustomerServiceImpl - CustomerService");
		return customerRepository.existsById(id) ? customerRepository.findById(id).get() : null ;
	}
			
	@Override
	public void updateCustomer(Customer customer) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("updateCustomer(Customer customer) - CustomerServiceImpl - CustomerService");
				
		List<CustomerDomainEvent> events = singletonList(new CustomerUpdatedEvent(new CustomerInfo(customer.getId(), customer.getName())));
		ResultWithDomainEvents<Customer, CustomerDomainEvent> customerAndEvents = new ResultWithDomainEvents<>(customer, events);
		
		customer = customerRepository.save(customer);
		customerAggregateEventPublisher.publish(customer, customerAndEvents.events);

		meterRegistry.ifPresent(mr -> mr.counter("updated_customers").increment());
	}
			
	@Override
	public void deleteCustomer(Customer customer) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("deleteCustomer(Customer customer) - CustomerServiceImpl - CustomerService");
		
		List<CustomerDomainEvent> events = singletonList(new CustomerDeletedEvent(new CustomerInfo(customer.getId())));
		ResultWithDomainEvents<Customer, CustomerDomainEvent> customerAndEvents = new ResultWithDomainEvents<>(customer, events);
		
		customerRepository.delete(customer);
		customerAggregateEventPublisher.publish(customer, customerAndEvents.events);

		meterRegistry.ifPresent(mr -> mr.counter("deleted_customers").increment());
	}
			
	@Override
	public List<Customer> findAll() throws BusinessException{
		log.info("findAll() - CustomerServiceImpl - CustomerService");
		return customerRepository.findAll();
	}
	
}
