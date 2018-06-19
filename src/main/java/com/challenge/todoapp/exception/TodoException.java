package com.challenge.todoapp.exception;

public class TodoException extends Exception {

	public TodoException(String message) {
		super(message);
	}

	public TodoException(String message, Throwable e) {
		super(message, e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5847916807910100625L;

}
