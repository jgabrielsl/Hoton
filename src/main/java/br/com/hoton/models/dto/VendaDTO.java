package br.com.hoton.models.dto;

import java.math.BigDecimal;

import br.com.hoton.models.Venda;

public class VendaDTO {

	private Long idVenda;
	private String statusContato;
	private Integer statusContatoId;
	private String CodVenda;
	private String pagamentoTipo;
	private Integer pagamentoTipoId;
	private String dtCompra;
	private String nomeComprador;
	private String plataforma;
	private String produto;
	private String statusVenda;
	private Integer statusVendaId;
	private String numComprador;
	private String ultimoContato;
	private BigDecimal comissao;
	private BigDecimal valorTotal;
	private String linkBoleto;
	private String boleto;
	private String email;
	
	public VendaDTO() {}
	
	public VendaDTO(Venda venda, String ultContato) {
		this.idVenda = venda.getId();
		this.statusContato = venda.getContatoStatus();
		this.statusContatoId = venda.getContatoStatusId();
		this.CodVenda = venda.getVendaId();
		this.pagamentoTipo = venda.getFormaPagamento();
		this.pagamentoTipoId = venda.getFormaPagamentosId();
		this.dtCompra = venda.getDtCompra();
		this.nomeComprador = venda.getNome();
		this.plataforma = venda.getPlataforma();
		this.produto = venda.getProduto();
		this.statusVenda = venda.getVendaStatus();
		this.setStatusVendaId(venda.getVendaStatusId());
		this.numComprador = "55"+(venda.getDdd()==null?"":venda.getDdd())
				+(venda.getTelefone()==null?"":venda.getTelefone());
		this.ultimoContato = ultContato;
		this.comissao = venda.getComissao();
		this.valorTotal = venda.getValorTotal();
		this.linkBoleto = venda.getLinkCodigoBarra();
		this.boleto = venda.getCodigoBarra();
		this.email = venda.getEmail();
	}
	
	public Long getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}
	public String getStatusContato() {
		return statusContato;
	}
	public void setStatusContato(String statusContato) {
		this.statusContato = statusContato;
	}
	public String getCodVenda() {
		return CodVenda;
	}
	public void setCodVenda(String codVenda) {
		CodVenda = codVenda;
	}
	public String getPagamentoTipo() {
		return pagamentoTipo;
	}
	public void setPagamentoTipo(String pagamentoTipo) {
		this.pagamentoTipo = pagamentoTipo;
	}
	public String getDtCompra() {
		return dtCompra;
	}
	public void setDtCompra(String dtCompra) {
		this.dtCompra = dtCompra;
	}
	public String getNomeComprador() {
		return nomeComprador;
	}
	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public String getStatusVenda() {
		return statusVenda;
	}
	public void setStatusVenda(String statusVenda) {
		this.statusVenda = statusVenda;
	}
	public String getNumComprador() {
		return numComprador;
	}
	public void setNumComprador(String numComprador) {
		this.numComprador = numComprador;
	}

	public Integer getPagamentoTipoId() {
		return pagamentoTipoId;
	}

	public void setPagamentoTipoId(Integer pagamentoTipoId) {
		this.pagamentoTipoId = pagamentoTipoId;
	}

	public String getUltimoContato() {
		return ultimoContato;
	}

	public void setUltimoContato(String ultimoContato) {
		this.ultimoContato = ultimoContato;
	}

	public Integer getStatusContatoId() {
		return statusContatoId;
	}

	public void setStatusContatoId(Integer statusContatoId) {
		this.statusContatoId = statusContatoId;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getLinkBoleto() {
		return linkBoleto;
	}

	public void setLinkBoleto(String linkBoleto) {
		this.linkBoleto = linkBoleto;
	}

	public String getBoleto() {
		return boleto;
	}

	public void setBoleto(String boleto) {
		this.boleto = boleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatusVendaId() {
		return statusVendaId;
	}

	public void setStatusVendaId(Integer statusVendaId) {
		this.statusVendaId = statusVendaId;
	}

}
