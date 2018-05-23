package org.ordersample.orderservice.impl;

import org.ordersample.servicemodel.common.BusinessException;
import org.ordersample.servicemodel.order.api.events.*;
import org.ordersample.servicemodel.order.api.info.*;
import org.ordersample.orderservice.dao.OrderService;
import org.ordersample.orderservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.util.Collections.singletonList;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;
import org.ordersample.orderservice.saga.createorder.CreateOrderSagaData;
import org.ordersample.orderservice.saga.updateorder.UpdateOrderSagaData;
import io.eventuate.tram.sagas.orchestration.SagaManager;

@Component
@Transactional
public class OrderServiceImpl implements OrderService{

	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDomainEventPublisher orderAggregateEventPublisher;

	@Autowired
	private SagaManager<CreateOrderSagaData> createOrderSagaManager;

	@Autowired
	private SagaManager<UpdateOrderSagaData> updateOrderSagaManager;

	@Override
	public Order createOrder(Order order) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("createOrder(Order order) - OrderServiceImpl - OrderService");
		
		order = orderRepository.save(order);
		
		List<OrderDomainEvent> events = singletonList(new OrderCreatedEvent(new OrderInfo(order.getId(), order.getDescription(), order.getCustomerId(), order.getInvoiceId())));
		ResultWithDomainEvents<Order, OrderDomainEvent> orderAndEvents = new ResultWithDomainEvents<>(order, events);		
		
		orderAggregateEventPublisher.publish(order, orderAndEvents.events);

		CreateOrderSagaData data = new CreateOrderSagaData(order.getId(), order.getCustomerId(), order.getInvoiceId());
		createOrderSagaManager.create(data, Order.class, order.getId());
		
		return order;
	}
				
	@Override
	public Order findOrder(String id) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("findOrder(String id) - OrderServiceImpl - OrderService");
		return orderRepository.findOne(id);
	}
			
	@Override
	public void updateOrder(Order order) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("updateOrder(Order order) - OrderServiceImpl - OrderService");

		order = orderRepository.save(order);
		
		UpdateOrderSagaData data = new UpdateOrderSagaData(order.getId(), order.getDescription(), order.getCustomerId(), order.getInvoiceId());
		updateOrderSagaManager.create(data);
		
	}
			
	@Override
	public void rejectOrder(Order order) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("rejectOrder(Order order) - OrderServiceImpl - OrderService");
		
		order.setCompleted(false);
		order = orderRepository.save(order);
		
		List<OrderDomainEvent> events = singletonList(new OrderRejectedEvent(new OrderInfo(order.getId(), order.getDescription(), order.getCustomerId(), order.getInvoiceId())));
		ResultWithDomainEvents<Order, OrderDomainEvent> orderAndEvents = new ResultWithDomainEvents<>(order, events);
		
		orderAggregateEventPublisher.publish(order, orderAndEvents.events);
		
	}
			
	@Override
	public void completeOrder(Order order) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("completeOrder(Order order) - OrderServiceImpl - OrderService");
		
		order.setCompleted(true);
		order = orderRepository.save(order);

		List<OrderDomainEvent> events = singletonList(new OrderCompletedEvent(new OrderInfo(order.getId(), order.getDescription(), order.getCustomerId(), order.getInvoiceId())));
		ResultWithDomainEvents<Order, OrderDomainEvent> orderAndEvents = new ResultWithDomainEvents<>(order, events);		
		orderAggregateEventPublisher.publish(order, orderAndEvents.events);

	}
			
	@Override
	public void editOrder(Order order) throws BusinessException{
		// TODO Auto-generated method stub
		log.info("editOrder(Order order) - OrderServiceImpl - OrderService");
		
		order = orderRepository.save(order);

		List<OrderDomainEvent> events = singletonList(new OrderEditedEvent(new OrderInfo(order.getId(), order.getDescription(), order.getCustomerId(), order.getInvoiceId())));
		ResultWithDomainEvents<Order, OrderDomainEvent> orderAndEvents = new ResultWithDomainEvents<>(order, events);		
		orderAggregateEventPublisher.publish(order, orderAndEvents.events);

	}
			
	@Override
	public List<Order> findAll() throws BusinessException{
		log.info("findAll() - OrderServiceImpl - OrderService");
		return orderRepository.findAll();
	}

	@Override
	public void updateInvoiceOrder(String orderId, String invoiceId) throws BusinessException {
		// TODO Auto-generated method stub
		log.info("updateInvoiceOrder() - OrderServiceImpl - OrderService");
		
		Order order = orderRepository.findOne(orderId);
		order.setInvoiceId(invoiceId);
		order = orderRepository.save(order);
		
		List<OrderDomainEvent> events = singletonList(new OrderUpdatedInvoiceEvent(new OrderInfo(order.getId(), order.getDescription(), order.getCustomerId(), order.getInvoiceId())));
		ResultWithDomainEvents<Order, OrderDomainEvent> orderAndEvents = new ResultWithDomainEvents<>(order, events);
		orderAggregateEventPublisher.publish(order, orderAndEvents.events);
	}

	@Override
	public void deleteOrder(Order order) throws BusinessException {
		// TODO Auto-generated method stub
		log.info("updateInvoiceOrder() - OrderServiceImpl - OrderService");
		
		List<OrderDomainEvent> events = singletonList(new OrderDeletedEvent(new OrderInfo(order.getId(), "", "", "")));
		ResultWithDomainEvents<Order, OrderDomainEvent> orderAndEvents = new ResultWithDomainEvents<>(order, events);
		
		orderRepository.delete(order);
		orderAggregateEventPublisher.publish(order, orderAndEvents.events);
	}
	
}
