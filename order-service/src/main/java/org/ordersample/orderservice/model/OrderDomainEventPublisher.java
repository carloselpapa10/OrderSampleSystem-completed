package org.ordersample.orderservice.model;

import org.springframework.stereotype.Component;
import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.ordersample.servicemodel.order.api.events.OrderDomainEvent;

@Component
public class OrderDomainEventPublisher extends AbstractAggregateDomainEventPublisher<Order,OrderDomainEvent>{

	public OrderDomainEventPublisher(DomainEventPublisher eventPublisher) {
		super(eventPublisher, Order.class, Order::getId);
    }
}
