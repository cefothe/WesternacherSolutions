package com.westernacher.solutions.usermanagement.rest.handler;

import javax.persistence.EntityNotFoundException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class HttpExceptionHandlers {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> entityNotFoundExpection(Exception e){
		log.info(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
