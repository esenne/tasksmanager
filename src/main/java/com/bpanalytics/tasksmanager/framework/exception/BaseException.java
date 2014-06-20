package com.bpanalytics.tasksmanager.framework.exception;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 5565505910871254109L;

	private Object[] arguments;
	
	public BaseException(String message, Object[] arguments, Throwable cause) {
		super(message, cause);
		this.arguments = arguments;
	}

	public BaseException(String message, Object[] arguments) {
		super(message);
		this.arguments = arguments;
	}
	
	public Object[] getArguments() {
		return arguments;
	}

}
