package com.godieboy.clip.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionUtil {
	
	public static String AMOUNT_FIELD = "amount";
	public static String DATE_FIELD = "date";
	public static String DESCRIPTION_FIELD = "description";
	public static String USER_ID_FIELD = "user_id";
	public static String TRANSACTION_ID_FIELD = "transaction_id";
	
	public static String DATE_FORMAT="yyyy-MM-dd";
	
	public static String FILE_NAME="transactions.json";
	private static final SimpleDateFormat sdf = new SimpleDateFormat(TransactionUtil.DATE_FORMAT);

	
	
	public static Date dateFromString(String dateString) throws ParseException{
		return sdf.parse(dateString);
	}

}
