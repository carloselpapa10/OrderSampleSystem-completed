package org.ordersample.servicemodel.order.api.events;

import org.ordersample.servicemodel.order.api.info.OrderInfo;

public class OrderCompletedEvent implements OrderDomainEvent{

	private OrderInfo orderInfo;
	
	public OrderCompletedEvent() {}

	public OrderCompletedEvent(OrderInfo orderInfo) {
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
