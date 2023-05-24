package br.com.hoton.models.whatsapp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.hoton.exceptions.GetStatusException;

@Entity
public class MsgWhatsappType {

	public static MsgWhatsappType AGUARDANDO_PAGAMENTO = new MsgWhatsappType((long)1, "AGUARDANDO_PAGAMENTO", "Aguardando Pagamento", true);
	public static MsgWhatsappType FINALIZADA = new MsgWhatsappType((long)2, "FINALIZADA", "Finalizada", true);
	public static MsgWhatsappType CANCELADA = new MsgWhatsappType((long)3, "CANCELADA", "Cancelada", true);
	public static MsgWhatsappType DEVOLVIDA = new MsgWhatsappType((long)4, "DEVOLVIDA", "Devolvida", true);
	public static MsgWhatsappType BLOQUEADA = new MsgWhatsappType((long)5, "BLOQUEADA", "Bloqueada", true);
	public static MsgWhatsappType COMPLETA = new MsgWhatsappType((long)6, "COMPLETA", "Completa", true);
	public static MsgWhatsappType EXPIRADA = new MsgWhatsappType((long)7, "EXPIRADA", "Expirada", true);
	public static MsgWhatsappType REEMBOLSO = new MsgWhatsappType((long)8, "REEMBOLSO", "Pedido Reembolso", true);
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	private String descricao;
	private boolean ativo;
	
	public MsgWhatsappType() {}
	
	public MsgWhatsappType(Long id, String status, String descricao, boolean ativo) {
		this.id = id;
		this.status = status;
		this.descricao = descricao;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public static MsgWhatsappType getStatus(Long id) {
		switch (id.intValue()) {
		case 1:
			return AGUARDANDO_PAGAMENTO;
		case 2:
			return FINALIZADA;
		case 3:
			return CANCELADA;
		case 4:
			return DEVOLVIDA;
		case 5:
			return BLOQUEADA;
		case 6:
			return COMPLETA;
		case 7:
			return EXPIRADA;
		case 8:
			return REEMBOLSO;
		default:
			throw new GetStatusException("Status não encontrado");
		}
	}
	
	public static List<MsgWhatsappType> getList() {
		List<MsgWhatsappType> types = new ArrayList<MsgWhatsappType>();
		types.add(AGUARDANDO_PAGAMENTO);
		types.add(FINALIZADA);
		types.add(CANCELADA);
		types.add(DEVOLVIDA);
		types.add(BLOQUEADA);
		types.add(COMPLETA);
		types.add(EXPIRADA);
		types.add(REEMBOLSO);
		return types;
	}
}
