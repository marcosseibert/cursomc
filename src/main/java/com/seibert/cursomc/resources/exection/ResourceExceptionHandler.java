package com.seibert.cursomc.resources.exection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.seibert.cursomc.services.exception.DataIntegrityViolationException;
import com.seibert.cursomc.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> constraitViolation(DataIntegrityViolationException e, HttpServletRequest request){
		
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
