package org.ordersample.orderservice.saga.createorder;

import org.ordersample.orderservice.impl.OrderServiceImpl;
import org.ordersample.orderservice.proxy.*;
import org.ordersample.servicemodel.customer.api.commands.*;	
import org.ordersample.servicemodel.customer.api.info.*;
import org.ordersample.servicemodel.order.api.commands.*;	
import org.ordersample.servicemodel.order.api.info.*;
import org.ordersample.servicemodel.invoice.api.commands.*;	
import org.ordersample.servicemodel.invoice.api.info.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;

@Component
public class CreateOrderSaga implements SimpleSaga<CreateOrderSagaData>{
	
	private static final Logger log = LoggerFactory.getLogger(CreateOrderSaga.class);

	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	private SagaDefinition<CreateOrderSagaData> sagaDefinition;
	
	public CreateOrderSaga(CustomerServiceProxy customerService, OrderServiceProxy orderService, InvoiceServiceProxy invoiceService){
		
		this.sagaDefinition =
				step()					
					.withCompensation(orderService.rejectOrderCommand, this::makeRejectOrderCommand)			
				.step()
					.invokeParticipant(customerService.validateCustomerByOrderService, this::makeValidateCustomerByOrderService)
				.step()
					.invokeParticipant(invoiceService.requestInvoiceCommand, this::makeRequestInvoiceCommand)
					.onReply(InvoiceInfo.class, this::handleRequestInvoiceCommand)				
					.withCompensation(invoiceService.compensateInvoiceCommand, this::makeCompensateInvoiceCommand)			
				.step()
					.invokeParticipant(orderService.completeOrderCommand, this::makeCompleteOrderCommand)
				.build();
	}

	@Override
	public SagaDefinition<CreateOrderSagaData> getSagaDefinition() {
		return sagaDefinition;
	}

	private RejectOrderCommand makeRejectOrderCommand(CreateOrderSagaData data) {
		log.info("makeRejectOrderCommand() - CreateOrderSaga - OrderService"); 		
		return new RejectOrderCommand(new OrderInfo(data.getId(), "", "", ""));
	}

	private ValidateCustomerByOrderService makeValidateCustomerByOrderService(CreateOrderSagaData data) {
		log.info("makeValidateCustomerByOrderService() - CreateOrderSaga - OrderService");
		return new ValidateCustomerByOrderService(new CustomerInfo(data.getCustomerid()));
	}

	private RequestInvoiceCommand makeRequestInvoiceCommand(CreateOrderSagaData data) {
		log.info("makeRequestInvoiceCommand() - CreateOrderSaga - OrderService");
		 
		return new RequestInvoiceCommand(new InvoiceInfo("", data.getId(), "Invoice Comment"));
	}

	private void handleRequestInvoiceCommand(CreateOrderSagaData data, InvoiceInfo invoiceInfo) {
		log.info("handleRequestInvoiceCommand() - CreateOrderSaga - OrderService"); 
		orderServiceImpl.updateInvoiceOrder(data.getId(), invoiceInfo.getInvoiceId());
	}

	private CompensateInvoiceCommand makeCompensateInvoiceCommand(CreateOrderSagaData data) {
		log.info("makeCompensateInvoiceCommand() - CreateOrderSaga - OrderService"); 
		return new CompensateInvoiceCommand(new InvoiceInfo(data.getInvoiceid(), data.getId(), ""));
	}

	private CompleteOrderCommand makeCompleteOrderCommand(CreateOrderSagaData data) {
		log.info("makeCompleteOrderCommand() - CreateOrderSaga - OrderService"); 
		return new CompleteOrderCommand(new OrderInfo(data.getId(), "", "", ""));
	}

}
