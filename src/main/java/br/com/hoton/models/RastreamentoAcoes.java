package br.com.hoton.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RastreamentoAcoes {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ip;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
	private Usuario user;
	private String acao;
	private LocalDateTime data;
	
	public RastreamentoAcoes(String ip, Usuario user, String acao, LocalDateTime data) {
		this.ip = ip;
		this.user = user;
		this.acao = acao;
		this.data = data;
	}
	
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "RastreamentoAcoes [id=" + id + ", ip=" + ip + ", user=" + user.getId() + ", acao=" + acao + ", data=" + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss:SSS"))
				+ "]";
	}
	
	
}
