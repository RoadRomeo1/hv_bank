package com.hv.foundation.bank.hv_bank.util;

import java.util.ArrayList;
import java.util.List;

import com.hv.foundation.bank.hv_bank.model.Account;

public class TestData {
	public static Account getAccount(long no) {
		Account account = new Account(no, "savings", "test", "account", 101, "test city address", 1234567890, "test.account@mail.com", 12345.0);
		return account;
	}
	
	public static List<Account> getAccounts(int len){
		List<Account> accounts = new ArrayList<Account>();
		for(int i = 1 ; i != len+1 ; i++) {
			accounts.add(new Account(Long.valueOf(i), i%2 == 0 ? "savings": "current", "test-"+ i, "account", 101+i, "test city-"+i+" address", 1234567890, "test"+i+".account@mail.com", 12345.0));
		}
		return accounts;
	}
}
