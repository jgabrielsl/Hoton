package br.com.hoton.exceptions;

public class TokenUsuarioException extends Exception{

	private static final long serialVersionUID = 7718828512143293558L;
	
	private String code;
	
	public TokenUsuarioException(String mensagem, String code) {
		super(mensagem);
		this.setCode(code);
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
