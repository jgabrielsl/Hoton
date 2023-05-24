package br.com.hoton.rest.domains;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.hoton.exceptions.AtributoPostbackException;
import br.com.hoton.models.Venda;
import br.com.hoton.models.enums.FormaPagamentoEnum;
import br.com.hoton.models.enums.StatusContatoEnum;
import br.com.hoton.models.enums.StatusVendaEnum;

public class Hotmart {
	
	private final String TESTE = "yCN6tCNPXoKGimUk3eEKRPOE9jSWTr3123520";
	
	private String hoton_tok;
	private String hottok;
	private String prod;
	private String prod_name;
	private String off;
	private String price;
	private String aff;
	private String aff_name;
	private String email;
	private String name;
	private String first_name;
	private String last_name;
	private String doc;
	private String phone_local_code;
	private String phone_number;
	private String phone_checkout_local_code;
	private String phone_checkout_number;
	private String address;
	private String address_number;
	private String address_country;
	private String address_district;
	private String address_comp;
	private String address_city;
	private String address_state;
	private String address_zip_code;
	private String transaction;
	private String xcod;
	private String src;
	private String status;
	private String payment_type;
	private String hotkey;
	private String name_subscription_plan;
	private String subscriber_code;
	private String recurrency_period;
	private String recurrency;
	private String cms_marketplace;
	private String cms_vendor;
	private String cms_aff;
	private String coupon_code;
	private String callback_type;
	private String subscription_status;
	private String transaction_ext;
	private String sck;
	private String purchase_date;
	private String confirmation_purchase_date;
	private String billet_url;
	private String currency_code_from;
	private String original_offer_price;
	private String currency;
	private String signature_status;
	private String billet_barcode;
	private String producer_name;
	private String producer_document;
	private String producer_legal_nature;
	private String currency_code_from_;
	private String refusal_reason;
	private String doc_type;
	private String full_price;
	private String warranty_date;
	private String cms_aff_currency;
	private String product_support_email;
	private String amount;
	private String aff_cms_rate_currency;
	private String aff_cms_rate_commission;
	private String aff_cms_rate_conversion;
	private String installments_number;
	private String has_co_production;
	private String productOfferPaymentMode;
	private String receiver_type;
	private String subscription_date_next_charge;
	private String funnel;
	private String order_bump;
	private String parent_purchase_transaction;
	
	public String getHoton_tok() {
		return hoton_tok;
	}

	public void setHoton_tok(String hoton_tok) {
		this.hoton_tok = hoton_tok;
	}

	public String getHottok() {
		return hottok;
	}

	public void setHottok(String hottok) {
		this.hottok = hottok;
	}

	public String getProd() {
		return prod;
	}

