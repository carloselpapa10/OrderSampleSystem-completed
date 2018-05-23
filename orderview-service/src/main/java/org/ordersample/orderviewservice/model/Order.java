package org.ordersample.orderviewservice.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Orders")
public class Order{

    private String id;									
    private String description;									
    private Customer customer;									
    private Invoice invoice;
    private boolean completed;

	public Order() {}
	
	public Order(String id) {
		super();
		this.id = id;
	}

	public Order(String id, String description, Customer customer) {
		super();
		this.id = id;
		this.description = description;
		this.customer = customer;
	}

	public Order(String id, String description, Customer customer, Invoice invoice) {
		super();
		this.id = id;
		this.description = description;
		this.customer = customer;
		this.invoice = invoice;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
