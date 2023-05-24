package br.com.hoton.models.enums;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.hoton.exceptions.DisparoStatusException;

@Entity
public class DisparadorStatus {

	public static DisparadorStatus PARA_PROCESSAMENTO = new DisparadorStatus((long)1, "PARA_PROCESSAMENTO", "Aguardando Processamento");
	public static DisparadorStatus PROCESSANDO = new DisparadorStatus((long)2, "PROCESSANDO", "Processando");
	public static DisparadorStatus PROCESSADO_SUCCESSO = new DisparadorStatus((long)3, "PROCESSADO_SUCCESSO", "Processamento concluido");
	public static DisparadorStatus PROCESSADO_ERRO = new DisparadorStatus((long)4, "PROCESSADO_ERRO", "Erro ao processar");
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	private String descricao;
	
	public DisparadorStatus() {}
	
	public DisparadorStatus(Long id, String status, String descricaco) {
		this.id = id;
		this.status = status;
		this.descricao = descricaco;
	}
	
	public static DisparadorStatus getStatus(Integer id) {
		switch (id) {
		case 1:
			return PARA_PROCESSAMENTO;
		case 2:
			return PROCESSANDO;
		case 3:
			return PROCESSADO_SUCCESSO;
		case 4:
			return PROCESSADO_ERRO;
		default:
			throw new DisparoStatusException("Status não encontrado");
		}
	}
	
	public static List<DisparadorStatus> getList() {
		List<DisparadorStatus> stss = new ArrayList<DisparadorStatus>();
		stss.add(PARA_PROCESSAMENTO);
		stss.add(PROCESSANDO);
		stss.add(PROCESSADO_SUCCESSO);
		stss.add(PROCESSADO_ERRO);
		return stss;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