	public void setProd(String prod) {
		this.prod = prod;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getOff() {
		return off;
	}

	public void setOff(String off) {
		this.off = off;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAff() {
		return aff;
	}

	public void setAff(String aff) {
		this.aff = aff;
	}

	public String getAff_name() {
		return aff_name;
	}

	public void setAff_name(String aff_name) {
		this.aff_name = aff_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getPhone_local_code() {
		return phone_local_code;
	}

	public void setPhone_local_code(String phone_local_code) {
		this.phone_local_code = phone_local_code;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPhone_checkout_local_code() {
		return phone_checkout_local_code;
	}

	public void setPhone_checkout_local_code(String phone_checkout_local_code) {
		this.phone_checkout_local_code = phone_checkout_local_code;
	}

	public String getPhone_checkout_number() {
		return phone_checkout_number;
	}

	public void setPhone_checkout_number(String phone_checkout_number) {
		this.phone_checkout_number = phone_checkout_number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress_number() {
		return address_number;
	}

	public void setAddress_number(String address_number) {
		this.address_number = address_number;
	}

	public String getAddress_country() {
		return address_country;
	}

	public void setAddress_country(String address_country) {
		this.address_country = address_country;
	}

	public String getAddress_district() {
		return address_district;
	}

	public void setAddress_district(String address_district) {
		this.address_district = address_district;
	}

	public String getAddress_comp() {
		return address_comp;
	}

	public void setAddress_comp(String address_comp) {
		this.address_comp = address_comp;
	}

	public String getAddress_city() {
		return address_city;
	}

	public void setAddress_city(String address_city) {
		this.address_city = address_city;
	}

	public String getAddress_state() {
		return address_state;
	}

	public void setAddress_state(String address_state) {
		this.address_state = address_state;
	}

	public String getAddress_zip_code() {
		return address_zip_code;
	}

	public void setAddress_zip_code(String address_zip_code) {
		this.address_zip_code = address_zip_code;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getXcod() {
		return xcod;
	}

	public void setXcod(String xcod) {
		this.xcod = xcod;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getHotkey() {
		return hotkey;
	}

	public void setHotkey(String hotkey) {
		this.hotkey = hotkey;
	}

	public String getName_subscription_plan() {
		return name_subscription_plan;
	}

	public void setName_subscription_plan(String name_subscription_plan) {
		this.name_subscription_plan = name_subscription_plan;
	}

	public String getSubscriber_code() {
		return subscriber_code;
	}

	public void setSubscriber_code(String subscriber_code) {
		this.subscriber_code = subscriber_code;
	}

	public String getRecurrency_period() {
		return recurrency_period;
	}

	public void setRecurrency_period(String recurrency_period) {
		this.recurrency_period = recurrency_period;
	}

	public String getRecurrency() {
		return recurrency;
	}

	public void setRecurrency(String recurrency) {
		this.recurrency = recurrency;
	}

	public String getCms_marketplace() {
		return cms_marketplace;
	}

	public void setCms_marketplace(String cms_marketplace) {
		this.cms_marketplace = cms_marketplace;
	}

	public String getCms_vendor() {
		return cms_vendor;
	}

	public void setCms_vendor(String cms_vendor) {
		this.cms_vendor = cms_vendor;
	}

	public String getCms_aff() {
		return cms_aff;
	}

	public void setCms_aff(String cms_aff) {
		this.cms_aff = cms_aff;
	}

	public String getCoupon_code() {
		return coupon_code;
	}

	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}

	public String getCallback_type() {
		return callback_type;
	}

	public void setCallback_type(String callback_type) {
		this.callback_type = callback_type;
	}

	public String getSubscription_status() {
		return subscription_status;
	}

	public void setSubscription_status(String subscription_status) {
		this.subscription_status = subscription_status;
	}

	public String getTransaction_ext() {
		return transaction_ext;
	}

	public void setTransaction_ext(String transaction_ext) {
		this.transaction_ext = transaction_ext;
	}

	public String getSck() {
		return sck;
	}

	public void setSck(String sck) {
		this.sck = sck;
	}

	public String getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}

	public String getConfirmation_purchase_date() {
		return confirmation_purchase_date;
	}

	public void setConfirmation_purchase_date(String confirmation_purchase_date) {
		this.confirmation_purchase_date = confirmation_purchase_date;
	}

	public String getBillet_url() {
		return billet_url;
	}

	public void setBillet_url(String billet_url) {
		this.billet_url = billet_url;
	}

	public String getCurrency_code_from() {
		return currency_code_from;
	}

	public void setCurrency_code_from(String currency_code_from) {
		this.currency_code_from = currency_code_from;
	}

	public String getOriginal_offer_price() {
		return original_offer_price;
	}

	public void setOriginal_offer_price(String original_offer_price) {
		this.original_offer_price = original_offer_price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSignature_status() {
		return signature_status;
	}

	public void setSignature_status(String signature_status) {
		this.signature_status = signature_status;
	}

	public String getBillet_barcode() {
		return billet_barcode;
	}

	public void setBillet_barcode(String billet_barcode) {
		this.billet_barcode = billet_barcode;
	}

	public String getProducer_name() {
		return producer_name;
	}

	public void setProducer_name(String producer_name) {
		this.producer_name = producer_name;
	}

	public String getProducer_document() {
		return producer_document;
	}

	public void setProducer_document(String producer_document) {
		this.producer_document = producer_document;
	}

	public String getProducer_legal_nature() {
		return producer_legal_nature;
	}

	public void setProducer_legal_nature(String producer_legal_nature) {
		this.producer_legal_nature = producer_legal_nature;
	}

	public String getCurrency_code_from_() {
		return currency_code_from_;
	}

	public void setCurrency_code_from_(String currency_code_from_) {
		this.currency_code_from_ = currency_code_from_;
	}

	public String getRefusal_reason() {
		return refusal_reason;
	}

	public void setRefusal_reason(String refusal_reason) {
		this.refusal_reason = refusal_reason;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public String getFull_price() {
		return full_price;
	}

	public void setFull_price(String full_price) {
		this.full_price = full_price;
	}

	public String getWarranty_date() {
		return warranty_date;
	}

	public void setWarranty_date(String warranty_date) {
		this.warranty_date = warranty_date;
	}

	public String getCms_aff_currency() {
		return cms_aff_currency;
	}

	public void setCms_aff_currency(String cms_aff_currency) {
		this.cms_aff_currency = cms_aff_currency;
	}

	public String getProduct_support_email() {
		return product_support_email;
	}

	public void setProduct_support_email(String product_support_email) {
		this.product_support_email = product_support_email;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAff_cms_rate_currency() {
		return aff_cms_rate_currency;
	}

	public void setAff_cms_rate_currency(String aff_cms_rate_currency) {
		this.aff_cms_rate_currency = aff_cms_rate_currency;
	}

	public String getAff_cms_rate_commission() {
		return aff_cms_rate_commission;
	}

	public void setAff_cms_rate_commission(String aff_cms_rate_commission) {
		this.aff_cms_rate_commission = aff_cms_rate_commission;
	}

	public String getAff_cms_rate_conversion() {
		return aff_cms_rate_conversion;
	}

	public void setAff_cms_rate_conversion(String aff_cms_rate_conversion) {
		this.aff_cms_rate_conversion = aff_cms_rate_conversion;
	}

	public String getInstallments_number() {
		return installments_number;
	}

	public void setInstallments_number(String installments_number) {
		this.installments_number = installments_number;
	}

	public String getHas_co_production() {
		return has_co_production;
	}

	public void setHas_co_production(String has_co_production) {
		this.has_co_production = has_co_production;
	}

	public String getProductOfferPaymentMode() {
		return productOfferPaymentMode;
	}

	public void setProductOfferPaymentMode(String productOfferPaymentMode) {
		this.productOfferPaymentMode = productOfferPaymentMode;
	}

	public String getReceiver_type() {
		return receiver_type;
	}

	public void setReceiver_type(String receiver_type) {
		this.receiver_type = receiver_type;
	}

	public String getSubscription_date_next_charge() {
		return subscription_date_next_charge;
	}

	public void setSubscription_date_next_charge(String subscription_date_next_charge) {
		this.subscription_date_next_charge = subscription_date_next_charge;
	}

	public String getFunnel() {
		return funnel;
	}

	public void setFunnel(String funnel) {
		this.funnel = funnel;
	}

	public String getOrder_bump() {
		return order_bump;
	}

	public void setOrder_bump(String order_bump) {
		this.order_bump = order_bump;
	}

	public String getParent_purchase_transaction() {
		return parent_purchase_transaction;
	}

	public void setParent_purchase_transaction(String parent_purchase_transaction) {
		this.parent_purchase_transaction = parent_purchase_transaction;
	}

	@Override
	public String toString() {
		return "HotonDomain [hoton_tok=" + hoton_tok + ", hottok=" + hottok + ", prod=" + prod + ", prod_name="
				+ prod_name + ", off=" + off + ", price=" + price + ", aff=" + aff + ", aff_name=" + aff_name
				+ ", email=" + email + ", name=" + name + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", doc=" + doc + ", phone_local_code=" + phone_local_code + ", phone_number=" + phone_number
				+ ", phone_checkout_local_code=" + phone_checkout_local_code + ", phone_checkout_number="
				+ phone_checkout_number + ", address=" + address + ", address_number=" + address_number
				+ ", address_country=" + address_country + ", address_district=" + address_district + ", address_comp="
				+ address_comp + ", address_city=" + address_city + ", address_state=" + address_state
				+ ", address_zip_code=" + address_zip_code + ", transaction=" + transaction + ", xcod=" + xcod
				+ ", src=" + src + ", status=" + status + ", payment_type=" + payment_type + ", hotkey=" + hotkey
				+ ", name_subscription_plan=" + name_subscription_plan + ", subscriber_code=" + subscriber_code
				+ ", recurrency_period=" + recurrency_period + ", recurrency=" + recurrency + ", cms_marketplace="
				+ cms_marketplace + ", cms_vendor=" + cms_vendor + ", cms_aff=" + cms_aff + ", coupon_code="
				+ coupon_code + ", callback_type=" + callback_type + ", subscription_status=" + subscription_status
				+ ", transaction_ext=" + transaction_ext + ", sck=" + sck + ", purchase_date=" + purchase_date
				+ ", confirmation_purchase_date=" + confirmation_purchase_date + ", billet_url=" + billet_url
				+ ", currency_code_from=" + currency_code_from + ", original_offer_price=" + original_offer_price
				+ ", currency=" + currency + ", signature_status=" + signature_status + ", billet_barcode="
				+ billet_barcode + ", producer_name=" + producer_name + ", producer_document=" + producer_document
				+ ", producer_legal_nature=" + producer_legal_nature + ", currency_code_from_=" + currency_code_from_
				+ ", refusal_reason=" + refusal_reason + ", doc_type=" + doc_type + ", full_price=" + full_price
				+ ", warranty_date=" + warranty_date + ", cms_aff_currency=" + cms_aff_currency
				+ ", product_support_email=" + product_support_email + ", amount=" + amount + ", aff_cms_rate_currency="
				+ aff_cms_rate_currency + ", aff_cms_rate_commission=" + aff_cms_rate_commission
				+ ", aff_cms_rate_conversion=" + aff_cms_rate_conversion + ", installments_number="
				+ installments_number + ", has_co_production=" + has_co_production + ", productOfferPaymentMode="
				+ productOfferPaymentMode + ", receiver_type=" + receiver_type + ", subscription_date_next_charge="
				+ subscription_date_next_charge + ", funnel=" + funnel + ", order_bump=" + order_bump
				+ ", parent_purchase_transaction=" + parent_purchase_transaction + "]";
	}

	public Venda obterVenda(String plataforma) {
		Venda venda = new Venda();
		venda.setTest(this.hottok.equals(this.TESTE));
		venda.setVendaStatusId(StatusVendaEnum.getID(this.status));
		venda.setVendaStatus(StatusVendaEnum.getDesc(venda.getVendaStatusId()));
		venda.setVendaId(this.transaction);
		venda.setContatoStatusId(StatusContatoEnum.getID(this.status));
		venda.setContatoStatus(StatusContatoEnum.getDesc(venda.getContatoStatusId()));
		venda.setFormaPagamentosId(FormaPagamentoEnum.getID(this.payment_type));
		venda.setFormaPagamento(FormaPagamentoEnum.getDesc(venda.getFormaPagamentosId()));
		venda.setProduto(this.prod_name);
		venda.setCodProdu(this.prod);
		venda.setDocTipo(this.doc_type);
		venda.setDocumento(this.doc);
		venda.setMoeda(this.currency);
		venda.setTipoVenda(this.aff_name == null||this.aff_name.equals("")?"Produtor":"Afiliado");
		venda.setValor(convertBigDecimal(this.price)); 
		venda.setValorTotal(convertBigDecimal(this.full_price)); 
		venda.setParcelaAtual(this.installments_number);
		venda.setNome(this.name);
		venda.setEmail(this.email);
		venda.setDdd(retornaTelefone("ddd"));
		venda.setTelefone(retornaTelefone("telefone"));
		venda.setDtCompra(formataData(this.purchase_date));
		venda.setDate(criaData(formataData(this.purchase_date)));
		venda.setPlataforma(plataforma);
		venda.setCodInscrito(this.subscriber_code);
		venda.setCodigoBarra(this.billet_barcode);
		venda.setLinkCodigoBarra(this.billet_url);
		venda.setComissao(this.cms_vendor!= null&&!this.cms_vendor.equals("")?convertBigDecimal(this.cms_vendor):convertBigDecimal(this.cms_aff.indexOf(";")>=0?this.cms_aff.substring(0, this.cms_aff.indexOf(";")):this.cms_aff));
		return venda;
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

	private String formataData(String data) {
		String ano = data.substring(0, 4);
		String mes = data.substring(5, 7);
		String dia = data.substring(8, 10);
		return dia+"/"+mes+"/"+ano;
	}

	private String retornaTelefone(String tipo) {
		
		if(tipo.equalsIgnoreCase("telefone") && this.phone_checkout_number != null && 
				!this.phone_checkout_number.equalsIgnoreCase(""))return this.phone_checkout_number;
		
		if(tipo.equalsIgnoreCase("telefone") && this.phone_number != null && 
				!this.phone_number.equalsIgnoreCase(""))return this.phone_number;
		
		if(tipo.equalsIgnoreCase("ddd") && this.phone_checkout_local_code != null && 
				!this.phone_checkout_local_code.equalsIgnoreCase(""))return this.phone_checkout_local_code;
		
		if(tipo.equalsIgnoreCase("ddd") && this.phone_local_code != null && 
				!this.phone_local_code.equalsIgnoreCase(""))return this.phone_local_code;
		
		return "";
	}

	public void verifyParams() throws Exception {
		if(this.transaction == null || this.transaction.equalsIgnoreCase(""))throw new AtributoPostbackException("transaction_id invalido", "TRANSACTION_ID_INVALIDO");
		if(this.status == null || this.status.equalsIgnoreCase(""))throw new AtributoPostbackException("status invalido", "STATUS_INVALIDO");	
		if(this.payment_type == null || this.payment_type.equalsIgnoreCase(""))throw new AtributoPostbackException("payment_type invalido", "PAYMENT_TYPE_INVALIDO");
		if(this.hoton_tok == null || this.hoton_tok.equalsIgnoreCase(""))throw new AtributoPostbackException("Token Hoton invalido", "HOTON_TOK_INVALIDO");
		if(this.hottok == null || this.hottok.equalsIgnoreCase(""))throw new AtributoPostbackException("Token Hotmart invalido", "HOTTOK_INVALIDO");
		if(this.purchase_date == null || this.purchase_date.equalsIgnoreCase(""))throw new AtributoPostbackException("Data da compra invalido", "PURCHASE_DATE_INVALIDO");
	}
}
