package com.godieboy.clip.exception;

public class TransactionException extends Exception {
	
	private int errorCode;
	private String errorMessage;

	/**
	 * 
	 */
	private static final long serialVersionUID = 10000L;
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public TransactionException(Throwable throwable) {
		super(throwable);
	}
	
	public TransactionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
	public TransactionException(String msg) {
		super(msg);
	}
	
	public TransactionException(String message, int errorCode) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = message;
	}
	
	@Override
	public String toString() {
		
		return this.errorCode + ":" + this.getErrorMessage();
	}
	
	

}
