package com.team5.CommonService.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

	private String userid;
	private String password;
	private String firstname;
	private String lastname;
	private CardDetails cartdetails;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public CardDetails getCartdetails() {
		return cartdetails;
	}
	public void setCartdetails(CardDetails cartdetails) {
		this.cartdetails = cartdetails;
	}
	
	
}
 