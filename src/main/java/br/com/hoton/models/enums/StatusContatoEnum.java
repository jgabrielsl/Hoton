package br.com.hoton.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum StatusContatoEnum {
	
	AGUARDANDO_CONTATO(1, "Aguardando Contato", true),
	COMPLETED(2, "Finalizado", true),
	CHARGEBACK(3, "Cancelado", true),
	CONTATADO(4, "Contatado", true),
	CONVERTIDO(5, "Convertido", true),
	AGENDADO(6, "", false),
	CONTATO_VENCIDO(7, "Contato Vencido", true),
	NOT_FOUND(-1, "", false);
	
	private Integer id;
	private String desc;
	private boolean tela;
	
	StatusContatoEnum(Integer id, String desc, boolean tela) {
		this.id = id;
		this.desc = desc;
		this.tela = tela;
	}
	
	StatusContatoEnum(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	StatusContatoEnum(Integer id) {
		this.id = id;
	}
	
	public static Integer getID(String tipo) {
		switch (tipo.toUpperCase()) {
		case "COMPLETED":
			return 2;
		case "CHARGEBACK":
			return 3;
			
		case "7":
			return 7;
		case "4":
			return 3;	
		case "9":
			return 7;
		
		case "Completa":
			return 2;
		case "Cancelada":
			return 3;
			
		default:
			return 1;
		}
	}
	
	public Integer getId() {
        return this.id;
    }
	
	public String getDesc() {
		return this.desc;
	}
	
	public boolean getTela() {
		return this.tela;
	}
	
	public static String getDesc(Integer id) {
		if(id == StatusContatoEnum.AGUARDANDO_CONTATO.id)
			return StatusContatoEnum.AGUARDANDO_CONTATO.desc;
		if(id == StatusContatoEnum.CHARGEBACK.id)
			return StatusContatoEnum.CHARGEBACK.desc;
		if(id == StatusContatoEnum.COMPLETED.id)
			return StatusContatoEnum.COMPLETED.desc;
		if(id == StatusContatoEnum.CONTATADO.id)
			return StatusContatoEnum.CONTATADO.desc;
		if(id == StatusContatoEnum.CONVERTIDO.id)
			return StatusContatoEnum.CONVERTIDO.desc;
		if(id == StatusContatoEnum.AGENDADO.id)
			return StatusContatoEnum.AGENDADO.desc;
		if(id == StatusContatoEnum.CONTATO_VENCIDO.id)
			return StatusContatoEnum.CONTATO_VENCIDO.desc;
		return "";
	}

	public static Map<Integer, String> toList() {
		Map<Integer, String> stsList = new HashMap<Integer, String>();
		for (StatusContatoEnum  aux: StatusContatoEnum.values())
			if(aux.getTela())
				stsList.put(aux.getId(), aux.getDesc());
		return stsList;
	}
	
}
