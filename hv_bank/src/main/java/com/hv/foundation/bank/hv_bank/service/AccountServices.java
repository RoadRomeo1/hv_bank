package com.hv.foundation.bank.hv_bank.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hv.foundation.bank.hv_bank.dao.AccountOperation;
import com.hv.foundation.bank.hv_bank.exception.AccountNotFoundException;
import com.hv.foundation.bank.hv_bank.model.Account;
import com.hv.foundation.bank.hv_bank.model.AccountOperationResponseFormat;

@Service
public class AccountServices {
	
	//Dependencies lies here
	@Autowired
	private AccountOperation api;
	@Autowired
	private ResponseService resService;
	@Autowired
	private ValidationService validationService;
	
	//Used for fetching all the accounts available
	public List<Account> getAllAccounts(){
		
		List<Account> list = new ArrayList<Account>();
		api.findAll().forEach(list::add);
		
		if (list.isEmpty()) {
			throw new AccountNotFoundException("Accounts list is empty");
		}
		return list;
	}
	
	//Used for creation of account
	public AccountOperationResponseFormat createAccount(Account account) {
		
		//validation of account info
//		ValidationService validationService = new ValidationService();
		validationService.isValidAccount(account);
		long acc_no = 0;
		
		//for unit testing...
//		if(account.getAccount_no() == Long.valueOf(1)) {
//			api.save(account);
//			return null;
//		}
		
		//account creation happens here
		acc_no = account.generateAccountNo();
		account.setAccount_no(acc_no);
		api.save(account);
		//response formatting and returning
		return resService.getOperationResponse(HttpStatus.CREATED.value(), "Account is created.", acc_no);
		
	}
	
	//used for fetching account details with account number
	public Account getAccountByNo(long no) {
		
		//for unit testing...
//				if(no == Long.valueOf(1)) {
//					Account account = api.findById(no).get();
//					return account;
//				}
//		
		if (!api.existsById(no)) {
			throw new AccountNotFoundException("No account found for number: " + no);
		}
		
		Account account = api.findById(no).get();
		return account;
	}
	
	//used for deleting all the accounts
	public AccountOperationResponseFormat deleteAllAccounts() {
	
		List<Account> list = new ArrayList<Account>();
		
		api.findAll().forEach(list::add);

		//comment this if statement in case of unit testing....
		if(list.isEmpty()) {
			throw new AccountNotFoundException("Accounts list is empty");
		}
		
		api.deleteAll();
	
		
		return resService.getOperationResponse(HttpStatus.OK.value(), "All data is deleted", 0);
	}
	
	//used for deleting accounts by account number
	public AccountOperationResponseFormat deleteAccountByNo(Long no) {
		
		//for unit testing
//		if(no == Long.valueOf(1)) {
//			api.deleteById(no);
//			return null;
//		}
		
		if (api.existsById(no)) {
			//crud section
			api.deleteById(no);
			//response format section
			return resService.getOperationResponse(HttpStatus.OK.value(), "Account is removed", no);
		}
		
		else {
			throw new AccountNotFoundException("No account found for number: " + no);
		}
	}
	
	//used for updating account details by their account number
	public AccountOperationResponseFormat updateByAccountNo(long no, Account account) {
		
		//for unit testing...
//		if(no == Long.valueOf(1)) {
//			
//			ValidationService validationService = new ValidationService();
//			validationService.isValidAccount(account);
//			
//			api.save(account);
//			return null;
//		}
		
		//checks that accounts exist or not
		if(api.existsById(no)) {

			account.setAccount_no(no);
			
			//validates data for account for updation
			validationService.isValidAccount(account);
			
			//updation happens here
			api.save(account);
			
			
			//response format section
		return resService.getOperationResponse(HttpStatus.OK.value(), "Account details is updated", no);
		}
		else {
			throw new AccountNotFoundException("No account found for number: " + no);
		}
	}
	
	
}
