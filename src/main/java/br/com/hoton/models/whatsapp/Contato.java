package br.com.hoton.models.whatsapp;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.hoton.models.Disparador;
import br.com.hoton.models.Venda;

@Entity
public class Contato {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String telefone;
	
	@ManyToOne
    @JoinColumn(name="mensagem_whats_id", nullable=true)
	@JsonIgnore
	private MensagemWhatsapp mensagem;
	@ManyToOne
    @JoinColumn(name="status_id", nullable=false)
	private ContatoStatus status;
	
	@Lob
    @Column(length = 65535, columnDefinition="text")
	private String mensagemSend;
	
	@ManyToOne
    @JoinColumn(name="conf_id", nullable=false)
	@JsonIgnore
	private Conf conf;
	private String feedback;
	private LocalDateTime times; 
	
	@OneToOne
	@JoinColumn(name="venda_id", nullable=true)
	@JsonIgnore
	private Venda venda;
	
	@OneToOne
	@JoinColumn(name="disparador_id", nullable=true)
	private Disparador disparador;
	
	public Disparador getDisparador() {
		return disparador;
	}
	
	public void setDisparador(Disparador disparador) {
		this.disparador = disparador;
	}
	
	public String getMensagemSend() {
		return mensagemSend;
	}
	public void setMensagemSend(String mensagemSend) {
		this.mensagemSend = mensagemSend;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public MensagemWhatsapp getMensagem() {
		return mensagem;
	}
	public void setMensagem(MensagemWhatsapp mensagem) {
		this.mensagem = mensagem;
	}
	public ContatoStatus getStatus() {
		return status;
	}
	public void setStatus(ContatoStatus status) {
		this.status = status;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Conf getConf() {
		return conf;
	}
	public void setConf(Conf conf) {
		this.conf = conf;
	}
	public LocalDateTime getTimes() {
		return times;
	}
	public void setTimes(LocalDateTime times) {
		this.times = times;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}	
}
