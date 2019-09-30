//USER INTERFACE

package com.capgemini.ui;

import java.util.Scanner;
import com.capgemini.service.BankService;
import com.capgemini.service.BankServiceImpl;
import com.capgemini.validation.*;

//import com.capgemini.dao.BankApplicationDao;
//import com.capgemini.dao.BankApplicationDaoImpl;
import com.capgemini.model.User;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		BankService service = new BankServiceImpl();
		String username = null;
		String password = null;

		double amount;
		String contact_no;
		double balance;

		System.out.println("****WELCOME TO BANKING SERVICES*****");
		boolean flag1 = true;
		do {
			System.out.println("Enter your choice:");
			System.out.println("1.Create Account \n 2.Login \n 3.Exit");
			int ch = sc.nextInt();
			switch (ch) {

			// When user selects option 1,it creates new user account
			case 1:
				System.out.println("Enter name:");
				String name = sc.nextLine();
				sc.nextLine();
				boolean res1;
				do {
					res1 = false;
					System.out.println("Enter contact no:");
					contact_no = sc.next();
					sc.nextLine();
					boolean res = service.validatePhone(contact_no);
					if (!res) {
						res1 = true;
						System.out.println("Contact number should be 10 digits long..Try again");

					}
				} while (res1);
				boolean res = false, res_ = false;

				System.out.println("Enter email id:");
				String email_id = sc.nextLine();
				boolean flag = false;
				boolean flag_=false;

				do {
					try {
						System.out.println("Enter username:");
						System.out.println("(Username should be between 6 and 12 characters long)");
						username = sc.nextLine();
						res = service.validateUsername(username);

						System.out.println("Enter password:");
						System.out.println("(Password should be between 5 and 10 characters long)");
						password = sc.nextLine();
						res_ = service.validatePassword(password);
						
						if(res==false && res_==false) {
							flag=true;
							flag_=true;}

					} catch (UsernameException e) {

						System.out.println("Invalid username..Please try again");
						 
					} catch (PasswordValidation e) {

						System.out.println("Invalid password..Please try again");
						
					}
				} while (flag == false || flag_ == false);

				boolean res2;
				do {
					res2 = false;
					System.out.println("Enter balance:");
					balance = sc.nextDouble();
					res = service.validateBalance(balance);
					if (!res) {
						res2 = true;
						System.out.println("Balance can not be less than zero.. Please try again");
					}

				} while (res2);

				User user = new User(username, password, contact_no, email_id, name, balance);
				int b = service.createAccount(user);
				if (b >= 1)
					System.out.println("Employee added");
				else
					System.out.println("Employee not added");
				break;

			// When user selects option2, Login option

			case 2:
				System.out.println("Enter username:");
				username = sc.next();

				System.out.println("Enter password:");
				String password1 = sc.next();
				boolean status = service.loginValidate(username, password1);
				if (!status)
					System.out.println("Sorry..data not found");

				// After successful login
				else {
					System.out.println("Login successful");
					System.out.println("Enter the service you want");
					System.out.println("1. Show balance");
					System.out.println("2. Deposit money");
					System.out.println("3. Withdraw money");
					System.out.println("4. Fund transfer");
					System.out.println("5. Print Transaction");
					int choice = sc.nextInt();

					// For displaying balance
					if (choice == 1) {

						double acct_balance = service.showBalance(username, password1);
						System.out.println("Account balance:" + acct_balance);
					}

					// To deposit amount
					else if (choice == 2) {
						System.out.println("Enter amount");
						amount = sc.nextDouble();
						double deposit = service.deposit(username, password1, amount);
						System.out.println("Updated balance:" + deposit);

						// To withdraw amount
					} else if (choice == 3) {
						System.out.println("Enter amount");
						amount = sc.nextDouble();

						double withdraw = service.withdraw(username, password1, amount);
						System.out.println("Updated balance:" + withdraw);
					}

					// To transfer fund

					else if (choice == 4) {
						System.out.println("Enter amount");
						amount = sc.nextDouble();
						sc.nextLine();
						System.out.println("Enter the username in which amount is to be transferred");
						String username1 = sc.nextLine();
						boolean fund = service.fundTransfer(username, password1, amount, username1);
						if (fund) {
							System.out.println("Amount has been tranferred successfully.");
							double amount1 = service.showBalance(username, password1);
							System.out.println("Updated balance:" + (amount1));
						}

						else
							System.out.println("Insuffcient balance");
					}
					
					else if (choice == 5) {
						service.printTransaction(username, password);
					}
				}
				break;

			case 3:
				System.out.println("***************application closed***************");
				flag1 = false;
				System.exit(0);
			}
		} while (flag1 == true);
	}
}