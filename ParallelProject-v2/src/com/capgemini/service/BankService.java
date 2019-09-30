//Service layer Interface

package com.capgemini.service;

import com.capgemini.model.User;
import com.capgemini.validation.PasswordValidation;
import com.capgemini.validation.UsernameException;

public interface BankService {

	public double showBalance(String username, String password); // To show balance

	public double deposit(String username, String password, double amount); // To deposit amount

	public double withdraw(String username, String password, double amount); // To withdraw money

	public boolean fundTransfer(String username, String password, double amount, String username1); //To transfer fund

	public void printTransaction(String username, String password);    //Print transaction details

	public int createAccount(User u);   //Create new user account

	
	//validation methods 
	
	boolean validatePhone(String contact_no);

	public boolean validateBalance(double balance);

	public boolean validateUsername(String username) throws UsernameException;

	public boolean loginValidate(String username, String password1);

	public boolean validatePassword(String password) throws PasswordValidation;

}
