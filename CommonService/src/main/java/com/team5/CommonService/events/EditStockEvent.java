package com.team5.CommonService.events;

import java.util.List;

import com.team5.CommonService.model.LineItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditStockEvent {

	private List<LineItems> lineitems;
	private String orderid;
	
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
}
