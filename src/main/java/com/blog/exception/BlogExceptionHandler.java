package com.blog.exception;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		Map<String, String> errorsMap = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(err -> {
			String fieldName = ((FieldError) err).getField();
			errorsMap.put(fieldName, err.getDefaultMessage());
		});
		return new ResponseEntity<>(errorsMap , HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<ExceptionResponse> fileNotFoundExceptionHandler(FileNotFoundException ex) {
		return new ResponseEntity<>(ExceptionResponse.builder()
				.message(ex.getMessage()).code(BlogApplicationConstant.ERROR_CODE_1).build(), HttpStatus.NOT_FOUND);
	}
}
