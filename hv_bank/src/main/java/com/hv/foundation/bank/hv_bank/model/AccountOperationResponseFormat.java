package com.hv.foundation.bank.hv_bank.model;

public class AccountOperationResponseFormat {

	private int status;
	private String message;
	private long account_number;
	
	public AccountOperationResponseFormat() {
	}
	public AccountOperationResponseFormat(int status, String message, long account_number) {
		super();
		this.status = status;
		this.message = message;
		this.account_number = account_number;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
	public long getAccount_number() {
		return account_number;
	}
	public void setAccount_number(long account_number) {
		this.account_number = account_number;
	}
	@Override
	public String toString() {
		return "AccountOperationResponseFormat [status=" + status + ", message=" + message + ", account_number="
				+ account_number + "]";
	}
	
}
