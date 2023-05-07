package com.team5.CartService.data;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Cart {
	@Id
	private String id;
	private String user;
	private List<LineItems> lineitems;
	private String cartstatus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public List<LineItems> getLineitems() {
		return lineitems;
	}
	public void setLineitems(List<LineItems> lineitems) {
		this.lineitems = lineitems;
	}
	public String getCartstatus() {
		return cartstatus;
	}
	public void setCartstatus(String cartstatus) {
		this.cartstatus = cartstatus;
	}
	
	
	
}
