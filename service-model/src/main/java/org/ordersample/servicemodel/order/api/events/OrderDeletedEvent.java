package org.ordersample.servicemodel.order.api.events;

import org.ordersample.servicemodel.order.api.info.OrderInfo;

public class OrderDeletedEvent implements OrderDomainEvent{

	private OrderInfo orderInfo;

	public OrderDeletedEvent() {}
	
	public OrderDeletedEvent(OrderInfo orderInfo) {
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
