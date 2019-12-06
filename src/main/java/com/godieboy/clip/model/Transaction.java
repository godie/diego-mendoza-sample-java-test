package com.godieboy.clip.model;

import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Transaction {

	@Column(name = "user_id")
	@JsonProperty("user_id")
	private long userId;

	@Id
	// @Column(name="transaction_id", columnDefinition="BINARY(16)")
	@JsonProperty("transaction_id")
	private String id;

	@Column(name = "amount")
	private double amount;

	@Column(name = "description")
	private String description;

	@Column(name = "date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static Comparator<Transaction> ORDER_BY_DATE = new Comparator<Transaction>() {
		public int compare(Transaction one, Transaction other) {
			return one.date.compareTo(other.date);
		}
	};

	@Override
	public String toString() {
		return "{user_id=" + userId + ", transaction_id=" + id + ", amount=" + amount + ", description=" + description
				+ ", date=" + date + "}";
	}

}