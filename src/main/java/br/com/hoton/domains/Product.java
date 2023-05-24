package br.com.hoton.domains;

public class Product {

	public Integer id;
	public String name;
	public String ucode;
	public Integer getId() {
		return id;
	}
	private void setId(Integer id) {
		this.id = id;
	}
	private String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	private String getUcode() {
		return ucode;
	}
	private void setUcode(String ucode) {
		this.ucode = ucode;
	}
	private Product(Integer id, String name, String ucode) {
		super();
		this.id = id;
		this.name = name;
		this.ucode = ucode;
	}
	
}
