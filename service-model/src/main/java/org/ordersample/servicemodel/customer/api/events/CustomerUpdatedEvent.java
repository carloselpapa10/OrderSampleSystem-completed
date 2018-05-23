package org.ordersample.servicemodel.customer.api.events;

import org.ordersample.servicemodel.customer.api.info.CustomerInfo;

public class CustomerUpdatedEvent implements CustomerDomainEvent{

	CustomerInfo customerInfo; 
	
	public CustomerUpdatedEvent() {}

	public CustomerUpdatedEvent(CustomerInfo customerInfo) {
		super();
		this.customerInfo = customerInfo;
	}

	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	
}
