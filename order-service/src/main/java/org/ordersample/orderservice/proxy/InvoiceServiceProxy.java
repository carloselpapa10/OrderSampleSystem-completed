package org.ordersample.orderservice.proxy;

import org.springframework.stereotype.Component;
import io.eventuate.tram.sagas.simpledsl.CommandEndpoint;
import io.eventuate.tram.sagas.simpledsl.CommandEndpointBuilder;
import org.ordersample.servicemodel.common.Channels;
import io.eventuate.tram.commands.common.Success;
import org.ordersample.servicemodel.invoice.api.commands.*;
import org.ordersample.servicemodel.invoice.api.info.*;

@Component
public class InvoiceServiceProxy {

	public final CommandEndpoint<RequestInvoiceCommand> requestInvoiceCommand = CommandEndpointBuilder
								.forCommand(RequestInvoiceCommand.class)
								.withChannel(Channels.INVOICESERVICE)
								.withReply(InvoiceInfo.class)
								.build();								

	public final CommandEndpoint<CompensateInvoiceCommand> compensateInvoiceCommand = CommandEndpointBuilder
								.forCommand(CompensateInvoiceCommand.class)
								.withChannel(Channels.INVOICESERVICE)
								.withReply(Success.class)
								.build();			

	public final CommandEndpoint<ValidateInvoiceByOrderService> validateInvoiceByOrderService = CommandEndpointBuilder
								.forCommand(ValidateInvoiceByOrderService.class)
								.withChannel(Channels.INVOICESERVICE)
								.withReply(Success.class)
								.build();			

}					
