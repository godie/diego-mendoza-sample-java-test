package com.godieboy.clip.dao;

import java.util.List;

import com.godieboy.clip.model.Transaction;



public interface TransactionDAO  {
	
	Transaction findByIdAndUserId(String transactionId, long userId);
	List<Transaction> findByUserId(long userId);
	double sum(long userId);
	Transaction save(Transaction transaction);
	int countAll();

}
