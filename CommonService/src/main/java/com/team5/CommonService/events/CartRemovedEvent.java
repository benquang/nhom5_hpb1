package com.team5.CommonService.events;

import java.util.List;

import com.team5.CommonService.model.LineItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRemovedEvent {

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
