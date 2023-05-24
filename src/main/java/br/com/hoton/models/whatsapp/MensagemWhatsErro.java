package br.com.hoton.models.whatsapp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.hoton.models.Usuario;

@Entity
public class MensagemWhatsErro {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String vendaId;
	private String vendaStatus;
	private String mensagem;
	@OneToOne
	@JoinColumn(name="usuario_id", nullable=true)
	private Usuario user;
	
	public MensagemWhatsErro() {}
	
	public MensagemWhatsErro(String vendaId, String vendaStatus, String mensagem, Usuario user) {
		this.vendaId = vendaId;
		this.vendaStatus = vendaStatus;
		this.mensagem = mensagem;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendaId() {
		return vendaId;
	}

	public void setVendaId(String vendaId) {
		this.vendaId = vendaId;
	}

	public String getVendaStatus() {
		return vendaStatus;
	}

	public void setVendaStatus(String vendaStatus) {
		this.vendaStatus = vendaStatus;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
}
