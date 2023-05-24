package br.com.hoton.exceptions;

public class GetStatusException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7079205607803359267L;
	
	public GetStatusException(String message) {
		super(message);
	}
}
