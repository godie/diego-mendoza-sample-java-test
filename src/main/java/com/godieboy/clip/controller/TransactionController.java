package com.godieboy.clip.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.godieboy.clip.dto.TransactionDataDTO;
import com.godieboy.clip.dto.TransactionReportDTO;
import com.godieboy.clip.dto.UserSumDTO;
import com.godieboy.clip.exception.TransactionException;
import com.godieboy.clip.model.Transaction;
import com.godieboy.clip.service.TransactionService;
import com.godieboy.clip.util.TransactionUtil;

//@Controller
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	
	Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping(value="/{userId}/{transactionId}",  produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Transaction show(@PathVariable long userId,@PathVariable String transactionId) throws TransactionException {
		Transaction transaction = transactionService.show(userId, transactionId);
		if(transaction == null) {
			transaction = new Transaction();
		}
		return transaction;
		
	}
	
	@RequestMapping(value="/{userId}",  produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Transaction> show(@PathVariable long userId) {
		List<Transaction> transaction = transactionService.findByUser(userId);
		return transaction;
		
	}
	
	@RequestMapping(value="/{userId}/add",  produces = "application/json", method = RequestMethod.POST)
	@ResponseBody
	//public Transaction add(@PathVariable long userId,@RequestBody Map<String, Object> data ) throws TransactionException {
		public Transaction add(@PathVariable long userId,@RequestBody TransactionDataDTO data ) throws TransactionException {
		
		Transaction transaction = null;
		
		try {
			String dateString = data.getDate();
			String description = data.getDescription();
			double amount = data.getAmount();
			long userId2 = data.getUserId();
			transaction = new Transaction();
			Date dateFromString = TransactionUtil.dateFromString(dateString);
			transaction.setDate(dateFromString);
			transaction.setUserId(userId);
			transaction.setDescription(description);
			transaction.setAmount(amount);
			if(userId == userId2) {
				transaction = transactionService.add(transaction);
			}
		} catch (ParseException ex) {
 			throw new TransactionException("Date Format is not valid", 20);
		}
				
		if(transaction == null) {
			transaction = new Transaction();
		}
		return transaction;
		
	}
	@RequestMapping(value="/{userId}/sum",  produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public UserSumDTO sum(@PathVariable long userId) {
		double sum = transactionService.sumByUser(userId);
		return new UserSumDTO(userId,sum);
	}
	
	@RequestMapping(value="/{userId}/report",  produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public List<TransactionReportDTO> report(@PathVariable long userId) {
		List<TransactionReportDTO> reportList = transactionService.getReport(userId);
		return reportList;
	}
	
	
	
	
	
	@RequestMapping(value="",  produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public String index() {
		
		return "Hello world";
		
	}

}
