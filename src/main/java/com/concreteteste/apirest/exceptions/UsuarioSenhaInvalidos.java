package com.concreteteste.apirest.exceptions;

import org.springframework.http.HttpStatus;

public class UsuarioSenhaInvalidos extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	private static final String MSG = "Usuário e/ou senha inválidos";
	
	public UsuarioSenhaInvalidos ( HttpStatus httpStatus) {
		super(MSG);
	}
	
	

}
