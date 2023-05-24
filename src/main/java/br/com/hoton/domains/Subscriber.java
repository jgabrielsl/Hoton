package br.com.hoton.domains;

public class Subscriber {

	private String name;
	private String email;
	private String ucode;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUcode() {
		return ucode;
	}
	public void setUcode(String ucode) {
		this.ucode = ucode;
	}
	public Subscriber(String name, String email, String ucode) {
		super();
		this.name = name;
		this.email = email;
		this.ucode = ucode;
	}
	
	
}
