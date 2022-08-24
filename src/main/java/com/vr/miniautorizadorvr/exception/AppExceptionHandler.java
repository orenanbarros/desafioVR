package com.vr.miniautorizadorvr.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<VrCardErrorMessage> handleNotFoundException(EntityNotFoundException e, HttpServletRequest request){
				
		VrCardErrorMessage vrCardErrorMessage = new VrCardErrorMessage(HttpStatus.NOT_FOUND.value(),e.getMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(vrCardErrorMessage);
		
	}
	
	@ExceptionHandler(EntityUnprocessableException.class)
	public ResponseEntity<VrCardErrorMessage> handleUnprocessableException(EntityUnprocessableException e, HttpServletRequest request){
				
		VrCardErrorMessage vrCardErrorMessage = new VrCardErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(),e.getMessage());
				
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(vrCardErrorMessage);
		
	}
}
