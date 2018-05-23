package org.ordersample.orderservice.webapi;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderRequest {

	private String description;
	private String customerid;

	public CreateOrderRequest() {
	}

	public CreateOrderRequest(String description, String customerid) {
		super();
		this.description = description;
		this.customerid = customerid;
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
	
}
