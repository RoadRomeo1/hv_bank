package com.hv.foundation.bank.hv_bank.model;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Account {
	@Transient
	private static int id = 10;
	@Id
	@Column
	private Long account_no;
	@Column
	private String account_type;
	@Column
	private String first_name;
	@Column
	private String last_name;
	@Column
	private int age;
	@Column 
	private String address;
	@Column
	private long contact;
	@Column 
	private String email;
	@Column
	private double balance;
	
	public Account() {
	}
	
	public Account(Long account_no, String account_type, String first_name, String last_name, int age, String address,
			long contact, String email, double balance) {
		super();
		this.account_no = account_no;
		this.account_type = account_type;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.address = address;
		this.contact = contact;
		this.email = email;
		this.balance = balance;
	}

	public Account(String account_type, String first_name, String last_name, int age, String address,
			long contact, String email, double balance) {
		super();

		this.account_type = account_type;
		this.first_name = first_name;
		this.last_name = last_name;
		this.age = age;
		this.address = address;
		this.contact = contact;
		this.email = email;
		this.balance = balance;
	}
	public Long getAccount_no() {
		return account_no;
	}
	public void setAccount_no(Long account_no) {
		this.account_no = account_no;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public long generateAccountNo() {
		Random random = new Random();
		int max = 999999;
		int min = 100000;
		int no = random.nextInt((max - min) + 1) + min;
		
		String noStr = String.valueOf(no).concat(Integer.toString(Account.id));
		Account.id++;
		return	Long.valueOf(noStr);
	}

	@Override
	public String toString() {
		return "Account [account_no=" + account_no + ", account_type=" + account_type + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", age=" + age + ", address=" + address + ", contact=" + contact
				+ ", email=" + email + ", balance=" + balance + "]";
	}

}
