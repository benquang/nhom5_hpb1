package com.team5.CommonService.events;

import lombok.Data;

@Data
public class OrderCancelledEvent {
	private String orderid;
	private String orderstatus;
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	
	
}
