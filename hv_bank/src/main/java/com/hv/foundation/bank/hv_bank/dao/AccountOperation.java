package com.hv.foundation.bank.hv_bank.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hv.foundation.bank.hv_bank.model.Account;

@Repository
public interface AccountOperation extends CrudRepository<Account, Long>{
	
}
