package org.ordersample.servicemodel.order.api.commands;

import java.util.List;

import org.ordersample.servicemodel.order.api.info.OrderInfo;

import io.eventuate.tram.commands.common.Command;

public class EditOrderCommand implements Command{

	private OrderInfo orderInfo; 
	
	public EditOrderCommand() {}

	public EditOrderCommand(OrderInfo orderInfo) {
		super();
		this.orderInfo = orderInfo;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
	
}
