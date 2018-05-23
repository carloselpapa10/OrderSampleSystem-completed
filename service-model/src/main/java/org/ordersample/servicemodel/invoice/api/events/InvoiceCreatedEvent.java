package org.ordersample.servicemodel.invoice.api.events;

import org.ordersample.servicemodel.invoice.api.info.InvoiceInfo;

public class InvoiceCreatedEvent implements InvoiceDomainEvent{

	private InvoiceInfo invoiceInfo; 
	
	public InvoiceCreatedEvent() {}

	public InvoiceCreatedEvent(InvoiceInfo invoiceInfo) {
		super();
		this.invoiceInfo = invoiceInfo;
	}

	public InvoiceInfo getInvoiceInfo() {
		return invoiceInfo;
	}

	public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
		this.invoiceInfo = invoiceInfo;
	}
	
}
