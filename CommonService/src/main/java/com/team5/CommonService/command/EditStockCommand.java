package com.team5.CommonService.command;


import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.team5.CommonService.model.LineItems;

public class EditStockCommand {

	@TargetAggregateIdentifier
	private List<LineItems> lineitems;
	private String orderid;
	
	public List<LineItems> getLineitems() {
		return lineitems;
	}
	public void setLineitems(List<LineItems> lineitems) {
		this.lineitems = lineitems;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	
	
	

	
	
	
	
	
	//


	
}
