package com.bpanalytics.tasksmanager.framework.exception;

public class BusinessException extends BaseException {

	private static final long serialVersionUID = 6407758857809447315L;
	
	private Status status = Status.ERROR;
	
	public BusinessException(String message, Object[] arguments) {
		super(message, arguments);
	}
	
	public BusinessException(String message, Object[] arguments, Status status) {
		super(message, arguments);
		this.status = status;
	}

	public BusinessException(String message, Object[] arguments, Throwable cause) {
		super(message, arguments, cause);
	}

	public BusinessException(String message, Throwable cause) {
		this(message, null, cause);
	}

	public BusinessException(String message) {
		this(message, (Throwable) null);
	}

	public BusinessException(String message, Status status) {
		this(message, (Throwable) null);
		this.status = status;
	}

	public boolean isWarning() {
		return status == Status.WARNING;
	}
	
	public boolean isError() {
		return status == Status.ERROR;
	}
	
	public enum Status {
		ERROR, WARNING;
	}
}
