package br.com.hoton.models.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.hoton.models.Disparador;
import br.com.hoton.models.Usuario;

public class DisparadorUpload {

	private String file;
	private String extension;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime dataHora;
	private boolean processed;
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	
	@Override
	public String toString() {
		return "DisparadorUpload [file=" + file + ", extension=" + extension + ", dataHora=" + dataHora + "]";
	}
	
	public Disparador build(String fileName, Usuario user) {
		return new Disparador(this.dataHora, fileName, user, processed);
	}
	public void isValid() throws Exception {
		if(!this.extension.equalsIgnoreCase("XLSX"))
			throw new Exception("Formato Inválido!");
		
		if(this.file == null || this.file.length() <= 0)
			throw new Exception("Arquivo Inválido!");
	}
	public boolean isProcessed() {
		return processed;
	}
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
	
}
