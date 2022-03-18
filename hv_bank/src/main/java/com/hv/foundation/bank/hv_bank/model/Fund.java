package com.hv.foundation.bank.hv_bank.model;

public class Fund {
	private long sender_id;
	private long receiver_id;
	private double amount;
	
	public Fund() {
	}

	public Fund(long sender_id, long receiver_id, double amount) {
		super();
		this.sender_id = sender_id;
		this.receiver_id = receiver_id;
		this.amount = amount;
	}

	public long getSender_id() {
		return sender_id;
	}

	public void setSender_id(long sender_id) {
		this.sender_id = sender_id;
	}

	public long getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(long receiver_id) {
		this.receiver_id = receiver_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Fund [sender_id=" + sender_id + ", receiver_id=" + receiver_id + ", amount=" + amount + "]";
	}
}
