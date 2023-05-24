package br.com.hoton.rest.domains;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import br.com.hoton.exceptions.AtributoPostbackException;
import br.com.hoton.models.Venda;
import br.com.hoton.models.enums.FormaPagamentoEnum;
import br.com.hoton.models.enums.StatusContatoEnum;
import br.com.hoton.models.enums.StatusVendaEnum;

public class Monetizze {
	
	private final String TESTE = "X-Wing";
	
	public Monetizze(Map<String, String> map) {
		this.chave_unica = map.getOrDefault("chave_unica", "");
		this.codigo = map.getOrDefault("produto[codigo]", "");
		this.nomeProd = map.getOrDefault("produto[nome]", "");
		this.chave = map.getOrDefault("produto[chave]", "");
		this.codigoTipo = map.getOrDefault("tipoPostback[codigo]", "");
		this.descricao = map.getOrDefault("tipoPostback[descricao]", "");
		this.codigoVenda = map.getOrDefault("venda[codigo]", "");
		this.dataInicio = map.getOrDefault("venda[dataInicio]", "");
		this.dataFinalizada = map.getOrDefault("venda[dataFinalizada]", "");
		this.meioPagamento = map.getOrDefault("venda[meioPagamento]", "");
		this.formaPagamento = map.getOrDefault("venda[formaPagamento]", "");
		this.garantiaRestante = map.getOrDefault("venda[garantiaRestante]", "");
		this.status = map.getOrDefault("venda[status]", "");
		this.valorVenda = map.getOrDefault("venda[valor]", "");
		this.quantidade = map.getOrDefault("venda[quantidade]", "");
		this.tipo_frete = map.getOrDefault("venda[tipo_frete]", "");
		this.frete = map.getOrDefault("venda[frete]", "");
		this.valorRecebido = map.getOrDefault("venda[valorRecebido]", "");
		this.plano = map.getOrDefault("venda[plano]", "");
		this.cupom = map.getOrDefault("venda[cupom]", "");
		this.linkBoleto = map.getOrDefault("venda[linkBoleto]", "");
		this.linha_digitavel = map.getOrDefault("venda[linha_digitavel]", "");
		this.src = map.getOrDefault("venda[src]", "");
		this.onebuyclick = map.getOrDefault("venda[onebuyclick]", "");
		this.venda_upsell = map.getOrDefault("venda[venda_upsell]", "");
		this.nomeComissao = map.getOrDefault("comissoes[0][nome]", "");
		this.tipo_comissao = map.getOrDefault("comissoes[0][tipo_comissao]", "");
		this.valor = map.getOrDefault("comissoes[0][valor]", "");
		this.porcentagem = map.getOrDefault("comissoes[0][porcentagem]", "");
		this.emailComissao = map.getOrDefault("comissoes[0][email]", "");
		this.nome = map.getOrDefault("comprador[nome]", "");
		this.emailComprador = map.getOrDefault("comprador[email]", "");
		this.data_nascimento = map.getOrDefault("comprador[data_nascimento]", "");
		this.cnpj_cpf = map.getOrDefault("comprador[cnpj_cpf]", "");
		this.telefone = map.getOrDefault("comprador[telefone]", "");
		this.cep = map.getOrDefault("comprador[cep]", "");
		this.endereco = map.getOrDefault("comprador[endereco]", "");
		this.numero = map.getOrDefault("comprador[numero]", "");
		this.complemento = map.getOrDefault("comprador[complemento]", "");
		this.bairro = map.getOrDefault("comprador[bairro]", "");
		this.cidade = map.getOrDefault("comprador[cidade]", "");
		this.estado = map.getOrDefault("comprador[estado]", "");
		this.pais = map.getOrDefault("comprador[pais]", "");
		this.hoton_tok = map.getOrDefault("hoton_tok", "");
	}

	public Monetizze() {}
	
	private String hoton_tok;
	private String chave_unica; 			
	private String codigo;		
	private String nomeProd;			
	private String chave;			
	private String codigoTipo;	
	private String descricao;
	private String codigoVenda;			
	private String dataInicio;		
	private String dataFinalizada;	
	private String meioPagamento;	
	private String formaPagamento;	
	private String garantiaRestante;
	private String status;			
	private String valorVenda;			
	private String quantidade;		
	private String tipo_frete;		
	private String frete;			
	private String valorRecebido;	
	private String plano;			
	private String cupom;			
	private String linkBoleto;		
	private String linha_digitavel;	
	private String src;				
	private String onebuyclick;		
	private String venda_upsell;	
	private String nomeComissao;		
	private String tipo_comissao;
	private String valor;	
	private String porcentagem;
	private String emailComissao;	
	private String nome;		
	private String emailComprador;		
	private String data_nascimento;
	private String cnpj_cpf;	
	private String telefone;	
	private String cep;			
	private String endereco;	
	private String numero;		
	private String complemento;	
	private String bairro;		
	private String cidade;		
	private String estado;		
	private String pais;
	
