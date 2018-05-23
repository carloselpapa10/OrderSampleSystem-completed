package org.ordersample.orderviewservice.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ordersample.orderviewservice.impl.CustomerServiceImpl;
import org.ordersample.orderviewservice.model.Customer;
import org.ordersample.servicemodel.customer.api.events.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;	

@Component
public class CustomerHistoryEventHandlers {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerHistoryEventHandlers.class);
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	public DomainEventHandlers domainEventHandlers() {
		return DomainEventHandlersBuilder
				.forAggregateType("org.ordersample.customerservice.model.Customer")
				.onEvent(CustomerCreatedEvent.class, this::handleCustomerCreatedEvent)
				.onEvent(CustomerDeletedEvent.class, this::handleCustomerDeletedEvent)
				.onEvent(CustomerUpdatedEvent.class, this::handleCustomerUpdatedEvent)
				.build();
	}

	private void handleCustomerCreatedEvent(DomainEventEnvelope<CustomerCreatedEvent> dee) {
		log.info("handleCustomerCreatedEvent() - CustomerHistoryEventHandlers - CustomerService");
		
		Customer customer = new Customer(dee.getAggregateId(), dee.getEvent().getCustomerInfo().getName());		
		customer = customerServiceImpl.createCustomer(customer);
	}

	private void handleCustomerDeletedEvent(DomainEventEnvelope<CustomerDeletedEvent> dee) {
		log.info("handleCustomerDeletedEvent() - CustomerHistoryEventHandlers - CustomerService");
		customerServiceImpl.deleteCustomer(dee.getAggregateId());
	}

	private void handleCustomerUpdatedEvent(DomainEventEnvelope<CustomerUpdatedEvent> dee) {
		log.info("handleCustomerUpdatedEvent() - CustomerHistoryEventHandlers - CustomerService");
		customerServiceImpl.updateCustomer(new Customer(dee.getAggregateId(), dee.getEvent().getCustomerInfo().getName()));
	}

}
