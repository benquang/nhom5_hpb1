package com.team5.OrderService.data;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.team5.CommonService.model.LineItems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order {
	@Id
	private String orderid;
	private String user;
	private String fullname;
	private String phone;
	private String email;
	private String address;
	private String ordernote;
	private List<com.team5.CommonService.model.LineItems> lineitems;
	private Double total;
	private String orderstatus;
	
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
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrdernote() {
		return ordernote;
	}
	public void setOrdernote(String ordernote) {
		this.ordernote = ordernote;
	}
	public List<LineItems> getLineitems() {
		return lineitems;
	}
	public void setLineitems(List<LineItems> lineitems) {
		this.lineitems = lineitems;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	
	
}
