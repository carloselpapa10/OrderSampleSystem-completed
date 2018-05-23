package org.ordersample.servicemodel.customer.api.info;

import java.util.ArrayList;
import java.util.List;

public class CustomerInfo {
	
	private String id; 
    private String name;

	public CustomerInfo(){}
	
	public CustomerInfo(String id) {
		super();
		this.id = id;
	}

	public CustomerInfo(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}			
