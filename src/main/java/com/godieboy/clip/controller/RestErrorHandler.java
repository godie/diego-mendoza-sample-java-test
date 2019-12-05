package com.godieboy.clip.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.godieboy.clip.dto.ErrorMessageDTO;
import com.godieboy.clip.exception.TransactionException;

@RestControllerAdvice
public class RestErrorHandler {
	
	@ExceptionHandler(TransactionException.class)
	//@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	//@ResponseBody
	public ErrorMessageDTO processValidationError(TransactionException ex) {
		ErrorMessageDTO dto = new ErrorMessageDTO();
		dto.setCode(ex.getErrorCode());
		dto.setMessage(ex.getErrorMessage());
		return dto;
	}

}
