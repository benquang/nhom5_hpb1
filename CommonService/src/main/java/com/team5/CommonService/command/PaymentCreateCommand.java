package com.team5.CommonService.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.team5.CommonService.model.CardDetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentCreateCommand {

	@TargetAggregateIdentifier
	private String paymentid;
	private String orderid;
	private String paymentstatus;
	
	//
	private String user;

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

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	
	
	
	
}
