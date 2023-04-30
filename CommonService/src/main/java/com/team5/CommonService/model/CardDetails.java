package com.team5.CommonService.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDetails {
	private String cartnumber;
	private int validuntilmonth;
	private double balance;
	public String getCartnumber() {
		return cartnumber;
	}
	public void setCartnumber(String cartnumber) {
		this.cartnumber = cartnumber;
	}
	public int getValiduntilmonth() {
		return validuntilmonth;
	}
	public void setValiduntilmonth(int validuntilmonth) {
		this.validuntilmonth = validuntilmonth;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	

}
