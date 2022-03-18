package com.hv.foundation.bank.hv_bank.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hv.foundation.bank.hv_bank.model.Account;
import com.hv.foundation.bank.hv_bank.util.TestData;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class AccountOperationTest {

	@Autowired
	AccountOperation api;
	
	@Test
	void createOrUpdateAccountTest() {
		Account account = TestData.getAccount(Long.valueOf(1));
		
		api.save(account);
		
		List<Account> accounts = new ArrayList<Account>();
		api.findAll().forEach(acc -> accounts.add(acc));
		assertEquals(1, accounts.size());
	}
	
	
	@Test
	void getAccountByNoTest() {
		
		Account account = TestData.getAccount(Long.valueOf(1));
		api.save(account);
		
		Account testAccount = api.findById(Long.valueOf(1)).get();
		assertEquals(account.getAccount_no(), testAccount.getAccount_no());
		assertEquals(account.getAccount_type(), testAccount.getAccount_type());
		assertEquals(account.getFirst_name(), testAccount.getFirst_name());
		assertEquals(account.getLast_name(), testAccount.getLast_name());
		assertEquals(account.getAge(), testAccount.getAge());
		assertEquals(account.getAddress(), testAccount.getAddress());
		assertEquals(account.getContact(), testAccount.getContact());
		assertEquals(account.getEmail(), testAccount.getEmail());
		assertEquals(account.getBalance(), testAccount.getBalance());
		
	}
	
	@Test
	void getAllAccountsTest() {
		
		List<Account> accounts = TestData.getAccounts(3);
		
		for(Account acc : accounts) {
			api.save(acc);
		}
		
		List<Account> testAccounts = new ArrayList<Account>();
		
		api.findAll().forEach(testAccounts :: add);
		
		assertEquals(accounts.size(), testAccounts.size());
		
	}
	
	@Test
	void deleteAllAccountTest() {
		List<Account> accounts = TestData.getAccounts(3);
		
		for(Account acc : accounts) {
			api.save(acc);
		}
		
		api.deleteAll();
		
		List<Account> testAccounts = new ArrayList<Account>();
		api.findAll().forEach(testAccounts :: add);
		assertEquals(0, testAccounts.size());
	}

	@Test
	void deleteAccountByIdTest() {
		List<Account> accounts = TestData.getAccounts(3);

		for (Account acc : accounts) {
			api.save(acc);
		}
		
		api.deleteById(Long.valueOf(2));
		List<Account> testAccounts = new ArrayList<Account>();
		api.findAll().forEach(testAccounts :: add);
		assertEquals(2, testAccounts.size());
	}
}
