package com.masai.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	 
	@ExceptionHandler(BenificiaryException.class)
	public ResponseEntity<MyErrorDetails> handleStudentException(BenificiaryException exp, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(WalletException.class)
	public ResponseEntity<MyErrorDetails> handleStudentException(WalletException exp, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(BillPaymentException.class)
	public ResponseEntity<MyErrorDetails> handleEmployeeException(BillPaymentException exp, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> myExceptionHandler(Exception e, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(true));
		ResponseEntity<MyErrorDetails> re = new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		return re;

	}

	// to handle Not found exception(for Uri Exception)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> mynotFoundHandler(NoHandlerFoundException nfe, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), nfe.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<MyErrorDetails> handleTransactionException(TransactionException exp, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), exp.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	
	//field validation Exception handler
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> myMANVExceptionHandler(MethodArgumentNotValidException ex) {
		
		Map<String, String> errorBody = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			
			String fieldName = ((FieldError)error).getField();
			
			String message = error.getDefaultMessage();
			
			errorBody.put(fieldName, message);
			
		});
		
		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}
}
