package org.ordersample.servicemodel.order.api.info;

import java.util.ArrayList;
import java.util.List;

public class OrderInfo {

	private String id; 
    private String description;									
    private String customerid;									
    private String invoiceid;
    
	public OrderInfo(){}

	public OrderInfo(String id, String description, String customerid, String invoiceid) {
		super();
		this.id = id;
		this.description = description;
		this.customerid = customerid;
		this.invoiceid = invoiceid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
