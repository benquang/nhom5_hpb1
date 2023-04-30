package com.team5.CommonService.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Value;

@Value
public class CancleOrderCommand {

	@TargetAggregateIdentifier
	private String orderid;
	private String orderstatus = "CANCELLED";
	
	public CancleOrderCommand() {}
	
	public CancleOrderCommand(String orderid) {
		this.orderid = orderid;
	}
}
