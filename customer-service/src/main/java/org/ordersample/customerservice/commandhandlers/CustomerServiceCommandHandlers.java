package org.ordersample.customerservice.commandhandlers;

import org.ordersample.servicemodel.common.Channels;
import org.ordersample.servicemodel.customer.api.commands.*;
import org.ordersample.servicemodel.customer.api.info.*;
import org.ordersample.customerservice.impl.*;
import org.ordersample.customerservice.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceCommandHandlers {

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceCommandHandlers.class);

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	public CommandHandlers commandHandlers() {
		return SagaCommandHandlersBuilder
				.fromChannel(Channels.CUSTOMERSERVICE)
				.onMessage(ValidateCustomerByOrderService.class, this::handleValidateCustomerByOrderService)
				.build();
	}		
		
	private Message handleValidateCustomerByOrderService(CommandMessage<ValidateCustomerByOrderService> cm) {
		log.info("handleValidateCustomerByOrderService() - CustomerServiceCommandHandlers - CustomerService");
		
		ValidateCustomerByOrderService command = cm.getCommand();
		
		if(customerServiceImpl.findCustomer(command.getCustomerInfo().getId()) != null) {
			return withSuccess();
		}else {
			return withFailure();
		}		
	}

}
