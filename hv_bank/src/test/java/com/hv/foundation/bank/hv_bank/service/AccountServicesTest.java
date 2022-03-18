package com.hv.foundation.bank.hv_bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hv.foundation.bank.hv_bank.dao.AccountOperation;
import com.hv.foundation.bank.hv_bank.model.Account;
import com.hv.foundation.bank.hv_bank.util.TestData;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class AccountServicesTest {
	
	@InjectMocks
	AccountServices service;
	
	@Mock
	AccountOperation api;
	
	
	@Test
	void testGetAllAccounts() {
		
		//getting test data from another class...
		List<Account> accounts = TestData.getAccounts(3);
		
		Mockito.when(api.findAll()).thenReturn(accounts);
		
		
		List<Account> accountTestList = service.getAllAccounts();
		
		assertEquals(3, accountTestList.size());
		
	}

	@Test
	void testCreateAccount() {
 
		Account account = TestData.getAccount(Long.valueOf(1));
		service.createAccount(account);
		Mockito.verify(api).save(account);
	}

	@Test
	void testGetAccountByNo() {
		
		Account account = TestData.getAccount(Long.valueOf(1));
		Mockito.when(api.findById(Long.valueOf(1))).thenReturn(Optional.of(account));
		
		Account testAccount = service.getAccountByNo(Long.valueOf(1));
		
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
	void testDeleteAllAccounts() {
		try {
		service.deleteAllAccounts();
		}catch (NullPointerException e) {
			Mockito.verify(api).deleteAll();
		}	
	}

	@Test
	void testDeleteAccountByNo() {
		long id = Long.valueOf(1);
		service.deleteAccountByNo(id);
		Mockito.verify(api).deleteById(id);
	}

	@Test
	void testUpdateByAccountNo() {
		Account account = TestData.getAccount(Long.valueOf(1));
		service.updateByAccountNo(Long.valueOf(1), account);
		Mockito.verify(api).save(account);
	}

}
