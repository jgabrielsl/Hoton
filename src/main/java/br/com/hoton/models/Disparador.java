package br.com.hoton.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.hoton.models.enums.DisparadorStatus;

@Entity
public class Disparador {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@JsonIgnore
	private String fileName;
	private LocalDateTime dtUpload;
	private LocalDateTime dtDisparo;
	@ManyToOne
    @JoinColumn(name="usuario_id", nullable=false)
	@JsonIgnore
	private Usuario user;
	
	@ManyToOne
    @JoinColumn(name="status_id", nullable=false)
	private DisparadorStatus status;
	
	private boolean processed;
	
	public Disparador() {}
	
	public Disparador(LocalDateTime dtDisparo, String fileName, Usuario user, boolean processed) {
		this.fileName = fileName;
		this.dtUpload = LocalDateTime.now();
		this.dtDisparo = dtDisparo;
		this.status = DisparadorStatus.PARA_PROCESSAMENTO;
		this.user = user;
		this.processed = processed;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public LocalDateTime getDtUpload() {
		return dtUpload;
	}
	public void setDtUpload(LocalDateTime dtUpload) {
		this.dtUpload = dtUpload;
	}
	public LocalDateTime getDtDisparo() {
		return dtDisparo;
	}
	public void setDtDisparo(LocalDateTime dtDisparo) {
		this.dtDisparo = dtDisparo;
	}
	public DisparadorStatus getStatus() {
		return status;
	}
	public void setStatus(DisparadorStatus status) {
		this.status = status;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
}
