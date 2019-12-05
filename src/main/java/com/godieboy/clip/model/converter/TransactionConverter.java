package com.godieboy.clip.model.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.godieboy.clip.model.Transaction;
import com.godieboy.clip.util.TransactionUtil;

public class TransactionConverter {
	 
	//TODO MOVE TO TransactionsUtil
	private static final SimpleDateFormat sdf = new SimpleDateFormat(TransactionUtil.DATE_FORMAT);

	
	public static Transaction convertToTransactionFromJson(JSONObject transactionJson) {
		Transaction transaction = null;
		if(transactionJson != null) {
			double amount = (double) transactionJson.get(TransactionUtil.AMOUNT_FIELD);
			String description = (String) transactionJson.get(TransactionUtil.DESCRIPTION_FIELD);
			String dateString = (String) transactionJson.get(TransactionUtil.DATE_FIELD);
			long userId = (long) transactionJson.get(TransactionUtil.USER_ID_FIELD);
			String transactionId = (String) transactionJson.get(TransactionUtil.TRANSACTION_ID_FIELD);
			
			transaction = new Transaction();
			
			transaction.setId(transactionId);
			transaction.setAmount(amount);
			transaction.setUserId(userId);
			
			if(description != null) {
				transaction.setDescription(description);
			}
			
			if(dateString != null) {
				try {
					transaction.setDate(sdf.parse(dateString));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		}
		return transaction;
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<Transaction> convertToTransactionListFromJson(JSONArray transactionArray){
		List<Transaction> trasactionList = new ArrayList<Transaction>();
		transactionArray.iterator();
		transactionArray.forEach(transactionJson -> trasactionList.add(convertToTransactionFromJson( (JSONObject)transactionJson)) );
		return trasactionList;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray convertListToJsonArray(List<Transaction> listTransactions) {
		JSONArray transactionsArr = new JSONArray();
		//transactionsArr.addAll(listTransactions);
		listTransactions.stream().forEach(transaction -> transactionsArr.add(convertTransactionToJsonObject(transaction)));
		return transactionsArr;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject convertTransactionToJsonObject(Transaction transaction) {
		JSONObject transactionJson = null;
		@SuppressWarnings("unused")
		Map<String, Object> values = new HashMap<>();
		if(transaction != null) {
			transactionJson = new JSONObject();
			transactionJson.put(TransactionUtil.AMOUNT_FIELD, transaction.getAmount());
			
			Date dateRaw = transaction.getDate();
			String dateString = sdf.format(dateRaw);
			transactionJson.put(TransactionUtil. DATE_FIELD, dateString);
			String description = transaction.getDescription();
			if(description == null) {
				description = "";
			}
			transactionJson.put(TransactionUtil.DESCRIPTION_FIELD, description);
			transactionJson.put(TransactionUtil.TRANSACTION_ID_FIELD, transaction.getId());
			transactionJson.put(TransactionUtil.USER_ID_FIELD, transaction.getUserId());
		}
		return transactionJson;
	}

}
