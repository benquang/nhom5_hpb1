package com.team5.CommonService.queries;

import java.util.List;

import com.team5.CommonService.model.LineItems;

public class GetProductQuery {
	private List<LineItems> lineitems;
	
	public List<LineItems> getLineitems() {
		return lineitems;
	}
	public void setLineitems(List<LineItems> lineitems) {
		this.lineitems = lineitems;
	}
}
