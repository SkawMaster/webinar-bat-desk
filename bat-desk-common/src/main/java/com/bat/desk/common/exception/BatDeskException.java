package com.bat.desk.common.exception;

import com.bat.architecture.common.constant.GlobalConstant;
import com.bat.architecture.common.exception.BatException;

public class BatDeskException extends BatException {

	private static final long serialVersionUID = -3357600780764229808L;

	public BatDeskException(String header) {
		this(header, GlobalConstant.EMPTY_STRING);
	}
	
	public BatDeskException(String header, String message) {
		super(new StringBuilder(header).append(message).toString());
	}

}
