//Dao layer interface

package com.capgemini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.capgemini.exceptions.TechnicalException;
import com.capgemini.model.User;
import com.capgemini.model.Transaction;
import com.capgemini.model.User;
import com.capgemini.utils.DatabaseUtils;

public class BankApplicationDaoImpl implements BankApplicationDao {

	User u = new User();
	double bal;
	String str;
	private DatabaseUtils dbUtil = null;

	public BankApplicationDaoImpl() {

		dbUtil = DatabaseUtils.getInstance();

	}

	// Implementing methods of DAO interface by overriding
	
	
	//Method to retrieve balance 
	@Override
	public double get_Balance(String username, String password) {

		try {
			Connection connection = dbUtil.openDatabaseConnections();
			String query = "select u_salary from Bank" + " where u_name='" + username + "' and password='" + password
					+ "'";
			Statement pstmt = connection.createStatement();
			ResultSet rs = pstmt.executeQuery(query);

			while (rs.next()) {

				double balance = rs.getDouble("u_salary");
				bal = balance;

			}

			dbUtil.closeDatabaseConnections();
		} catch (SQLException | ClassNotFoundException e) {
			TechnicalException te = new TechnicalException(e,
					"There is some technical issue while opening/closing connection");
			throw te;
		}
		return bal;

	}

	
	//Method to deposit money in account
	@Override
	public double amt_deposit(String username, String password, double amount) {

		try {
			Connection connection = dbUtil.openDatabaseConnections();
			String sql = "UPDATE Bank " + "SET u_salary = u_salary+" + amount + " where u_name='" + username
					+ "' and password='" + password + "'";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			int result = pstmt.executeUpdate(sql);
			String query = "select u_salary from Bank" + " where u_name='" + username + "' and password='" + password
					+ "'";
			ResultSet rs = pstmt.executeQuery(query);

			while (rs.next()) {

				double balance = rs.getDouble("u_salary");
				bal = balance;

			}
			sql = "INSERT INTO Transaction(Transaction_type,amount,balance)" + "VALUES(?,?,?)";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.setDouble(2, amount);
			pstmt.setDouble(3, bal);
			pstmt.executeUpdate();

			dbUtil.closeDatabaseConnections();

		} catch (SQLException | ClassNotFoundException e) {
			TechnicalException te = new TechnicalException(e,
					"There is some technical issue while opening/closing connection");
			throw te;
		}
		return bal;

	}
	
	//Method to withdraw money

	@Override
	public double amt_withdraw(String username, String password, double amount) {

		str = "Withdraw";
		boolean flag = true;

		try {
			Connection connection = dbUtil.openDatabaseConnections();
			String query = "select u_salary from Bank" + " where u_name='" + username + "' and password='" + password
					+ "'";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			while (rs.next()) {
				double balance = rs.getDouble("u_salary");
				if (balance < amount) {
					System.out.println("Not enough balance");
					bal = balance;
				} else {
					String sql = "UPDATE Bank " + "SET u_salary = u_salary-" + amount + " where u_name='" + username
							+ "' and password='" + password + "'";
					pstmt = connection.prepareStatement(sql);
					int result = pstmt.executeUpdate(sql);
				}
				query = "select u_salary from Bank" + " where u_name='" + username + "' and password='" + password + "'";
				rs = pstmt.executeQuery(query);

				while (rs.next()) {

					balance = rs.getDouble("u_salary");
					bal = balance;
				}
			}
			String sql = "INSERT INTO Transaction(Transaction_type,amount,balance)" + "VALUES(?,?,?)";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.setDouble(2, amount);
			pstmt.setDouble(3, bal);
			pstmt.executeUpdate();
			dbUtil.closeDatabaseConnections();

		} catch (SQLException | ClassNotFoundException e) {
			TechnicalException te = new TechnicalException(e,
					"There is some technical issue while opening/closing connection");
			throw te;
		}
		return bal;

	}

	//Method to transfer fund to another account
	
