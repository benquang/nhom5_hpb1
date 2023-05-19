package com.team5.UserService.model;

public class UserRestModel {
	private String userid;
	private String password;
	
	private String fullname;
	private String cardnumber;
	private Double balance;
	private int validmonth;
	
	private String lastorder;
	private Double lastpay;
	
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
	public String getLastorder() {
		return lastorder;
	}
	public void setLastorder(String lastorder) {
		this.lastorder = lastorder;
	}
	public Double getLastpay() {
		return lastpay;
	}
	public void setLastpay(Double lastpay) {
		this.lastpay = lastpay;
	}
	
	
	
	
}
