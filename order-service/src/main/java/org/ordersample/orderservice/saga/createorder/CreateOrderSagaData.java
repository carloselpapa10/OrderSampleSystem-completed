package org.ordersample.orderservice.saga.createorder;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderSagaData {

	private String id; 
    private String customerid;									
    private String invoiceid;
    
	public CreateOrderSagaData() {}

	public CreateOrderSagaData(String id, String customerid, String invoiceid) {
		super();
		this.id = id;
		this.customerid = customerid;
		this.invoiceid = invoiceid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(String invoiceid) {
		this.invoiceid = invoiceid;
	}
	
}
