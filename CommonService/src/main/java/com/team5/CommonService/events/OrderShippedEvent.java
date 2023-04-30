package com.team5.CommonService.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderShippedEvent {

	private String shipmentid;
	private String orderid;
	private String shipmentstatus;
	
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
	public String getShipmentstatus() {
		return shipmentstatus;
	}
	public void setShipmentstatus(String shipmentstatus) {
		this.shipmentstatus = shipmentstatus;
	}
	
	
}
