package com.godieboy.clip.dao.imp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.godieboy.clip.dao.TransactionDAO;
import com.godieboy.clip.model.Transaction;


@RunWith(SpringRunner.class)
@SpringBootTest
class TransactionDAOImpTest {
	
	private TransactionDAO transactionDAO;
	
	@TestConfiguration
    static class TransactionDAOImpTestContextConfiguration {
  
        @Bean
        public TransactionDAO transactionDAO() {
            return new TransactionDAOImp();
        }
    }
	
	@Autowired
	public void setTransactionDAO(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}

	@Test
	void testFindByIdAndUserId() {
		long userId = 123;
		String transactionId = "2299ce24-9eaf-417f-82d6-e57f93777dc4";
		
		Transaction t = transactionDAO.findByIdAndUserId(transactionId, userId);
		assertNotNull(t);
		assertEquals("Joes Tacos", t.getDescription());
	}

	@Test
	void testFindByUserId() {
		long userId = 123;
		List<Transaction> list = transactionDAO.findByUserId(userId);
		assertNotNull(list);
		assertTrue(list.size() > 0);
		assertNotNull(list.get(0));
	}
	
	@Test
	void testNotFindByUserId() {
		long userId = 1234;
		List<Transaction> list = transactionDAO.findByUserId(userId);
		assertNotNull(list);
		assertTrue(list.size() == 0);
	}

	@Test
	void testSum() {
		long userId = 123;
		double expected = 101.23;
		double sum = transactionDAO.sum(userId);
		assertNotNull(sum);
		assertTrue("Expected" + expected +" but was" + sum, sum == expected);
	}

	@Test
	void testSave() {
		int actualSize = transactionDAO.countAll();
		Transaction transaction = new Transaction();
		transaction.setAmount(50.0);
		transaction.setDate(new Date());
		transaction.setDescription("Save Test");
		transaction.setUserId(1000l);
		transaction = transactionDAO.save(transaction);
		assertNotNull(transaction);
		assertNotNull(transaction.getId());
		assertTrue(actualSize < transactionDAO.countAll());
		
	}

}
