package com.godieboy.clip.dao.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.godieboy.clip.dao.TransactionDAO;
import com.godieboy.clip.model.Transaction;
import com.godieboy.clip.model.converter.TransactionConverter;
import com.godieboy.clip.util.TransactionUtil;

@Repository
public class TransactionDAOImp implements TransactionDAO {

	
	Logger logger = LoggerFactory.getLogger(TransactionDAOImp.class);

	
	private List<Transaction> transactionList;

	public TransactionDAOImp() {
		//logger.info("instanacicesssssssssss");
		loadFile();

	}
	
	private boolean saveOnFile(Transaction transaction){
		try (FileWriter file = new FileWriter(new ClassPathResource(TransactionUtil.FILE_NAME).getFile())){
			this.transactionList.add(transaction);
			JSONArray convertListToJsonArray = TransactionConverter.convertListToJsonArray(transactionList);
			file.write(convertListToJsonArray.toJSONString());
			file.flush();
			loadFile();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
		}
		
		return false;
		
	}

	private String loadFile() {
		JSONParser jsonParser = new JSONParser();
		try {
			File dataFile = new ClassPathResource(TransactionUtil.FILE_NAME).getFile();
			FileReader reader = new FileReader(dataFile);
			Object obj = jsonParser.parse(reader);
			 
            JSONArray transactionsJsonArray = (JSONArray) obj;
            
            this.transactionList = TransactionConverter.convertToTransactionListFromJson(transactionsJsonArray);
            
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {		
			e.printStackTrace();
		} finally {
			
		}
		return "done";
	}

	@Override
	public Transaction findByIdAndUserId(String transactionId, long userId) {
		logger.info("ID="+transactionId);
		logger.info("userID="+userId);
		List<Transaction> filtered = this.transactionList.stream().filter(t -> t.getUserId() == userId && transactionId.equals(t.getId())).collect(Collectors.toList());
		if(filtered.size() == 1) {
			return filtered.get(0);
		}
		return null;
	}

	@Override
	public List<Transaction> findByUserId(long userId) {
		List<Transaction> filtered = this.transactionList.stream().filter(t -> t.getUserId() == userId).collect(Collectors.toList());
		return filtered;
	}

	@Override //TODO delete from DAO 
	public double sum(long userId) {
		List<Transaction> listByUser = findByUserId(userId);
		
		logger.info("SIZE =="+ listByUser.size());
		
		double sum =0;
		for(Transaction t : listByUser) {
			sum += t.getAmount();
			logger.info(""+sum);
		}
		sum = Math.round(sum*100)/100.0d; //para redondear

		return sum;
	}

	@Override
	public Transaction save(Transaction transaction) {
		// TODO Auto-generated method stub
		String id = UUID.randomUUID().toString();
		transaction.setId(id);
		
		if(this.saveOnFile(transaction)) {
			return transaction;
		}
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return this.transactionList.size();
	}
	
	

}
