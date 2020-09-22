package com.concreteteste.apirest.exceptions;

import org.springframework.http.HttpStatus;

public class SessaoInvalida extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	private static final String MSG = "Senha inválida";
	
	public SessaoInvalida (HttpStatus httpStatus) {
		super(MSG);
	}
	
	

}
