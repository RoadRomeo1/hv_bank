package com.hv.foundation.bank.hv_bank.service;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.hv.foundation.bank.hv_bank.model.AccountOperationResponseFormat;
import com.hv.foundation.bank.hv_bank.model.ExceptionResponse;

@Service
public class ResponseService {
	
	//returns response for CRUD operations
	public AccountOperationResponseFormat getOperationResponse(int status, String message, long account_no) {
		
		AccountOperationResponseFormat format = new AccountOperationResponseFormat();
 		format.setStatus(status);
		format.setMessage(message);
		format.setAccount_number(account_no);
		return format;
	}
	
	//returns response whenever exception is thrown anywhere
	  public ExceptionResponse getExceptionResponse(int status, String error, HttpServletRequest request) {
			
			ExceptionResponse response = new ExceptionResponse();
			
			response.setTimestamp(System.currentTimeMillis());
			response.setStatus(status);
			response.setError(error);
			response.setPath(request.getRequestURI());
			
			return response;
		}
	
	  
}
