package br.com.hoton.models.whatsapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import br.com.hoton.models.Usuario;

@Entity
public class MensagemWhatsapp {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob
    @Column(length = 65535, columnDefinition="text")
	private String mensagem;
	private boolean ativo;
	
	@ManyToOne
    @JoinColumn(name="usuario_id", nullable=false)
	private Usuario user;
	
	@ManyToOne
    @JoinColumn(name="type_id", nullable=false)
	private MsgWhatsappType type;
	
	@Column(columnDefinition = "INT default 0")
	private Integer dataAfter;
	private Integer tipoPagamento;
	
	public MensagemWhatsapp() {}
	
	public MensagemWhatsapp(String mensagem, boolean ativo, MsgWhatsappType type, Usuario user, Integer dataAfter, Integer tipoPagamento) {
		this.mensagem = mensagem;
		this.ativo = ativo;
		this.type = type;
		this.user = user;
		this.dataAfter = dataAfter;
		this.tipoPagamento = tipoPagamento;
	}
	public MensagemWhatsapp(String mensagem, boolean ativo, MsgWhatsappType type, Usuario user, Integer dataAfter) {
		this.mensagem = mensagem;
		this.ativo = ativo;
		this.type = type;
		this.user = user;
		this.dataAfter = dataAfter;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public MsgWhatsappType getType() {
		return type;
	}

	public void setType(MsgWhatsappType type) {
		this.type = type;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Integer getDataAfter() {
		return dataAfter;
	}

	public void setDataAfter(Integer dataAfter) {
		this.dataAfter = dataAfter;
	}

	public void setTipoPagamento(Integer tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Integer getTipoPagamento() {
		return tipoPagamento;
	}
}
