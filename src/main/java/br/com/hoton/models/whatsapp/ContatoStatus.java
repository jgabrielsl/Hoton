package br.com.hoton.models.whatsapp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.hoton.exceptions.GetStatusException;

@Entity
public class ContatoStatus {

	public static ContatoStatus AGUARDANDO_ENVIO = new ContatoStatus((long)1, "AGUARDANDO_ENVIO", "Aguardando Envio");
	public static ContatoStatus ENVIADO = new ContatoStatus((long)2, "ENVIADO", "Enviado");
	public static ContatoStatus FALHA = new ContatoStatus((long)3, "FALHA", "Envio falhou");
	public static ContatoStatus ENVIANDO = new ContatoStatus((long)4, "ENVIANDO", "Enviando a Mensagem");
	public static ContatoStatus CANCELADO = new ContatoStatus((long)5, "CANCELADO", "Mensagem Cancelada");
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	private String descricao;
	
	public ContatoStatus() {}
	
	public ContatoStatus(Long id, String status, String descricao) {
		super();
		this.id = id;
		this.status = status;
		this.descricao = descricao;
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
	
	public static ContatoStatus getStatus(Integer id) {
		switch (id) {
		case 1:
			return AGUARDANDO_ENVIO;
		case 2:
			return ENVIADO;
		case 3:
			return FALHA;
		case 4:
			return ENVIANDO;
		case 5:
			return CANCELADO;
		default:
			throw new GetStatusException("Status não encontrado");
		}
	}
	
	public static List<ContatoStatus> getList() {
		List<ContatoStatus> contatos = new ArrayList<ContatoStatus>();
		contatos.add(AGUARDANDO_ENVIO);
		contatos.add(ENVIADO);
		contatos.add(FALHA);
		contatos.add(ENVIANDO);
		contatos.add(CANCELADO);
		return contatos;
	}
}
