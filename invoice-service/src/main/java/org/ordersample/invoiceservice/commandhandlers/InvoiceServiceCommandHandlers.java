package org.ordersample.invoiceservice.commandhandlers;

import org.ordersample.servicemodel.common.Channels;
import org.ordersample.servicemodel.invoice.api.commands.*;
import org.ordersample.servicemodel.invoice.api.info.*;
import org.ordersample.invoiceservice.impl.*;
import org.ordersample.invoiceservice.model.*;
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

public class InvoiceServiceCommandHandlers {

	private static final Logger log = LoggerFactory.getLogger(InvoiceServiceCommandHandlers.class);

	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;

	public CommandHandlers commandHandlers() {
		return SagaCommandHandlersBuilder
				.fromChannel(Channels.INVOICESERVICE)
				.onMessage(RequestInvoiceCommand.class, this::handleRequestInvoiceCommand)
				.onMessage(CompensateInvoiceCommand.class, this::handleCompensateInvoiceCommand)
				.onMessage(ValidateInvoiceByOrderService.class, this::handleValidateInvoiceByOrderService)
				.build();
	}		
		
	private Message handleRequestInvoiceCommand(CommandMessage<RequestInvoiceCommand> cm) {
		log.info("handleRequestInvoiceCommand() - InvoiceServiceCommandHandlers - InvoiceService");
		
		RequestInvoiceCommand command = cm.getCommand();
		
		try {
			Invoice invoice = invoiceServiceImpl.createInvoice(new Invoice(command.getInvoiceInfo().getOrderid(), command.getInvoiceInfo().getInvoicecomment()));
			return withSuccess(new InvoiceInfo(invoice.getId(), invoice.getOrderId(), invoice.getInvoiceComment()));
		}catch (Exception e) {
			return withFailure();
		}
		
	}

	private Message handleCompensateInvoiceCommand(CommandMessage<CompensateInvoiceCommand> cm) {
		log.info("handleCompensateInvoiceCommand() - InvoiceServiceCommandHandlers - InvoiceService");
		
		CompensateInvoiceCommand command = cm.getCommand();
		
		try {
			invoiceServiceImpl.rejectInvoice(new Invoice(command.getInvoiceInfo().getOrderid(), command.getInvoiceInfo().getInvoicecomment()));			
			return withSuccess();			
		}catch (Exception e) {
			return withFailure();
		}
		
	}

	private Message handleValidateInvoiceByOrderService(CommandMessage<ValidateInvoiceByOrderService> cm) {
		log.info("handleValidateInvoiceByOrderService() - InvoiceServiceCommandHandlers - InvoiceService");
		
		ValidateInvoiceByOrderService command = cm.getCommand();
		
		if(invoiceServiceImpl.findInvoice(command.getInvoiceInfo().getInvoiceId()) != null) {
			return withSuccess();
		}
		
		return withFailure();
	}

}
