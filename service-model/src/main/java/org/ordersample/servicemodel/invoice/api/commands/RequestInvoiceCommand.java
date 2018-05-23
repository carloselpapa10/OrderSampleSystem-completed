package org.ordersample.servicemodel.invoice.api.commands;

import java.util.List;

import org.ordersample.servicemodel.invoice.api.info.InvoiceInfo;

import io.eventuate.tram.commands.common.Command;

public class RequestInvoiceCommand implements Command{

	private InvoiceInfo invoiceInfo; 
	
	public RequestInvoiceCommand() {}

	public RequestInvoiceCommand(InvoiceInfo invoiceInfo) {
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
