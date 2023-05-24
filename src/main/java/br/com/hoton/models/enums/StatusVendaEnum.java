package br.com.hoton.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum StatusVendaEnum {
	
	AGUARDANDO_PAGAMENTO(1, "Aguardando Pagamento", true),
	FINALIZADA(2, "Finalizada", true),
	CANCELADA(3, "Cancelada", true),
	DEVOLVIDA(4, "Devolvida", true),
	BLOQUEADA(5, "Bloqueada", true),
	COMPLETA(6, "Completa", true),
	EXPIRADA(7, "Expirada", true),
	PEDIDO_DEVOLUCAO(8, "Pedido Reembolso", true),
	NOT_FOUND(-1, "", false);
	
	private Integer id;
	private String desc;
	private boolean tela;

	StatusVendaEnum(Integer id, String desc, boolean tela) {
		this.id = id;
		this.desc = desc;
		this.tela = tela;
	}
	
	StatusVendaEnum(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	StatusVendaEnum(Integer id) {
		this.id = id;
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
	
	public static Integer getID(String tipo) {
		switch (tipo.toUpperCase()) {
		case "DELAYED":
			return 1;
		case "BILLET_PRINTED":
			return 1;
		case "APPROVED":
			return 2;
		case "CANCELED":
			return 3;
		case "REFUNDED":
			return 4;
		case "CHARGEBACK":
			return 5;
		case "COMPLETED":
			return 6;
		case "EXPIRED":
			return 7;
		case "DISPUTE":
			return 8;
		case "WAITING_PAYMENT":
			return 1;
			
		case "1":
			return 1;
		case "3":
			return 2;
		case "4":
			return 3;
		case "6":
			return 8;
		case "7":
			return 4;
		case "9":
			return -1;
		case "10":
			return 7;
		case "11":
			return 5;
		case "15":
			return 1;
			
		case "AGUARDANDO PAGAMENTO":
			return 1;
		case "FINALIZADA":
			return 2;
		case "CANCELADA":
			return 3;
		case "DEVOLVIDA":
			return 4;
		case "BLOQUEADA":
			return 5;
		case "COMPLETA":
			return 6;
		default:
			return -1;
		}
	}
	
	public static String getDesc(Integer id) {
		if(id == StatusVendaEnum.AGUARDANDO_PAGAMENTO.id)
			return StatusVendaEnum.AGUARDANDO_PAGAMENTO.desc;
		if(id == StatusVendaEnum.FINALIZADA.id)
			return StatusVendaEnum.FINALIZADA.desc;
		if(id == StatusVendaEnum.CANCELADA.id)
			return StatusVendaEnum.CANCELADA.desc;
		if(id == StatusVendaEnum.DEVOLVIDA.id)
			return StatusVendaEnum.DEVOLVIDA.desc;
		if(id == StatusVendaEnum.BLOQUEADA.id)
			return StatusVendaEnum.BLOQUEADA.desc;
		if(id == StatusVendaEnum.COMPLETA.id)
			return StatusVendaEnum.COMPLETA.desc;
		if(id == StatusVendaEnum.EXPIRADA.id)
			return StatusVendaEnum.EXPIRADA.desc;
		if(id == StatusVendaEnum.PEDIDO_DEVOLUCAO.id)
			return StatusVendaEnum.PEDIDO_DEVOLUCAO.desc;
		return "";
	}

	public static Map<Integer, String> toList() {
		Map<Integer, String> stsList = new HashMap<Integer, String>();
		for (StatusVendaEnum  aux: StatusVendaEnum.values())
			if(aux.getTela())
				stsList.put(aux.getId(), aux.getDesc());
		return stsList;
	}
	
}
