package com.bat.architecture.common.exception;

public class BatException extends Exception {

	private static final long serialVersionUID = 5727679515527139859L;

	public BatException(String message) {
		super(message);
	}

	public BatException(Throwable cause) {
		super(cause);
	}

	public BatException(String message, Throwable cause) {
		super(message, cause);
	}

}
