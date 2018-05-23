package org.ordersample.servicemodel.invoice.api.events;

import org.ordersample.servicemodel.invoice.api.info.InvoiceInfo;

public class InvoiceCreationFailedEvent implements InvoiceDomainEvent{

	private InvoiceInfo invoiceInfo; 
	
	public InvoiceCreationFailedEvent() {}	

	public InvoiceCreationFailedEvent(InvoiceInfo invoiceInfo) {
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
