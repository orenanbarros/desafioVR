package com.vr.miniautorizadorvr.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

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
	

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                  HttpHeaders headers, HttpStatus status, WebRequest request) {
           String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
                   
           VrCardErrorMessage vrCardErrorMessage = new VrCardErrorMessage(HttpStatus.BAD_REQUEST.value(),errorMessage);
           return new ResponseEntity<>(vrCardErrorMessage, status);
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)

    protected ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
                  HttpHeaders headers, HttpStatus status, WebRequest request) {
           String errorMessage = ex.getMessage();
                   
           VrCardErrorMessage vrCardErrorMessage = new VrCardErrorMessage(HttpStatus.BAD_REQUEST.value(),errorMessage);
           return new ResponseEntity<>(vrCardErrorMessage, status);
    }
	
	
	
	
	
}
