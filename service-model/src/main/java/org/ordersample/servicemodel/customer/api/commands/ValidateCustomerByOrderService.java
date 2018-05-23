package org.ordersample.servicemodel.customer.api.commands;

import java.util.List;

import org.ordersample.servicemodel.customer.api.info.CustomerInfo;

import io.eventuate.tram.commands.common.Command;

public class ValidateCustomerByOrderService implements Command{

	private CustomerInfo customerInfo;
	
	public ValidateCustomerByOrderService() {}

	public ValidateCustomerByOrderService(CustomerInfo customerInfo) {
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
