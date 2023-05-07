package com.team5.UserService.data;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
	@Id
	private String id;
	private String userid;
	private String password;
	private String fullname;
	private String cardnumber;
	private Double balance;
	private int validmonth;
	
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
	
	
	
	
	
	
	
	
	
}
