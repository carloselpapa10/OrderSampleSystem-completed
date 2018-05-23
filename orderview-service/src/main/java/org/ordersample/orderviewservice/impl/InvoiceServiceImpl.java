package org.ordersample.orderviewservice.impl;

import org.ordersample.servicemodel.common.BusinessException;
import org.ordersample.servicemodel.invoice.api.events.*;
import org.ordersample.servicemodel.invoice.api.info.*;
import org.ordersample.orderviewservice.dao.InvoiceService;
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
public class InvoiceServiceImpl implements InvoiceService{

	private static final Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Override
	public Invoice createInvoice(Invoice invoice) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("createInvoice(Invoice invoice) - InvoiceServiceImpl - OrderViewService");
		return invoiceRepository.save(invoice);
	}
				
	@Override
	public void rejectInvoice(String id) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("rejectInvoice(String id) - InvoiceServiceImpl - OrderViewService");
		invoiceRepository.delete(findInvoice(id));
	}
			
	@Override
	public Invoice findInvoice(String id) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("findInvoice(String id) - InvoiceServiceImpl - OrderViewService");
		return invoiceRepository.findOne(id);
	}
			
	@Override
	public List<Invoice> findAll() throws BusinessException{
		log.info("findAll() - InvoiceServiceImpl - OrderViewService");
		return invoiceRepository.findAll();
	}

}
