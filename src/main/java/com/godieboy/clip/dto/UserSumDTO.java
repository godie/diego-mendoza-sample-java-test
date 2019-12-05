package com.godieboy.clip.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSumDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 112300000L;
	@JsonProperty("user_id")
	private long userId;
	private double sum;
	
	public UserSumDTO() {
	
	}
	
	public UserSumDTO(long userId, double sum){
		this.sum = sum;
		this.userId = userId;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
}