	public boolean fund_Transfer(String username, String password, double amount, String username1) {

		str = "Fund Transfer";
		boolean flag = true;
		try {
			Connection connection = dbUtil.openDatabaseConnections();
			String query = "select u_salary from Bank" + " where u_name='" + username + "' and password='" + password
					+ "'";
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			while (rs.next()) {
				double balance = rs.getDouble("u_salary");
				if (balance < amount)
					flag = false;
				else {
					String sql = "UPDATE Bank " + "SET u_salary = u_salary-" + amount + " where u_name='" + username
							+ "' and pswrd='" + password + "'";
					int result = pstmt.executeUpdate(sql);
					String sql1 = "UPDATE Bank " + "SET u_salary = u_salary+" + amount + " where u_name='" + username1
							+ "'";

					result = pstmt.executeUpdate(sql1);
					String query1 = "select u_salary from Bank" + " where u_name='" + username + "' and password='"
							+ password + "'";
					rs = pstmt.executeQuery(query1);

					while (rs.next()) {

						balance = rs.getDouble("u_salary");
						bal = balance;

					}

				}
			}
			String sql = "INSERT INTO Transaction(Transaction_type,amount,balance)" + "VALUES(?,?,?)";

			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, str);
			pstmt.setDouble(2, amount);
			pstmt.setDouble(3, bal);
			pstmt.executeUpdate();

			dbUtil.closeDatabaseConnections();

		} catch (SQLException | ClassNotFoundException e) {
			TechnicalException te = new TechnicalException(e,
					"There is some technical issue while opening/closing connection");
			throw te;
		}
		return flag;
	}
	
	//Method to create new user account

	@Override
	public int createUserAccount(User user) {

		try {
			Connection connection = dbUtil.openDatabaseConnections();
			String query = "Insert into Bank (u_name,password,phone_no,email,name,u_salary) values (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(query);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getContact_no());
			pstmt.setString(4, user.getEmail_id());
			pstmt.setString(5, user.getName());
			pstmt.setDouble(6, user.getBalance());

			int result = pstmt.executeUpdate();
			dbUtil.closeDatabaseConnections();
			if (result >= 1) {
				return result;
			}

		} catch (SQLException | ClassNotFoundException e) {
			TechnicalException te = new TechnicalException(e,
					"There is some technical issue while opening/closing connection");
			throw te;
		}
		return 0;
	}

	//To print transaction details
	
	@Override
	public void get_Transaction(String username, String password) {

		try {
			Connection connection = dbUtil.openDatabaseConnections();
			String query = "select * from Transaction where transaction_type is NOT NULL";

			Statement pstmt = connection.createStatement();
			ResultSet rs = pstmt.executeQuery(query);

			while (rs.next()) {

				String type = rs.getString("Transaction_type");
				double amount = rs.getDouble("Amount");
				double balance = rs.getDouble("Balance");
				System.out.println(type + "   " + amount + "    " + balance);

			}
			query = "Delete from transaction";
			pstmt.executeUpdate(query);
			dbUtil.closeDatabaseConnections();
		} catch (SQLException | ClassNotFoundException e) {
			TechnicalException te = new TechnicalException(e,
					"There is some technical issue while opening/closing connection");
			throw te;
		}
	}
	
	//to validate login details

	@Override
	public boolean loginValidate(String username, String password) {

		boolean flag = true;
		try {
			Connection connection = dbUtil.openDatabaseConnections();
			String query = "select u_name from Bank" + " where u_name='" + username + "' and password='" + password + "'";
			Statement pstmt = connection.createStatement();
			ResultSet rs = pstmt.executeQuery(query);

			if (!rs.next())
				flag = false;

			dbUtil.closeDatabaseConnections();
		} catch (SQLException | ClassNotFoundException e) {
			TechnicalException te = new TechnicalException(e,
					"There is some technical issue while opening/closing connection");
			throw te;
		}

		return flag;
	}

}
