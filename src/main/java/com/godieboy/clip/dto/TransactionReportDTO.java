package com.godieboy.clip.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionReportDTO {

	@JsonProperty("id")
	private long userId;
	private String weekStartDate;
	private String weekEndDate;
	private int quantity;
	
	@JsonFormat(pattern="%.2f")
	private double amount;
	@JsonFormat(pattern="%.2f")
	private double totalAmount;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getWeekStartDate() {
		return weekStartDate;
	}

	public void setWeekStartDate(String weekStartDate) {
		this.weekStartDate = weekStartDate;
	}

	public String getWeekEndDate() {
		return weekEndDate;
	}

	public void setWeekEndDate(String weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
