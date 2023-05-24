package br.com.hoton.models.whatsapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.hoton.models.Usuario;

@Entity
public class Conf {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String chavePrivada;
	@ManyToOne
    @JoinColumn(name="usuario_id", nullable=false)
	private Usuario usuario;
	private String sessao;
	private String status;
	
	@Column(columnDefinition = "boolean default false")
	private boolean isMultDevice;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChavePrivada() {
		return chavePrivada;
	}
	public void setChavePrivada(String chavePrivada) {
		this.chavePrivada = chavePrivada;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getSessao() {
		return sessao;
	}
	public void setSessao(String sessao) {
		this.sessao = sessao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isMultDevice() {
		return isMultDevice;
	}
	public void setMultDevice(boolean isMultDevice) {
		this.isMultDevice = isMultDevice;
	}
	
	
}
