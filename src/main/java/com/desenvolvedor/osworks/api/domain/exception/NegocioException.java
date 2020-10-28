package com.desenvolvedor.osworks.api.domain.exception;

public class NegocioException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public NegocioException(String message) {
		super(message); // O que receber da exception (message) ele passa para a class super no caso a class RuntimeException
	}
	
}
