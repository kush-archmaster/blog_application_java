package com.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.constants.BlogApplicationConstant;
import com.blog.dtos.ExceptionResponse;

@RestControllerAdvice
public class BlogExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		return new ResponseEntity<>(ExceptionResponse.builder()
				.message(ex.getMessage()).code(BlogApplicationConstant.ERROR_CODE).build(), HttpStatus.NOT_FOUND);
	}
}
