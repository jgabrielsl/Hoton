package br.com.hoton.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum FormaPagamentoEnum {

	
	BOLETO(1, "Boleto", true),
	CARTAO_CREDITO(2, "Cartão de Crédito", true),
	CARTAO_DEBITO(3, "Cartão de Débito", false),
	TRANSF_BANCARIA(4, "Transferencia Bancaria", false),
	MOIP_BALANCE(5, "", false),
	BCASH_BALANCE(6, "", false),
	PAYPAL(7, "Paypal", false),
	HOTMART_BALANCE(8, "Balanço Hotmart", false),
	PIX(9, "PIX", false),
	NOT_FOUND(-1, "", false);
	
	private Integer id;
	private String desc;
	private boolean tela;
	
	FormaPagamentoEnum(Integer id, String desc, boolean tela) {
		this.id = id;
		this.desc = desc;
		this.tela = tela;
	}
	
	FormaPagamentoEnum(Integer id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	
	FormaPagamentoEnum(Integer id) {
		this.id = id;
	}
	
	public static Integer getID(String tipo) {
		switch (tipo.toLowerCase()) {
		case "billet":
			return 1;
		case "credit_card":
			return 2;
		case "debit_card":
			return 3;
		case "bank_transfer":
			return 4;
		case "moip_balance":
			return 5;
		case "bcash_balance":
			return 6;
		case "paypal":
			return 7;
		case "order.checkout.hotmart_balance":
			return 8;
		case "pix":
			return 9;
		default:
			return -1;
		}
	}
	
	public static Integer getIDLogic(String tipo) {
		if(tipo.equalsIgnoreCase("1"))
			return 1;
		else if(tipo.equalsIgnoreCase("9") || tipo.equalsIgnoreCase("13") || tipo.equalsIgnoreCase("14") || tipo.equalsIgnoreCase("15") || tipo.equalsIgnoreCase("16") || tipo.equalsIgnoreCase("21") || tipo.equalsIgnoreCase("23")
			|| tipo.equalsIgnoreCase("24") || tipo.equalsIgnoreCase("25") || tipo.equalsIgnoreCase("27"))
			return 2;
		else
			return 3;
	}
	
	public static Integer getIDMonetizze(String tipo) {
		if(tipo.equalsIgnoreCase("BOLETO"))
			return 1;
		else if(tipo.equalsIgnoreCase("DÉBITO ONLINE"))
			return 2;
		else
			return 3;
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
		if(id == FormaPagamentoEnum.BOLETO.id)
			return FormaPagamentoEnum.BOLETO.desc;
		if(id == FormaPagamentoEnum.CARTAO_CREDITO.id)
			return FormaPagamentoEnum.CARTAO_CREDITO.desc;
		if(id == FormaPagamentoEnum.CARTAO_DEBITO.id)
			return FormaPagamentoEnum.CARTAO_DEBITO.desc;
		if(id == FormaPagamentoEnum.TRANSF_BANCARIA.id)
			return FormaPagamentoEnum.TRANSF_BANCARIA.desc;
		if(id == FormaPagamentoEnum.MOIP_BALANCE.id)
			return FormaPagamentoEnum.MOIP_BALANCE.desc;
		if(id == FormaPagamentoEnum.BCASH_BALANCE.id)
			return FormaPagamentoEnum.BCASH_BALANCE.desc;
		if(id == FormaPagamentoEnum.PAYPAL.id)
			return FormaPagamentoEnum.PAYPAL.desc;
		if(id == FormaPagamentoEnum.HOTMART_BALANCE.id)
			return FormaPagamentoEnum.HOTMART_BALANCE.desc;
		if(id == FormaPagamentoEnum.PIX.id)
			return FormaPagamentoEnum.PIX.desc;
		return "";
	}

	public static Map<Integer, String> toList() {
		Map<Integer, String> stsList = new HashMap<Integer, String>();
		for (FormaPagamentoEnum  aux: FormaPagamentoEnum.values())
			if(aux.getTela())
				stsList.put(aux.getId(), aux.getDesc());
		return stsList;
	}
}
