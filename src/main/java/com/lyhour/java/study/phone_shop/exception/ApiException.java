package com.lyhour.java.study.phone_shop.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class ApiException extends RuntimeException{

	private final HttpStatus status;
	private final String message;
	

}
