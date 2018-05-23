package org.ordersample.invoiceservice.impl;

import org.ordersample.servicemodel.common.BusinessException;
import org.ordersample.servicemodel.invoice.api.events.*;
import org.ordersample.servicemodel.invoice.api.info.*;
import org.ordersample.invoiceservice.dao.InvoiceService;
import org.ordersample.invoiceservice.model.*;
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
public class InvoiceServiceImpl implements InvoiceService{

	private static final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private InvoiceDomainEventPublisher invoiceAggregateEventPublisher;

	@Override
	public Invoice createInvoice(Invoice invoice) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("createInvoice(Invoice invoice) - InvoiceServiceImpl - InvoiceService");
		
		invoice = invoiceRepository.save(invoice);
				
		List<InvoiceDomainEvent> events = singletonList(new InvoiceCreatedEvent(new InvoiceInfo(invoice.getId(), invoice.getOrderId(), invoice.getInvoiceComment())));
		ResultWithDomainEvents<Invoice, InvoiceDomainEvent> invoiceAndEvents = new ResultWithDomainEvents<>(invoice, events);
		
		invoiceAggregateEventPublisher.publish(invoice, invoiceAndEvents.events);

		return invoice;
	}
				
	@Override
	public void rejectInvoice(Invoice invoice) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("rejectInvoice(Invoice invoice) - InvoiceServiceImpl - InvoiceService");
		
		List<InvoiceDomainEvent> events = singletonList(new InvoiceCreationFailedEvent(new InvoiceInfo(invoice.getId(), invoice.getOrderId(), invoice.getInvoiceComment())));
		ResultWithDomainEvents<Invoice, InvoiceDomainEvent> invoiceAndEvents = new ResultWithDomainEvents<>(invoice, events);
		
		invoiceRepository.delete(invoice);
		invoiceAggregateEventPublisher.publish(invoice, invoiceAndEvents.events);
		
	}
			
	@Override
	public Invoice findInvoice(String id) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("findInvoice(String id) - InvoiceServiceImpl - InvoiceService");
		return invoiceRepository.findOne(id);
	}
			
	@Override
	public List<Invoice> findAll() throws BusinessException{
		log.info("findAll() - InvoiceServiceImpl - InvoiceService");
		return invoiceRepository.findAll();
	}
	
}
