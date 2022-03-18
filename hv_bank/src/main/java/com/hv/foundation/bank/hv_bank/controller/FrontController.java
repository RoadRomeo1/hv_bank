package com.hv.foundation.bank.hv_bank.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hv.foundation.bank.hv_bank.model.Account;
import com.hv.foundation.bank.hv_bank.model.AccountOperationResponseFormat;
import com.hv.foundation.bank.hv_bank.model.Fund;
import com.hv.foundation.bank.hv_bank.service.AccountServices;
import com.hv.foundation.bank.hv_bank.service.FundTransferSerivce;


@Controller
public class FrontController {

	@Autowired
	private AccountServices service;
	@Autowired
	private FundTransferSerivce fundService;
	
	
	//CRUD operations endpoints
	//===========================
	
	@GetMapping("/bank")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<Account> fetchAllAccounts() {
		return service.getAllAccounts();
	}
	
	@GetMapping("/bank/{no}")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Account fetchAllAccountByNo(@PathVariable long no) {
		return service.getAccountByNo(no);
	}
	
	@PostMapping("/bank")
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody AccountOperationResponseFormat saveAccount(@RequestBody Account account) {
		return service.createAccount(account);
	}
	
	@PutMapping("/bank/{no}")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody AccountOperationResponseFormat alterAccountByNo(@PathVariable Long no,@RequestBody Account account) {
		return service.updateByAccountNo(no, account);
		
	}
	
	@DeleteMapping("/bank")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody AccountOperationResponseFormat removeAllAccounts() {
		return service.deleteAllAccounts();
	}
	
	@DeleteMapping("/bank/{no}")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody AccountOperationResponseFormat removeAccountsByNo(@PathVariable long no) {
		return service.deleteAccountByNo(no);
	}
	
	//Fund transfer endpoint
	//=================
	
	@PostMapping("/bank/send")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody AccountOperationResponseFormat sendMoney(@RequestBody Fund fund) {
		return fundService.TransferAmount(fund);
	}
	
	
	
}
