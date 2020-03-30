package com.dummy.myerp.technical.log.message;

public class ErrorMessage extends DefaultMessage {

	/**
	 * {@code serialVersionUID}
	 */
	private static final long serialVersionUID = 1L;

	private final Exception exception;

	public <E extends Exception> ErrorMessage(E exception) {
		super();
		this.exception = exception;
	}

	@Override
	public String getFormattedMessage() {
		return exception.getClass().getSimpleName() + " levée !";
	}

	@Override
	public Throwable getThrowable() {
		return exception;
	}

}
