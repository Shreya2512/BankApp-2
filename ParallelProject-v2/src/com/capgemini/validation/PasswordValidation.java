package com.capgemini.validation;

public class PasswordValidation extends Exception{
	private String password;

	public PasswordValidation(String password) {
		
		this.password = password;
	}

	@Override
	public String toString() {
		return "Invalid password";
	}
	
}
