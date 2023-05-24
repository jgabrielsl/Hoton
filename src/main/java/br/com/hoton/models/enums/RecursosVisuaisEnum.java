package br.com.hoton.models.enums;

public enum RecursosVisuaisEnum {

	TUTORIAL(1),
	PAG_VENDAS(2),
	PAG_VENDAS_TOUR(3);
	
	private Integer id;
	
	RecursosVisuaisEnum(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
        return this.id;
    }
		
}
