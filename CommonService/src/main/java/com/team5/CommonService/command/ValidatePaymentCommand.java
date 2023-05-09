package com.team5.CommonService.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
public class ValidatePaymentCommand {

	@TargetAggregateIdentifier
	private String paymentid;
	private String orderid;
	private Double total;
	private String user;
	
	//

	public String getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
	
}
