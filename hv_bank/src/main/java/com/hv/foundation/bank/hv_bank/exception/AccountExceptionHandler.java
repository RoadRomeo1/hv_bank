package com.hv.foundation.bank.hv_bank.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.hv.foundation.bank.hv_bank.model.ExceptionResponse;
import com.hv.foundation.bank.hv_bank.service.ResponseService;

@ControllerAdvice
public class AccountExceptionHandler {
	
	@Autowired
	ResponseService response;
	
	//handling of custom exception starts here..

	//throws when user for given number is not found on the database
	@ExceptionHandler(AccountNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleAccountNotFoundExcpetion(AccountNotFoundException exception, HttpServletRequest request) {
		
		List<String> errors = new ArrayList<String>();
		errors.add(exception.getMessage());
		
		return response.getExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request);
	
	}
	
	//handled exception when user entered invalid account information while creating account.
	@ExceptionHandler(InvalidAccountInformationException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public @ResponseBody ExceptionResponse handleInvalidAccountInformationException(InvalidAccountInformationException exception, HttpServletRequest request) {
		
		return response.getExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), exception.getMessage(), request);

	}
	
	
	//handles all the generic exception..
		@ExceptionHandler(Exception.class)
		@ResponseStatus(value = HttpStatus.BAD_REQUEST)
		public @ResponseBody ExceptionResponse handleGenericException(Exception exception, HttpServletRequest request) {

			return response.getExceptionResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request); 
		}
		
	//throws when user give invalid account number in URI
		@ExceptionHandler(MethodArgumentTypeMismatchException.class)
		@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
		public @ResponseBody ExceptionResponse handleGenericException(MethodArgumentTypeMismatchException exception, HttpServletRequest request) {		
			return response.getExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Invalid account number", request);
		}	
	
	//throws when use give account information in invalid format 
		@ExceptionHandler(HttpMessageNotReadableException.class)
		@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
		public @ResponseBody ExceptionResponse handleInvalidDataException(HttpMessageNotReadableException exception, HttpServletRequest request) {
			return response.getExceptionResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Account data is in invalid format", request);
		}	
}
