//Model class of User

package com.capgemini.model;

public class User {

	//Declaring attributes
	private String username;
	private String password;
	private String contact_no;
	private String email_id;
	private String name;
	private double balance;
	
	//Default constructor of User class
	public User() {}
	
	//Parameterized constructor of user class
	public User(String username, String password, String contact_no, String email_id, String name, double balance) {
		super();
		this.username = username;
		this.password = password;
		this.contact_no = contact_no;
		this.email_id = email_id;
		this.name = name;
		this.balance=balance;
	}
	
	
	//Getters and setters
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
	
}
