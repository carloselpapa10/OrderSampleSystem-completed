package org.ordersample.invoiceservice.model;

import org.springframework.stereotype.Component;
import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.ordersample.servicemodel.invoice.api.events.InvoiceDomainEvent;

@Component
public class InvoiceDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Invoice,InvoiceDomainEvent>{

	public InvoiceDomainEventPublisher(DomainEventPublisher eventPublisher) {
		super(eventPublisher, Invoice.class, Invoice::getId);
    }
}
