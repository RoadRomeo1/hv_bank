package com.hv.foundation.bank.hv_bank.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hv.foundation.bank.hv_bank.model.Account;
import com.hv.foundation.bank.hv_bank.model.AccountOperationResponseFormat;
import com.hv.foundation.bank.hv_bank.model.Fund;
import com.hv.foundation.bank.hv_bank.service.AccountServices;
import com.hv.foundation.bank.hv_bank.service.FundTransferSerivce;
import com.hv.foundation.bank.hv_bank.util.TestData;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FrontControllerTest {
	
	@MockBean
	private AccountServices service;
	@MockBean
	private FundTransferSerivce fundService;
	
	@InjectMocks
	FrontController controller;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	void testFetchAllAccounts() throws Exception{
		
		Account account = TestData.getAccount(Long.valueOf(1));
		List<Account> accounts = Arrays.asList(account);
				
		Mockito.when(service.getAllAccounts()).thenReturn(accounts);
		
		
		mvc.perform(get("/bank"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(1)))
				.andExpect(jsonPath("$[0].first_name", Matchers.is("test")));
	}

	@Test
	void testFetchAllAccountByNo() throws Exception{
		
		Account account = TestData.getAccount(Long.valueOf(1));
		
		Mockito.when(service.getAccountByNo(Long.valueOf(1))).thenReturn(account);
		
		mvc.perform(get("/bank/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.first_name", Matchers.is("test")))
		.andExpect(jsonPath("$.account_no", Matchers.is(1)));
		
	}

	@Test
	void testSaveAccount() throws Exception{
		Account account = TestData.getAccount(Long.valueOf(1));
		
		AccountOperationResponseFormat response = new  AccountOperationResponseFormat(HttpStatus.CREATED.value(), "Account is created.",account.getAccount_no());
		
		Mockito.when(service.createAccount(account)).thenReturn(response);
		
		mvc.perform(post("/bank")
				.contentType("application/json")
				.content(new ObjectMapper().writeValueAsString(account)))
		.andExpect(status().isCreated());
	}

	@Test
	void testAlterAccountByNo() throws Exception{
		Account account = TestData.getAccount(Long.valueOf(1));
		
		AccountOperationResponseFormat response = new AccountOperationResponseFormat(HttpStatus.OK.value(), "Account details is updated", account.getAccount_no());
				
		Mockito.when(service.updateByAccountNo(account.generateAccountNo(), account)).thenReturn(response);		
		
		mvc.perform(put("/bank/1")
				.contentType("application/json")
				.content(new ObjectMapper().writeValueAsString(account)))
		.andExpect(status().isOk());
	}

	@Test
	void testRemoveAllAccounts() throws Exception{
		
		AccountOperationResponseFormat response = new AccountOperationResponseFormat(HttpStatus.OK.value(), "All data is deleted", 0); 
		
		Mockito.when(service.deleteAllAccounts()).thenReturn(response);
		
		MvcResult res = mvc.perform(delete("/bank"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status", Matchers.is(response.getStatus())))
		.andExpect(jsonPath("$.message", Matchers.is(response.getMessage()))).andReturn();
		
		String responseBody = res.getResponse().getContentAsString();
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(new ObjectMapper().writeValueAsString(response));
	}

	@Test
	void testRemoveAccountsByNo() throws Exception{
		
		Account account = TestData.getAccount(Long.valueOf(1));
		AccountOperationResponseFormat response = new AccountOperationResponseFormat(HttpStatus.OK.value(), "Account is removed", account.getAccount_no());
		
		Mockito.when(service.deleteAccountByNo(account.getAccount_no())).thenReturn(response);
		
		MvcResult res =  mvc.perform(delete("/bank/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status", Matchers.is(response.getStatus())))
		.andExpect(jsonPath("$.message", Matchers.is(response.getMessage()))).andReturn();
	
		String responseBody = res.getResponse().getContentAsString();

		assertThat(responseBody).isEqualToIgnoringWhitespace(new ObjectMapper().writeValueAsString(response));
	}

	@Test
	void testSendMoney() throws Exception{
		
		AccountOperationResponseFormat response = new AccountOperationResponseFormat(HttpStatus.OK.value(), "Fund transfer successful", 0);
		
		Fund fund = new Fund(Long.valueOf(1), Long.valueOf(2), 100.0);
		Mockito.when(fundService.TransferAmount(fund)).thenReturn(response);
		
		mvc.perform(post("/bank/send")
				.contentType("application/json")
				.content(new ObjectMapper().writeValueAsString(fund)))
		.andExpect(status().isOk());
	}

}
