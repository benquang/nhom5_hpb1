package com.team5.CommonService.events;

import java.util.List;

import com.team5.CommonService.model.LineItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditCartEvent {

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
	
	
	
}
