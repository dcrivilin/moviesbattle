package br.com.adatech.moviesbattle.adapter.out.database.exception;

public class ExceptionDataProvider extends RuntimeException {

	private static final long serialVersionUID = -6445729477569084037L;
	
	public ExceptionDataProvider(String msg) {
		super(msg);
	}

	public ExceptionDataProvider(String msg, Throwable cause) {
		super(msg, cause);
	}
}