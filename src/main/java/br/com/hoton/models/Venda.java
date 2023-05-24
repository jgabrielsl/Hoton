package br.com.hoton.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Venda {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String vendaStatus;
	private String vendaId;
	private Integer vendaStatusId;
	private String contatoStatus;
	private Integer contatoStatusId;
	private String formaPagamento;
	private Integer formaPagamentosId;
	private String produto;
	private String codProdu;
	private Long nextId;
	private String docTipo;
	private String documento;
	private String moeda;
	private String tipoVenda;
	private BigDecimal valor; 
	private BigDecimal valorTotal; 
	private String parcelaAtual;
	private String parcelaTotal;
	private String nome;
	private String email;
	private String ddd;
	private String telefone;
	private String dtCompra;
	private String plataforma;
	private String avaliacaoVenda;
	private boolean isTest;
	private String codInscrito;
	private String dtContato;
	private String codigoBarra;
	private String linkCodigoBarra;
	private BigDecimal comissao;
	private LocalDate date;
	
	@ManyToOne
    @JoinColumn(name="usuario_id", nullable=false)
    private Usuario usuario;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVendaStatus() {
		return vendaStatus;
	}
	public void setVendaStatus(String vendaStatus) {
		this.vendaStatus = vendaStatus;
	}
	public String getVendaId() {
		return vendaId;
	}
	public void setVendaId(String vendaId) {
		this.vendaId = vendaId;
	}
	public String getContatoStatus() {
		return contatoStatus;
	}
	public void setContatoStatus(String contatoStatus) {
		this.contatoStatus = contatoStatus;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public Long getNextId() {
		return nextId;
	}
	public void setNextId(Long nextId) {
		this.nextId = nextId;
	}
	public String getDocTipo() {
		return docTipo;
	}
	public void setDocTipo(String docTipo) {
		this.docTipo = docTipo;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getMoeda() {
		return moeda;
	}
	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}
	public String getTipoVenda() {
		return tipoVenda;
	}
	public void setTipoVenda(String tipoVenda) {
		this.tipoVenda = tipoVenda;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getParcelaAtual() {
		return parcelaAtual;
	}
	public void setParcelaAtual(String parcelaAtual) {
		this.parcelaAtual = parcelaAtual;
	}
	public String getParcelaTotal() {
		return parcelaTotal;
	}
	public void setParcelaTotal(String parcelaTotal) {
		this.parcelaTotal = parcelaTotal;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getDtCompra() {
		return dtCompra;
	}
	public void setDtCompra(String dtCompra) {
		this.dtCompra = dtCompra;
	}
	public String getPlataforma() {
		return plataforma;
	}
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}
	public String getAvaliacaoVenda() {
		return avaliacaoVenda;
	}
	public void setAvaliacaoVenda(String avaliacaoVenda) {
		this.avaliacaoVenda = avaliacaoVenda;
	}
	public boolean isTest() {
		return isTest;
	}
	public void setTest(boolean isTest) {
		this.isTest = isTest;
	}
	public String getCodInscrito() {
		return codInscrito;
	}
	public void setCodInscrito(String codInscrito) {
		this.codInscrito = codInscrito;
	}
	public Integer getContatoStatusId() {
		return contatoStatusId;
	}
	public void setContatoStatusId(Integer contatoStatusId) {
		this.contatoStatusId = contatoStatusId;
	}
	public Integer getFormaPagamentosId() {
		return formaPagamentosId;
	}
	public void setFormaPagamentosId(Integer formaPagamentosId) {
		this.formaPagamentosId = formaPagamentosId;
	}
	public String getCodProdu() {
		return codProdu;
	}
	public void setCodProdu(String codProdu) {
		this.codProdu = codProdu;
	}
	public Integer getVendaStatusId() {
		return vendaStatusId;
	}
	public void setVendaStatusId(Integer vendaStatusId) {
		this.vendaStatusId = vendaStatusId;
	}
	public String getDtContato() {
		return dtContato;
	}
	public void setDtContato(String dtContato) {
		this.dtContato = dtContato;
	}
	public String getCodigoBarra() {
		return codigoBarra;
	}
	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	public String getLinkCodigoBarra() {
		return linkCodigoBarra;
	}
	public void setLinkCodigoBarra(String linkCodigoBarra) {
		this.linkCodigoBarra = linkCodigoBarra;
	}
	public BigDecimal getComissao() {
		return comissao;
	}
	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
