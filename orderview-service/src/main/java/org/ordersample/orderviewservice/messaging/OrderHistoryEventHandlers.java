package org.ordersample.orderviewservice.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ordersample.orderviewservice.impl.*;
import org.ordersample.orderviewservice.model.*;
import org.ordersample.servicemodel.order.api.events.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;	

@Component
public class OrderHistoryEventHandlers {
	
	private static final Logger log = LoggerFactory.getLogger(OrderHistoryEventHandlers.class);

	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;
	
	public DomainEventHandlers domainEventHandlers() {
		return DomainEventHandlersBuilder
				.forAggregateType("org.ordersample.orderservice.model.Order")
				.onEvent(OrderCompletedEvent.class, this::handleOrderCompletedEvent)
				.onEvent(OrderCreatedEvent.class, this::handleOrderCreatedEvent)
				.onEvent(OrderRejectedEvent.class, this::handleOrderRejectedEvent)
				.onEvent(OrderEditedEvent.class, this::handleOrderEditedEvent)
				.onEvent(OrderUpdatedInvoiceEvent.class, this::handlerOrderUpdatedInvoiceEvent)
				.onEvent(OrderDeletedEvent.class, this::handleOrderDeletedEvent)
				.build();
	}

	private void handleOrderCompletedEvent(DomainEventEnvelope<OrderCompletedEvent> dee) {
		log.info("handleOrderCompletedEvent() - OrderHistoryEventHandlers - OrderService");
		Order order = orderServiceImpl.findOrder(dee.getAggregateId());
		orderServiceImpl.completeOrder(order);
	}

	private void handleOrderCreatedEvent(DomainEventEnvelope<OrderCreatedEvent> dee) {
		log.info("handleOrderCreatedEvent() - OrderHistoryEventHandlers - OrderService");
		
		Customer customer = customerServiceImpl.findCustomer(dee.getEvent().getOrderInfo().getCustomerid());
		orderServiceImpl.createOrder(new Order(dee.getAggregateId(), dee.getEvent().getOrderInfo().getDescription(), customer));
	}

	private void handleOrderRejectedEvent(DomainEventEnvelope<OrderRejectedEvent> dee) {
		log.info("handleOrderRejectedEvent() - OrderHistoryEventHandlers - OrderService");
		orderServiceImpl.rejectOrder(dee.getAggregateId());
	}

	private void handleOrderEditedEvent(DomainEventEnvelope<OrderEditedEvent> dee) {
		log.info("handleOrderEditedEvent() - OrderHistoryEventHandlers - OrderService");
		
		Customer customer = customerServiceImpl.findCustomer(dee.getEvent().getOrderInfo().getCustomerid());
		Invoice invoice = invoiceServiceImpl.findInvoice(dee.getEvent().getOrderInfo().getInvoiceid());
		Order order = new Order(dee.getAggregateId(), dee.getEvent().getOrderInfo().getDescription(), customer, invoice);
		
		orderServiceImpl.editOrder(order);
	}
	
	private void handlerOrderUpdatedInvoiceEvent(DomainEventEnvelope<OrderUpdatedInvoiceEvent> dee) {
		log.info("handlerOrderUpdatedInvoiceEvent() - OrderHistoryEventHandlers - OrderService");
		
		Invoice invoice = invoiceServiceImpl.findInvoice(dee.getEvent().getOrderInfo().getInvoiceid());
		Order order = orderServiceImpl.findOrder(dee.getAggregateId());
		
		orderServiceImpl.updateInvoiceOrder(order, invoice);
	}
	
	private void handleOrderDeletedEvent(DomainEventEnvelope<OrderDeletedEvent> dee) {
		log.info("handleOrderDeletedEvent() - OrderHistoryEventHandlers - OrderService");
		
		Order order = orderServiceImpl.findOrder(dee.getAggregateId());
		orderServiceImpl.deleteOrder(order);
	}

}
