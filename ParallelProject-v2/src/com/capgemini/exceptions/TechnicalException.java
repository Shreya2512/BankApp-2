package com.capgemini.exceptions;

public class TechnicalException extends RuntimeException {

	public TechnicalException(Exception e, String msg) {
		super(msg, e);
	}

}
