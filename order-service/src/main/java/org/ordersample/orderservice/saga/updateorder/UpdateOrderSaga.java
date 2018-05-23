package org.ordersample.orderservice.saga.updateorder;

import org.ordersample.orderservice.proxy.*;
import org.ordersample.servicemodel.customer.api.commands.*;	
import org.ordersample.servicemodel.customer.api.info.*;
import org.ordersample.servicemodel.order.api.commands.*;	
import org.ordersample.servicemodel.order.api.info.*;
import org.ordersample.servicemodel.invoice.api.commands.*;	
import org.ordersample.servicemodel.invoice.api.info.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;

@Component
public class UpdateOrderSaga implements SimpleSaga<UpdateOrderSagaData>{
	
	private static final Logger log = LoggerFactory.getLogger(UpdateOrderSaga.class);

	private SagaDefinition<UpdateOrderSagaData> sagaDefinition;
	
	public UpdateOrderSaga(CustomerServiceProxy customerService, OrderServiceProxy orderService, InvoiceServiceProxy invoiceService){
		
		this.sagaDefinition =
				step()					
					.invokeParticipant(customerService.validateCustomerByOrderService, this::makeValidateCustomerByOrderService)
				.step()
					.invokeParticipant(invoiceService.validateInvoiceByOrderService, this::makeValidateInvoiceByOrderService)
				.step()
					.invokeParticipant(orderService.editOrderCommand, this::makeEditOrderCommand)
				.build();
	}

	@Override
	public SagaDefinition<UpdateOrderSagaData> getSagaDefinition() {
		return sagaDefinition;
	}

	private ValidateCustomerByOrderService makeValidateCustomerByOrderService(UpdateOrderSagaData data) {
		log.info("makeValidateCustomerByOrderService() - UpdateOrderSaga - OrderService"); 
		return new ValidateCustomerByOrderService(new CustomerInfo(data.getCustomerid()));
	}

	private ValidateInvoiceByOrderService makeValidateInvoiceByOrderService(UpdateOrderSagaData data) {
		log.info("makeValidateInvoiceByOrderService() - UpdateOrderSaga - OrderService"); 
		return new ValidateInvoiceByOrderService(new InvoiceInfo(data.getInvoiceid(), data.getId(), ""));
	}

	private EditOrderCommand makeEditOrderCommand(UpdateOrderSagaData data) {
		log.info("makeEditOrderCommand() - UpdateOrderSaga - OrderService"); 
		return new EditOrderCommand(new OrderInfo(data.getId(), data.getDescription(), data.getCustomerid(), data.getInvoiceid()));
	}

}
