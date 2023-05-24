package br.com.hoton.exceptions;

public class AtributoPostbackException extends Exception{

	private static final long serialVersionUID = 7718828512143293558L;
	
	private String mensagem;
	private String code;
	
	public AtributoPostbackException(String mensagem, String code) {
		super();
		this.setMensagem(mensagem);
		this.setCode(code);
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
