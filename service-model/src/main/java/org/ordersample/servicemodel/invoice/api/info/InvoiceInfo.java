package org.ordersample.servicemodel.invoice.api.info;

import java.util.ArrayList;
import java.util.List;

public class InvoiceInfo {

	private String invoiceId; 
    private String orderid;									
    private String invoicecomment;
    
	public InvoiceInfo(){}

	public InvoiceInfo(String invoiceId, String orderid, String invoicecomment) {
		super();
		this.invoiceId = invoiceId;
		this.orderid = orderid;
		this.invoicecomment = invoicecomment;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getInvoicecomment() {
		return invoicecomment;
	}

	public void setInvoicecomment(String invoicecomment) {
		this.invoicecomment = invoicecomment;
	}
	
}			
