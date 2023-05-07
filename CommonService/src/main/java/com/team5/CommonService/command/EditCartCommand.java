package com.team5.CommonService.command;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.team5.CommonService.model.LineItems;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditCartCommand {

	@TargetAggregateIdentifier
	private String cardid;
	private String user;
	private String orderid;
	private String cardstatus;
	
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
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
	public String getCardstatus() {
		return cardstatus;
	}
	public void setCardstatus(String cardstatus) {
		this.cardstatus = cardstatus;
	}
	
	
	
	//


	
}
