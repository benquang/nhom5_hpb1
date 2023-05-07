package com.team5.CommonService.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

	private String id;
	private String userid;
	private String password;
	private String fullname;
	private String cardnumber;
	private Double balance;
	private int validmonth;
	//private CardDetails cartdetails;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public int getValidmonth() {
		return validmonth;
	}
	public void setValidmonth(int validmonth) {
		this.validmonth = validmonth;
	}
	
	
	
	
	
	/*public CardDetails getCartdetails() {
		return cartdetails;
	}
	public void setCartdetails(CardDetails cartdetails) {
		this.cartdetails = cartdetails;
	}*/
	
	
	
	
	
}
 