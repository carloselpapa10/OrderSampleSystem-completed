package org.ordersample.orderservice.proxy;

import org.springframework.stereotype.Component;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;
import org.ordersample.servicemodel.common.Channels;
import io.eventuate.tram.commands.common.Success;
import org.ordersample.servicemodel.customer.api.commands.*;
import org.ordersample.servicemodel.customer.api.info.*;

@Component
public class CustomerServiceProxy {

	public final CommandEndpoint<ValidateCustomerByOrderService> validateCustomerByOrderService = CommandEndpointBuilder
								.forCommand(ValidateCustomerByOrderService.class)
								.withChannel(Channels.CUSTOMERSERVICE)
								.withReply(Success.class)
								.build();			

}					
