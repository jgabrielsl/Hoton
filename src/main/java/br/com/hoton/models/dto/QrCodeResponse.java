package br.com.hoton.models.dto;

public class QrCodeResponse {

	private String name;
	private String extensao;
	private String image64;
	
	public QrCodeResponse() {}
	
	public QrCodeResponse(String name, String extensao, String image64) {
		this.name = name;
		this.extensao = extensao;
		this.image64 = image64;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExtensao() {
		return extensao;
	}
	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}
	public String getImage64() {
		return image64;
	}
	public void setImage64(String image64) {
		this.image64 = image64;
	}
}
