package com.capgemini.validation;

public class UsernameException extends Exception
{
	private String username;

	public UsernameException(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Username not valid";
	}
	
	
}
