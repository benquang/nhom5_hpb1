package com.team5.CommonService.events;


public class OrderShippedEvent {

	private String shipmentid;
	private String orderid;
	private String shipmentstatus;
	
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
	public String getShipmentstatus() {
		return shipmentstatus;
	}
	public void setShipmentstatus(String shipmentstatus) {
		this.shipmentstatus = shipmentstatus;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
