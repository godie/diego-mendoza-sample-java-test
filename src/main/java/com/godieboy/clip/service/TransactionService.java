package com.godieboy.clip.service;

import java.util.List;

import com.godieboy.clip.dto.TransactionReportDTO;
import com.godieboy.clip.exception.TransactionException;
import com.godieboy.clip.model.Transaction;

public interface TransactionService {
	
	Transaction add(Transaction transaction) throws TransactionException;
	Transaction show(long userId, String transactionId);
	List<Transaction> findByUser(long userId);
	double sumByUser(long userId);
	List<TransactionReportDTO> getReport(long userId);

}
