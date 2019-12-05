package com.godieboy.clip.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.godieboy.clip.dao.TransactionDAO;
import com.godieboy.clip.dto.TransactionReportDTO;
import com.godieboy.clip.exception.TransactionException;
import com.godieboy.clip.model.Transaction;
import com.godieboy.clip.service.TransactionService;


@Service
public class TransactionServiceImp implements TransactionService {
	
	
	@Autowired
	private TransactionDAO transactionDAO;

	@Override
	public Transaction add(Transaction transaction) throws TransactionException {
		if(transaction.getAmount() < 1) {
			throw new TransactionException("Amount must to be positive", 10);
		}
		return transactionDAO.save(transaction);
	}

	@Override
	public Transaction show(long userId, String transactionId) {
		return transactionDAO.findByIdAndUserId(transactionId, userId);
	}

	@Override
	public List<Transaction> findByUser(long userId) {
		return transactionDAO.findByUserId(userId);
	}

	@Override
	public double sumByUser(long userId) {
		double sum = 0;
		List<Transaction> transactions = transactionDAO.findByUserId(userId);
		for(Transaction t : transactions) {
			sum += t.getAmount();
		}
		sum = Math.round(sum*100)/100.0d; //para redondear
		return sum;
	}

	@Override
	public List<TransactionReportDTO> getReport(long userId) {
		List<Transaction> transactions = transactionDAO.findByUserId(userId);
		return null;
	}
	
	
	

}
