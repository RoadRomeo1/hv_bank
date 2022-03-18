package com.hv.foundation.bank.hv_bank.service;


import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hv.foundation.bank.hv_bank.dao.AccountOperation;
import com.hv.foundation.bank.hv_bank.model.Account;
import com.hv.foundation.bank.hv_bank.model.AccountOperationResponseFormat;
import com.hv.foundation.bank.hv_bank.model.Fund;
import com.hv.foundation.bank.hv_bank.util.TestData;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class FundTransferSerivceTest {

	@InjectMocks
	FundTransferSerivce service;
	
	@Mock
	AccountOperation api;
	
	
	@Test
	void testTransferAmount() {
		
		Account sender = TestData.getAccount(Long.valueOf(123));
		Account receiver = TestData.getAccount(Long.valueOf(456));
		
		Fund fund = new Fund(sender.getAccount_no(), receiver.getAccount_no(), Double.valueOf(100));
		
		AccountOperationResponseFormat responseFormat = service.TransferAmount(fund);
		
		if (responseFormat.getMessage() == "success") {
			
		}
		else {
			fail("Test Case Failed");
		}
	}

}
