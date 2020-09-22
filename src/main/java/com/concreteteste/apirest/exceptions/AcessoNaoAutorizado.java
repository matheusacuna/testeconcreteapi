package com.concreteteste.apirest.exceptions;

import org.springframework.http.HttpStatus;

public class AcessoNaoAutorizado extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;
	private static final String MSG = "Acesso não autorizado";
	
	public AcessoNaoAutorizado(HttpStatus httpStatus) {
		super(MSG);
	}
}
