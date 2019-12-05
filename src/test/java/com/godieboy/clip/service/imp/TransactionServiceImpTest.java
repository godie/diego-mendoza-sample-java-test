package com.godieboy.clip.service.imp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.godieboy.clip.exception.TransactionException;
import com.godieboy.clip.model.Transaction;
import com.godieboy.clip.service.TransactionService;
import com.godieboy.config.TransactionServiceConfig;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TransactionServiceConfig.class)
class TransactionServiceImpTest {
	
	@Autowired
	TransactionService TransactionService;

	@Test//(expected = TransactionException.class)
	
	void testAdd() throws TransactionException {
		Transaction transaction = new Transaction();
		transaction.setDate(new Date());
		transaction.setUserId(100l);
		transaction.setAmount(500);
		transaction.setId(null);
		transaction = TransactionService.add(transaction);
		assertNotNull(transaction.getId());
	}
	
	void testAddException() throws TransactionException {
		Transaction transaction = new Transaction();
		transaction.setDate(new Date());
		transaction.setUserId(100l);
		transaction.setAmount(0);
		transaction.setId(null);
		assertThrows(TransactionException.class, () -> TransactionService.add(transaction));
		//(TransactionException.class, () -> {TransactionService.add(transaction)});
		
	}

	@Test
	void testShow() {
		Transaction t = TransactionService.show(123l, "2299ce24-9eaf-417f-82d6-e57f93777dc4");
		assertNotNull(t);
	}

	@Test
	void testFindByUser() {
		List<Transaction> transactions = TransactionService.findByUser(123);
		assertTrue(transactions.size() > 0);
		
	}

	@Test
	void testSumByUser() {
		double sum = TransactionService.sumByUser(123);
		assertTrue(sum > 0);
	}
	
	@Test
	void testSumByNoTUser() {
		double sum = TransactionService.sumByUser(000);
		assertTrue(sum == 0);
	}

	@Test
	void testGetReport() {
		assertTrue(true);
	}

}
