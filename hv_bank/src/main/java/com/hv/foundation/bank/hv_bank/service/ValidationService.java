package com.hv.foundation.bank.hv_bank.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hv.foundation.bank.hv_bank.dao.AccountOperation;
import com.hv.foundation.bank.hv_bank.exception.AccountNotFoundException;
import com.hv.foundation.bank.hv_bank.exception.InvalidAccountInformationException;
import com.hv.foundation.bank.hv_bank.model.Account;
import com.hv.foundation.bank.hv_bank.model.Fund;

@Service
public class ValidationService {
	
	private static final String VALID_EMAIL_PATTERN = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	@Autowired
	AccountOperation api;
	
	
	//used for account info validation
	public void isValidAccount(Account account) {
		
		//check for empty input
		if(isAccountNullI(account) == false) {
			//for first name
			
			if(account.getFirst_name() == null || account.getFirst_name().equals("") || account.getFirst_name().equals(" ")) {
				throw new InvalidAccountInformationException("First name is required");
			}
			else {
				if((account.getFirst_name().length() > 30)){
					throw new InvalidAccountInformationException("minimum 1 and maximum 30 characters are allowed for first name");
				}
			}
			
			//for last name
			if(account.getLast_name() == null || account.getLast_name().equals("") || account.getLast_name().equals(" ")) {
				throw new InvalidAccountInformationException("Last name is required");
			}
			else {
				if((account.getLast_name().length() > 30)) {
					throw new InvalidAccountInformationException("minimum 1 and maximum 30 characters are allowed for last name");
				}
				
				
			}
			//for age
			if(account.getAge() == 0) {
				throw new InvalidAccountInformationException("Age is required");
			}
			else if((account.getAge() < 10)) {
				throw new InvalidAccountInformationException("Age limit is above 10 years");
			}
			
			//for balance
			if(!(account.getBalance() > 0)) {
				throw new InvalidAccountInformationException("Minimum balance is 1");
			}
			
			//for account type
			if(account.getAccount_type() == null || account.getAccount_type().equals("")) {
				throw new InvalidAccountInformationException("Account type is required.");
			}
			else {
				if((!account.getAccount_type().equals("savings")) && (!account.getAccount_type().equals("current"))) {
					throw new InvalidAccountInformationException("Account type should be savings or current");
				}
			}
			
			//for address
			if(account.getAddress() == null || account.getAddress().length() == 0) {
				throw new InvalidAccountInformationException("Address is required");
			}
			else{
				if(account.getAddress().length() > 150)
				throw new InvalidAccountInformationException("Word limit for address is 1-150");
			}
			
			
			//for contact
			if(account.getContact() != 0) {
				String phone = Long.toString(account.getContact());
				
				if(phone.length() != 10) {
					throw new InvalidAccountInformationException("Contact number must be of 10 digits");
				}
			}
			else if(account.getContact() == 0){
				throw new InvalidAccountInformationException("Contact number is required");
			}
			
			
			//for email
			if(account.getEmail() != null) {
				
				if(!account.getEmail().equals("")) {
					boolean res = account.getEmail().matches(VALID_EMAIL_PATTERN);
					
					if(res == false) {
						throw new InvalidAccountInformationException("Invalid email") ;
					}
				}
			}
		}
		else {
			throw new InvalidAccountInformationException("Details is required.");
		}
	}
	
	
	public boolean isAccountNullI(Account account) {
		boolean status = false;
		
		if(account.getFirst_name() == null && account.getLast_name() == null && account.getAge() == 0 && account.getBalance() == 0.0 && account.getAccount_type() == null && account.getAddress() == null && account.getContact() == 0 && account.getEmail() == null) {
			status  = true;
		}
		
		return status;
	}
	
	
	//used for fund transfer info validation
	
	public void isTransferValid(Fund fund) {
		
		long no_of_records = api.count();
		ValidationService validationService = new ValidationService();
		
		//check for empty input
		if(isFundNull(fund) == false) {
			//check for empty list
			if (no_of_records != 0) {
			
				if(isAccountsNotValid(fund)) 
				throw new InvalidAccountInformationException("Invalid Account Number");
				
				//check for equal account numbers
				if (validationService.isAccountEqual(fund.getSender_id(), fund.getReceiver_id()) == false) {
				
					//check for sender account 
					if (api.findById(fund.getSender_id()).isPresent()) {			
					
						//check for receiver account
						if (api.findById(fund.getReceiver_id()).isPresent()) {
						
							//check for valid amount
							if(fund.getAmount() >= 1) {
								
								double balance = api.findById(fund.getSender_id()).get().getBalance();
								
								//check for comparison of balance and amount
								if (balance >= fund.getAmount()) {
									//transfer
									return;
								} else {
									throw new InvalidAccountInformationException("Not Enough Balance");
								}
							}
							else {
								throw new InvalidAccountInformationException("Amount should be at least 1.");
							}
						} else {
							throw new AccountNotFoundException("Receivers account not found");
						}
					} else {
						throw new AccountNotFoundException("Senders account not found");
					}
				} else {
					throw new InvalidAccountInformationException("Both accounts can not be same");
				}
			} else {
				throw new AccountNotFoundException("Accounts list is empty.");
			}
		}
		else {
			throw new InvalidAccountInformationException("Details is required.");
		}
	}
	
	public boolean isFundNull(Fund fund) {
		boolean status = false;
		
		if(fund.getSender_id() == 0 && fund.getReceiver_id() == 0 && fund.getAmount() == 0.0) {
			status = true;
		}
		return status;
	}
	
	public boolean isAccountsNotValid(Fund fund) {
		boolean status = false;
		
		int sender = String.valueOf(fund.getSender_id()).length();
		int receiver = String.valueOf(fund.getReceiver_id()).length();
		
		if(sender != 8 || receiver != 8) {
			status = true; 
		}
		return status;
	}
	
	public boolean isAccountEqual(long sender_id, long receiver_id) {
		return sender_id == receiver_id;
	}
	
}
