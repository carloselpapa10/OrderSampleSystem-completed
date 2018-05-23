package org.ordersample.invoiceservice.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Invoices")
public class Invoice{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)						
	private String id; 
    private String orderid;									
    private String invoicecomment;									
				
	public Invoice() {}
	
	public Invoice(String orderid, String invoicecomment) {
		super();
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
