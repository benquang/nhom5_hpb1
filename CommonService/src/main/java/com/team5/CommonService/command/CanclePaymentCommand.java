package com.team5.CommonService.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class CanclePaymentCommand {

	@TargetAggregateIdentifier
	private String paymentid;
	private String orderid;
	private String paymentstatus = "CANCELLED";
	
	public CanclePaymentCommand() {}
	

	public CanclePaymentCommand(String paymentid, String orderid) {
		this.paymentid = paymentid;
		this.orderid = orderid;
		
	}
}
