package com.jarierca.gamevault.exceptions.jwt;

public class JwtException extends RuntimeException {

	private static final long serialVersionUID = 5035458106289876009L;

	public JwtException(String message) {
		super(message);
	}

	public JwtException(String message, Throwable cause) {
		super(message, cause);
	}
}
