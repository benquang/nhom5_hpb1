package com.team5.CommonService.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class ShipOrderCommand {

	@TargetAggregateIdentifier
	private String shipmentid;
	private String orderid;
	private String user;
	
	public String getShipmentid() {
		return shipmentid;
	}
	public void setShipmentid(String shipmentid) {
		this.shipmentid = shipmentid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
