package com.team5.CommonService.command;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.team5.CommonService.model.LineItems;

public class ProductCancelCommand {
	
	@TargetAggregateIdentifier
	private String product;
	private List<LineItems> lineitems;
	private String orderid;
	
	//
	private String user;
	private String paymentid;
	private String shipmentid;
	private String cart;
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	public String getShipmentid() {
		return shipmentid;
	}
	public void setShipmentid(String shipmentid) {
		this.shipmentid = shipmentid;
	}
	public String getCart() {
		return cart;
	}
	public void setCart(String cart) {
		this.cart = cart;
	}
}
