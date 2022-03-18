package com.hv.foundation.bank.hv_bank.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hv.foundation.bank.hv_bank.dao.AccountOperation;
import com.hv.foundation.bank.hv_bank.model.Account;
import com.hv.foundation.bank.hv_bank.model.AccountOperationResponseFormat;
import com.hv.foundation.bank.hv_bank.model.Fund;
import com.hv.foundation.bank.hv_bank.util.TestData;

@Service
public class FundTransferSerivce {

	// Dependencies lies here
	@Autowired
	private ValidationService validationService;
	@Autowired
	private ResponseService resService;
	@Autowired
	private AccountOperation api;

	public AccountOperationResponseFormat TransferAmount(Fund fund) {

// 		validation of fund transfer info.
//		ValidationService validationService = new ValidationService();
		validationService.isTransferValid(fund);
//		
		//for unit testing...
//		int sender_len = String.valueOf(fund.getSender_id()).length();
//		int receiver_len = String.valueOf(fund.getReceiver_id()).length();
//			
//		if((sender_len == 3)&&(receiver_len == 3)) {
//			
//			Account sender = TestData.getAccount(fund.getSender_id());
//			Account receiver = TestData.getAccount(fund.getReceiver_id());
//			
//			System.out.println("Sender account: " + sender.toString());
//			System.out.println("Receiver account: " + receiver.toString());
//			
//			receiver.setBalance(receiver.getBalance() + fund.getAmount());
//			sender.setBalance(sender.getBalance() - fund.getAmount());
//			
//			ResponseService resService = new ResponseService();
//			return resService.getOperationResponse(HttpStatus.OK.value(), "success", 0);
//			
//		}
		
		// incrementing amount on receiver end
		Account reciver = api.findById(fund.getReceiver_id()).get();
		reciver.setBalance(reciver.getBalance() + fund.getAmount());
		api.save(reciver);

		// decrementing account on senders end
		Account sender = api.findById(fund.getSender_id()).get();
		sender.setBalance(sender.getBalance() - fund.getAmount());
		api.save(sender);

		
		// returning response after transfer
		return resService.getOperationResponse(HttpStatus.OK.value(), "Fund transfer successful", 0);
	}

}
