package com.vr.miniautorizadorvr.exception;

import java.io.Serializable;

public class VrCardErrorMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codeError;
	private String message;
	
	
	public VrCardErrorMessage() {
	}
	
	public VrCardErrorMessage(int codeError, String message) {
		this.codeError = codeError;
		this.message = message;
	}
	
	public int getCodeError() {
		return codeError;
	}
	public void setCodeError(int codeError) {
		this.codeError = codeError;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
