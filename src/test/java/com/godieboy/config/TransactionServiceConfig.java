package com.godieboy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.godieboy.clip.dao.TransactionDAO;
import com.godieboy.clip.dao.imp.TransactionDAOImp;
import com.godieboy.clip.service.TransactionService;
import com.godieboy.clip.service.imp.TransactionServiceImp;

@Configuration
public class TransactionServiceConfig {
	
	@Bean
	TransactionService transactionService() {
		return new TransactionServiceImp();
	}
	
	@Bean
	TransactionDAO transactionDAO() {
		return new TransactionDAOImp();
	}

}
