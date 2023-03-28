package br.com.adatech.moviesbattle.application.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -5131626521215042227L;
	
	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}
}