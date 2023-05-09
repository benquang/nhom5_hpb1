package com.team5.CommonService.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;


public class ExcludingBalanceCommand {

	@TargetAggregateIdentifier
	private String id;
	private String user;
	private String orderid;
	private Double total;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	
	
}
