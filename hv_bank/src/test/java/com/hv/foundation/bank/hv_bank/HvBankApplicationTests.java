package com.hv.foundation.bank.hv_bank;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hv.foundation.bank.hv_bank.controller.FrontController;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class HvBankApplicationTests {

	@Autowired
	FrontController controller;
	
	@Test
	void contextLoads() throws Exception{
		assertThat(controller).isNotNull();	
	}

}
