package com.team5.CommonService.command;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.team5.CommonService.model.LineItems;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartCancelCommand {

	@TargetAggregateIdentifier
	private String cart;
	private String orderid;
	private String cardstatus;
	private String userid;
	
	//
	private String user;
	private String paymentid;
	private String shipmentid;
	
	public String getCart() {
		return cart;
	}
	public void setCart(String cart) {
		this.cart = cart;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getCardstatus() {
		return cardstatus;
	}
	public void setCardstatus(String cardstatus) {
		this.cardstatus = cardstatus;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	
	
	
	
	
	
		
}
