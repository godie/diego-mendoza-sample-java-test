package com.godieboy.clip.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionUtil {

	public static String AMOUNT_FIELD = "amount";
	public static String DATE_FIELD = "date";
	public static String DESCRIPTION_FIELD = "description";
	public static String USER_ID_FIELD = "user_id";
	public static String TRANSACTION_ID_FIELD = "transaction_id";

	public static String DATE_FORMAT = "yyyy-MM-dd";
	public static String DATE_FORMAT_DAY = "yyyy-MM-dd EEEE";

	public static String FILE_NAME = "transactions.json";
	private static final SimpleDateFormat sdf = new SimpleDateFormat(TransactionUtil.DATE_FORMAT);

	private static final SimpleDateFormat sdf2 = new SimpleDateFormat(TransactionUtil.DATE_FORMAT_DAY);

	private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

	public static Date dateFromString(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(TransactionUtil.DATE_FORMAT);
		sdf.setLenient(false);
		return sdf.parse(dateString);
	}

	public static Date dateFromStringWitday(String dateString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(TransactionUtil.DATE_FORMAT_DAY);
		sdf.setLenient(false);
		return sdf.parse(dateString);
	}

	public static String formatDayWitday(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(TransactionUtil.DATE_FORMAT_DAY);
		sdf.setLenient(false);
		return sdf.format(date);
	}

	public static double fixDouble(double number) {
		String doubleString = decimalFormat.format(number);
		Double d = Double.valueOf(doubleString);
		return d.doubleValue();
	}

}
