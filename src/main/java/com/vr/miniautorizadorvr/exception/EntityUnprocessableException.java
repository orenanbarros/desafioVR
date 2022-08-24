package com.vr.miniautorizadorvr.exception;

public class EntityUnprocessableException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	public EntityUnprocessableException(String msg) {
		super(msg);
	}

	public EntityUnprocessableException() {
		super();
		
	}

	

}
