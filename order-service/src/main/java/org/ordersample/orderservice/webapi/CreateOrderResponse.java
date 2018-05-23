package org.ordersample.orderservice.webapi;

public class CreateOrderResponse {
	
	private String id;
	
	public CreateOrderResponse() {}

	public CreateOrderResponse(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