	public String getChave_unica() {
		return chave_unica;
	}
	public void setChave_unica(String chave_unica) {
		this.chave_unica = chave_unica;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNomeProd() {
		return nomeProd;
	}
	public void setNomeProd(String nomeProd) {
		this.nomeProd = nomeProd;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getCodigoTipo() {
		return codigoTipo;
	}
	public void setCodigoTipo(String codigoTipo) {
		this.codigoTipo = codigoTipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCodigoVenda() {
		return codigoVenda;
	}
	public void setCodigoVenda(String codigoVenda) {
		this.codigoVenda = codigoVenda;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFinalizada() {
		return dataFinalizada;
	}
	public void setDataFinalizada(String dataFinalizada) {
		this.dataFinalizada = dataFinalizada;
	}
	public String getMeioPagamento() {
		return meioPagamento;
	}
	public void setMeioPagamento(String meioPagamento) {
		this.meioPagamento = meioPagamento;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getGarantiaRestante() {
		return garantiaRestante;
	}
	public void setGarantiaRestante(String garantiaRestante) {
		this.garantiaRestante = garantiaRestante;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(String valorVenda) {
		this.valorVenda = valorVenda;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getTipo_frete() {
		return tipo_frete;
	}
	public void setTipo_frete(String tipo_frete) {
		this.tipo_frete = tipo_frete;
	}
	public String getFrete() {
		return frete;
	}
	public void setFrete(String frete) {
		this.frete = frete;
	}
	public String getValorRecebido() {
		return valorRecebido;
	}
	public void setValorRecebido(String valorRecebido) {
		this.valorRecebido = valorRecebido;
	}
	public String getPlano() {
		return plano;
	}
	public void setPlano(String plano) {
		this.plano = plano;
	}
	public String getCupom() {
		return cupom;
	}
	public void setCupom(String cupom) {
		this.cupom = cupom;
	}
	public String getLinkBoleto() {
		return linkBoleto;
	}
	public void setLinkBoleto(String linkBoleto) {
		this.linkBoleto = linkBoleto;
	}
	public String getLinha_digitavel() {
		return linha_digitavel;
	}
	public void setLinha_digitavel(String linha_digitavel) {
		this.linha_digitavel = linha_digitavel;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getOnebuyclick() {
		return onebuyclick;
	}
	public void setOnebuyclick(String onebuyclick) {
		this.onebuyclick = onebuyclick;
	}
	public String getVenda_upsell() {
		return venda_upsell;
	}
	public void setVenda_upsell(String venda_upsell) {
		this.venda_upsell = venda_upsell;
	}
	public String getNomeComissao() {
		return nomeComissao;
	}
	public void setNomeComissao(String nomeComissao) {
		this.nomeComissao = nomeComissao;
	}
	public String getTipo_comissao() {
		return tipo_comissao;
	}
	public void setTipo_comissao(String tipo_comissao) {
		this.tipo_comissao = tipo_comissao;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(String porcentagem) {
		this.porcentagem = porcentagem;
	}
	public String getEmailComissao() {
		return emailComissao;
	}
	public void setEmailComissao(String emailComissao) {
		this.emailComissao = emailComissao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmailComprador() {
		return emailComprador;
	}
	public void setEmailComprador(String emailComprador) {
		this.emailComprador = emailComprador;
	}
	public String getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public String getCnpj_cpf() {
		return cnpj_cpf;
	}
	public void setCnpj_cpf(String cnpj_cpf) {
		this.cnpj_cpf = cnpj_cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "MonetizzeDomain [hoton_tok=" + hoton_tok + ", chave_unica=" + chave_unica + ", codigo=" + codigo
				+ ", nomeProd=" + nomeProd + ", chave=" + chave + ", codigoTipo=" + codigoTipo + ", descricao="
				+ descricao + ", codigoVenda=" + codigoVenda + ", dataInicio=" + dataInicio + ", dataFinalizada="
				+ dataFinalizada + ", meioPagamento=" + meioPagamento + ", formaPagamento=" + formaPagamento
				+ ", garantiaRestante=" + garantiaRestante + ", status=" + status + ", valorVenda=" + valorVenda
				+ ", quantidade=" + quantidade + ", tipo_frete=" + tipo_frete + ", frete=" + frete + ", valorRecebido="
				+ valorRecebido + ", plano=" + plano + ", cupom=" + cupom + ", linkBoleto=" + linkBoleto
				+ ", linha_digitavel=" + linha_digitavel + ", src=" + src + ", onebuyclick=" + onebuyclick
				+ ", venda_upsell=" + venda_upsell + ", nomeComissao=" + nomeComissao + ", tipo_comissao="
				+ tipo_comissao + ", valor=" + valor + ", porcentagem=" + porcentagem + ", emailComissao="
				+ emailComissao + ", nome=" + nome + ", emailComprador=" + emailComprador + ", data_nascimento="
				+ data_nascimento + ", cnpj_cpf=" + cnpj_cpf + ", telefone=" + telefone + ", cep=" + cep + ", endereco="
				+ endereco + ", numero=" + numero + ", complemento=" + complemento + ", bairro=" + bairro + ", cidade="
				+ cidade + ", estado=" + estado + ", pais=" + pais + "]";
	}

	public String getHoton_tok() {
		return hoton_tok;
	}

	public void setHoton_tok(String hoton_tok) {
		this.hoton_tok = hoton_tok;
	}

	public Venda obterVenda(String plataforma) {
		
		Venda venda = new Venda();
		venda.setPlataforma(plataforma);
		venda.setTest(this.nomeProd.equalsIgnoreCase(this.TESTE));
		
		venda.setVendaStatusId(StatusVendaEnum.getID(this.status));
		venda.setVendaStatus(StatusVendaEnum.getDesc(venda.getVendaStatusId()));
		venda.setVendaId(this.codigoVenda);
		venda.setContatoStatusId(StatusContatoEnum.getID(this.status));
		venda.setContatoStatus(StatusContatoEnum.getDesc(venda.getContatoStatusId()));
		venda.setFormaPagamentosId(FormaPagamentoEnum.getIDMonetizze(this.formaPagamento));
		venda.setFormaPagamento(FormaPagamentoEnum.getDesc(venda.getFormaPagamentosId()));
		venda.setProduto(this.nomeProd);
		venda.setCodProdu(this.codigo);
		venda.setDocTipo("TAXNUMBER");
		venda.setDocumento(this.cnpj_cpf);
		venda.setMoeda("BRL");
		venda.setTipoVenda(this.descricao);
		venda.setValor(convertBigDecimal(this.valorRecebido)); 
		venda.setValorTotal(convertBigDecimal(this.valorVenda)); 
		venda.setParcelaAtual("1");
		venda.setNome(this.nome);
		venda.setEmail(this.emailComprador);
		venda.setDdd(retornaTelefone("ddd"));
		venda.setTelefone(retornaTelefone("telefone"));
		venda.setDtCompra(formataData(this.dataInicio));
		venda.setCodInscrito(null);
		venda.setCodigoBarra(this.linha_digitavel);
		venda.setLinkCodigoBarra(this.linkBoleto);
		venda.setComissao(convertBigDecimal(this.valorRecebido));
		venda.setDate(criaData(formataData(this.dataInicio)));
		
		return venda;
	}

	private String formataData(String data) {
		String ano = data.substring(0, 4);
		String mes = data.substring(5, 7);
		String dia = data.substring(8, 10);
		return dia+"/"+mes+"/"+ano;
	}
	
	private BigDecimal convertBigDecimal(String price) {
		if(price == null || price.equals(""))
			return new BigDecimal("0");
		
		return new BigDecimal(price);
	}	

	private LocalDate criaData(String formataData) {
		try {
			return LocalDate.parse(formataData, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String retornaTelefone(String tipo) {
		
		if(tipo.equalsIgnoreCase("telefone") && this.telefone != null && 
				!this.telefone.equalsIgnoreCase("") && this.telefone.length() > 3)return this.telefone.substring(2);
		
		
		if(tipo.equalsIgnoreCase("ddd") && this.telefone != null && 
				!this.telefone.equalsIgnoreCase("") && this.telefone.length() > 3)return this.telefone.substring(0, 2);
		
		return "";
	}
	
	public void verifyParams() throws Exception {
		if(this.codigoVenda == null || this.codigoVenda.equalsIgnoreCase(""))throw new AtributoPostbackException("venda_codigo invalido", "VENDA_CODIGO_INVALIDO");
		if(this.status == null || this.status.equalsIgnoreCase(""))throw new AtributoPostbackException("status invalido", "STATUS_INVALIDO");	
		if(this.formaPagamento == null || this.formaPagamento.equalsIgnoreCase(""))throw new AtributoPostbackException("forma pagamento invalido", "FORMA_PAGAMENTO_INVALIDO");
		if(this.hoton_tok == null || this.hoton_tok.equalsIgnoreCase(""))throw new AtributoPostbackException("Token Hoton invalido", "HOTON_TOK_INVALIDO");
		if(this.chave_unica == null || this.chave_unica.equalsIgnoreCase(""))throw new AtributoPostbackException("Token Monetizze invalido", "CHAVE_UNICA_INVALIDO");
		if(this.dataInicio == null || this.dataInicio.equalsIgnoreCase(""))throw new AtributoPostbackException("Data da compra invalido", "DATA_INICIO_INVALIDO");
	}
}
