package com.team5.CommonService.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.team5.CommonService.model.CardDetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidatePaymentCommand {

	@TargetAggregateIdentifier
	private String paymentid;
	private String orderid;
	private CardDetails cartdetails;
	
	public String getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public CardDetails getCartdetails() {
		return cartdetails;
	}
	public void setCartdetails(CardDetails cartdetails) {
		this.cartdetails = cartdetails;
	}
	
	
}
