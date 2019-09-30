// Dao Interface 

package com.capgemini.dao;

import com.capgemini.model.Transaction;
import com.capgemini.model.User;

public interface BankApplicationDao {

	// Declaration of methods

	public double get_Balance(String username, String password);

	public double amt_deposit(String username, String password, double amount);

	public double amt_withdraw(String username, String password, double amount);

	public boolean fund_Transfer(String username, String password, double amount, String username1);

	public int createUserAccount(User user);

	public void get_Transaction(String username, String password);

	public boolean loginValidate(String username, String password);
}
