package org.ordersample.customerservice;

import org.ordersample.customerservice.commandhandlers.CustomerServiceCommandHandlersConfiguration;
import org.ordersample.customerservice.dao.CustomerServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;

@SpringBootApplication
@Import({CustomerServiceConfiguration.class,
	CustomerServiceCommandHandlersConfiguration.class,
	TramJdbcKafkaConfiguration.class})
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
}

