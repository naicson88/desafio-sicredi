package com.sicredi.desafio.exceptions;

import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
		
	ZonedDateTime time =  ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
	
	Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);
			
		@ExceptionHandler(value = {Exception.class})
		public ResponseEntity<ApiExceptions> handleExceptionError(Exception e) {
			ApiExceptions ex = new ApiExceptions(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, this.time);	
			logger.error("Exception: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
		}
		
		@ExceptionHandler(value = {IllegalArgumentException.class})
		public ResponseEntity<Object> handleValiationException(IllegalArgumentException e){			
			ApiExceptions ex = new ApiExceptions(e.getMessage(), 400,  HttpStatus.BAD_REQUEST, this.time);	
			logger.error("IllegalArgumentException: "+ e.getMessage());
			return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(value = {NoSuchElementException.class})
		public ResponseEntity<Object> handleNotFoundlErros(NoSuchElementException e){	
			ApiExceptions ex = new ApiExceptions(e.getMessage(),  HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));	
			logger.error("NoSuchElementException: " + e.getMessage());
			return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(value = {EntityNotFoundException.class})
		public ResponseEntity<Object> handleEntityNotFoundlErros(EntityNotFoundException e){			
			ApiExceptions ex = new ApiExceptions(e.getMessage(),  HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));	
			logger.error("EntityNotFoundException: " + e.getMessage());
			return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(value = {RuntimeException.class})
		public ResponseEntity<Object> handleRuntimeException(RuntimeException re){
			ApiExceptions ex = new ApiExceptions("Algo deu errado, verifique os dados informados: " + re.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, this.time);
			logger.error("RuntimeException: " + re.getMessage());
			
			return new ResponseEntity<Object>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@ExceptionHandler(value = {ErrorMessage.class})
		public ResponseEntity<Object> handleErrorMessage(ErrorMessage rm){
			ApiExceptions ex = new ApiExceptions(rm.getMsg(), rm.getHttpStatus().value(),  rm.getHttpStatus(), this.time);
			logger.error("Message : " + rm.getMsg());
			
			return new ResponseEntity<Object>(ex, rm.getHttpStatus());
		}
	}
