package org.ordersample.orderviewservice.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ordersample.orderviewservice.impl.InvoiceServiceImpl;
import org.ordersample.orderviewservice.model.Invoice;
import org.ordersample.servicemodel.invoice.api.events.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;	

@Component
public class InvoiceHistoryEventHandlers {
	
	private static final Logger log = LoggerFactory.getLogger(InvoiceHistoryEventHandlers.class);

	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;
	
	public DomainEventHandlers domainEventHandlers() {
		return DomainEventHandlersBuilder
				.forAggregateType("org.ordersample.invoiceservice.model.Invoice")
				.onEvent(InvoiceCreatedEvent.class, this::handleInvoiceCreatedEvent)
				.onEvent(InvoiceCreationFailedEvent.class, this::handleInvoiceCreationFailedEvent)
				.build();
	}

	private void handleInvoiceCreatedEvent(DomainEventEnvelope<InvoiceCreatedEvent> dee) {
		log.info("handleInvoiceCreatedEvent() - InvoiceHistoryEventHandlers - InvoiceService");		
		invoiceServiceImpl.createInvoice(new Invoice(dee.getAggregateId(), dee.getEvent().getInvoiceInfo().getOrderid(), dee.getEvent().getInvoiceInfo().getInvoicecomment()));
	}

	private void handleInvoiceCreationFailedEvent(DomainEventEnvelope<InvoiceCreationFailedEvent> dee) {
		log.info("handleInvoiceCreationFailedEvent() - InvoiceHistoryEventHandlers - InvoiceService");
		invoiceServiceImpl.rejectInvoice(dee.getAggregateId());
	}

}
