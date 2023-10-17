package com.eyup.library.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class DefaultExceptionHandler {

	 @ExceptionHandler(UserNotFoundException.class)
	    public ResponseEntity<ApiError> handle(UserNotFoundException userNotFoundException,HttpServletRequest request){
		 ApiError apiError = new ApiError(
	                request.getRequestURI(),
	                userNotFoundException.getMessage(),
	                HttpStatus.NOT_FOUND.value(),
	                LocalDateTime.now()
	        );
	        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	    }
	
	 @ExceptionHandler(AuthorNotFoundException.class)
	 public ResponseEntity<ApiError> handle(AuthorNotFoundException authorNotFoundException,HttpServletRequest request){
		 ApiError apiError = new ApiError(
	                request.getRequestURI(),
	                authorNotFoundException.getMessage(),
	                HttpStatus.NOT_FOUND.value(),
	                LocalDateTime.now()
	        );
		 return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler(BookNotFoundException.class)
	 public ResponseEntity<ApiError> handle(BookNotFoundException bookNotFoundException,HttpServletRequest request){
		 ApiError apiError = new ApiError(
	                request.getRequestURI(),
	                bookNotFoundException.getMessage(),
	                HttpStatus.NOT_FOUND.value(),
	                LocalDateTime.now()
	        );
		 return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	 }
	 
	 @ExceptionHandler(ReservedNotFoundException.class)
	 public ResponseEntity<ApiError> handle(ReservedNotFoundException reservedNotFoundException,HttpServletRequest request){
		 ApiError apiError = new ApiError(
	                request.getRequestURI(),
	                reservedNotFoundException.getMessage(),
	                HttpStatus.NOT_FOUND.value(),
	                LocalDateTime.now()
	        );
		 return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
	 }
}
