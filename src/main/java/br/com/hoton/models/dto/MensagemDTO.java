package br.com.hoton.models.dto;

import br.com.hoton.models.Mensagem;

public class MensagemDTO {

    private Long id;
	private String titulo;
	private String categoria;
	private String mensagem;
	private boolean enabled;
	
	public MensagemDTO(Mensagem mensagem) {
		this.id = mensagem.getId();
		this.titulo = mensagem.getTitulo();
		this.categoria = mensagem.getCategoria();
		this.mensagem = mensagem.getMensagem();
		this.enabled = mensagem.isEnabled();
	}
	
	public MensagemDTO() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}	
}
