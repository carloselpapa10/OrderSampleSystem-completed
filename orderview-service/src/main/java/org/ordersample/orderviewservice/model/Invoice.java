package org.ordersample.orderviewservice.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Invoices")
public class Invoice{

    private String id;									
    private String orderid;									
    private String invoicecomment;									

	public Invoice() {}
	
	public Invoice(String id, String orderid, String invoicecomment) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.invoicecomment = invoicecomment;
	}

	public void setId(String id) {
		this.id = id;
	}	

	public String getId() {
		return id;
	}
	public void setOrderId(String orderid) {
		this.orderid = orderid;
	}	

	public String getOrderId() {
		return orderid;
	}
	public void setInvoiceComment(String invoicecomment) {
		this.invoicecomment = invoicecomment;
	}	

	public String getInvoiceComment() {
		return invoicecomment;
	}

}
