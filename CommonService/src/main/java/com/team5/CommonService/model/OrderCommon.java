package com.team5.CommonService.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCommon {

	private String orderid;
	private String user;
	private List<LineItems> lineitems;
	
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
	public List<LineItems> getLineitems() {
		return lineitems;
	}
	public void setLineitems(List<LineItems> lineitems) {
		this.lineitems = lineitems;
	}
	
	
	
}
 