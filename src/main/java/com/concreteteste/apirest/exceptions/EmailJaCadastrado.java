package com.concreteteste.apirest.exceptions;

import org.springframework.http.HttpStatus;

public class EmailJaCadastrado extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;
	public static final String MSG = "E-mail já foi cadastrado";
	
	public EmailJaCadastrado(HttpStatus httpStatus) {
		super(MSG);
	}
	
	
	
	
}
