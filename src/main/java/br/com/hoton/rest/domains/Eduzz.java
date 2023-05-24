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

public class Eduzz {
	
	private final String TESTE = "testWebhook";

	public Eduzz() {}
	
	public Eduzz(Map<String, String> map) {
		this.api_key	=	map.getOrDefault("api_key",	"");
		this.hoton_tok	=	map.getOrDefault("hoton_tok",	"");
		this.pro_cod	=	map.getOrDefault("pro_cod",	"");
		this.pro_name	=	map.getOrDefault("pro_name",	"");
		this.pro_email	=	map.getOrDefault("pro_email",	"");
		this.pro_document_number	=	map.getOrDefault("pro_document_number",	"");
		this.pro_value	=	map.getOrDefault("pro_value",	"");
		this.trans_cod	=	map.getOrDefault("trans_cod",	"");
		this.trans_checkoutid	=	map.getOrDefault("trans_checkoutid",	"");
		this.trans_value	=	map.getOrDefault("trans_value",	"");
		this.trans_paid	=	map.getOrDefault("trans_paid",	"");
		this.trans_status	=	map.getOrDefault("trans_status",	"");
		this.trans_paymentmethod	=	map.getOrDefault("trans_paymentmethod",	"");
		this.trans_createdate	=	map.getOrDefault("trans_createdate",	"");
		this.trans_paiddate	=	map.getOrDefault("trans_paiddate",	"");
		this.trans_duedate	=	map.getOrDefault("trans_duedate",	"");
		this.trans_createtime	=	map.getOrDefault("trans_createtime",	"");
		this.trans_paidtime	=	map.getOrDefault("trans_paidtime",	"");
		this.trans_duetime	=	map.getOrDefault("trans_duetime",	"");
		this.trans_items_quantity	=	map.getOrDefault("trans_items_quantity",	"");
		this.trans_key	=	map.getOrDefault("trans_key",	"");
		this.recurrence_cod	=	map.getOrDefault("recurrence_cod",	"");
		this.recurrence_type	=	map.getOrDefault("recurrence_type",	"");
		this.recurrence_plan	=	map.getOrDefault("recurrence_plan",	"");
		this.product_cod	=	map.getOrDefault("product_cod",	"");
		this.product_name	=	map.getOrDefault("product_name",	"");
		this.product_refund	=	map.getOrDefault("product_refund",	"");
		this.product_sku	=	map.getOrDefault("product_sku",	"");
		this.discount_coupon_code	=	map.getOrDefault("discount_coupon_code",	"");
		this.cus_cod	=	map.getOrDefault("cus_cod",	"");
		this.cus_taxnumber	=	map.getOrDefault("cus_taxnumber",	"");
		this.cus_name	=	map.getOrDefault("cus_name",	"");
		this.cus_email = map.getOrDefault("cus_email",	"");
		this.cus_tel	=	map.getOrDefault("cus_tel",	"");
		this.cus_tel2	=	map.getOrDefault("cus_tel2",	"");
		this.cus_cel	=	map.getOrDefault("cus_cel",	"");
		this.cus_apikey	=	map.getOrDefault("cus_apikey",	"");
		this.cus_address	=	map.getOrDefault("cus_address",	"");
		this.cus_address_number	=	map.getOrDefault("cus_address_number",	"");
		this.cus_address_country	=	map.getOrDefault("cus_address_country",	"");
		this.cus_address_district	=	map.getOrDefault("cus_address_district",	"");
		this.cus_address_comp	=	map.getOrDefault("cus_address_comp",	"");
		this.cus_address_city	=	map.getOrDefault("cus_address_city",	"");
		this.cus_address_state	=	map.getOrDefault("cus_address_state",	"");
		this.cus_address_zip_code	=	map.getOrDefault("cus_address_zip_code",	"");
		this.recurrence_startdate	=	map.getOrDefault("recurrence_startdate",	"");
		this.recurrence_status	=	map.getOrDefault("recurrence_status",	"");
		this.recurrence_status_name	=	map.getOrDefault("recurrence_status_name",	"");
		this.recurrence_interval	=	map.getOrDefault("recurrence_interval",	"");
		this.recurrence_interval_type	=	map.getOrDefault("recurrence_interval_type",	"");
		this.recurrence_count	=	map.getOrDefault("recurrence_count",	"");
		this.recurrence_first_payment	=	map.getOrDefault("recurrence_first_payment",	"");
		this.aff_cod	=	map.getOrDefault("aff_cod",	"");
		this.aff_name	=	map.getOrDefault("aff_name",	"");
		this.aff_email	=	map.getOrDefault("aff_email",	"");
		this.aff_document_number	=	map.getOrDefault("aff_document_number",	"");
		this.aff_value	=	map.getOrDefault("aff_value",	"");
		this.tracker_trk	=	map.getOrDefault("tracker_trk",	"");
		this.tracker_trk2	=	map.getOrDefault("tracker_trk2",	"");
		this.tracker_trk3	=	map.getOrDefault("tracker_trk3",	"");
		this.tracker_utm_source	=	map.getOrDefault("tracker_utm_source",	"");
		this.tracker_utm_content	=	map.getOrDefault("tracker_utm_content",	"");
		this.tracker_utm_medium	=	map.getOrDefault("tracker_utm_medium",	"");
		this.tracker_utm_campaign	=	map.getOrDefault("tracker_utm_campaign",	"");
		this.utm_source	=	map.getOrDefault("utm_source",	"");
		this.utm_content	=	map.getOrDefault("utm_content",	"");
		this.utm_medium	=	map.getOrDefault("utm_medium",	"");
		this.utm_campaign	=	map.getOrDefault("utm_campaign",	"");
		this.sku_reference	=	map.getOrDefault("sku_reference",	"");
		this.eduzz_value	=	map.getOrDefault("eduzz_value",	"");
		this.other_values	=	map.getOrDefault("other_values",	"");
		this.trans_itemsitem_id	=	map.getOrDefault("trans_items[0][item_id]",	"");
		this.trans_itemsitem_name	=	map.getOrDefault("trans_items[0][item_name]",	"");
		this.trans_itemsitem_value	=	map.getOrDefault("trans_items[0][item_value]",	"");
		this.trans_itemsitem_coupon_code	=	map.getOrDefault("trans_items[0][item_coupon_code]",	"");
		this.trans_itemsitem_coupon_value	=	map.getOrDefault("trans_items[0][item_coupon_value]",	"");
		this.trans_itemsitem_product_id	=	map.getOrDefault("trans_items[0][item_product_id]",	"");
		this.trans_itemsitem_product_name	=	map.getOrDefault("trans_items[0][item_product_name]",	"");
		this.trans_itemsitem_product_refund	=	map.getOrDefault("trans_items[0][item_product_refund]",	"");
		this.trans_itemsitem_product_sku_reference	=	map.getOrDefault("trans_items[0][item_product_sku_reference]",	"");
		this.trans_itemsitem_product_partner_cod	=	map.getOrDefault("trans_items[0][item_product_partner_cod]",	"");
		this.trans_itemsitem_product_chargetype	=	map.getOrDefault("trans_items[0][item_product_chargetype]",	"");
		this.trans_barcode	=	map.getOrDefault("trans_barcode",	"");
		this.trans_orderid	=	map.getOrDefault("trans_orderid",	"");
		this.trans_currency	=	map.getOrDefault("trans_currency",	"");
		this.trans_job_id	=	map.getOrDefault("trans_job_id",	"");
		this.trans_job_status	=	map.getOrDefault("trans_job_status",	"");
		this.request_token	=	map.getOrDefault("request_token",	"");
		this.billet_url	=	map.getOrDefault("billet_url",	"");
		this.page_checkout_url	=	map.getOrDefault("page_checkout_url",	"");
		this.type	=	map.getOrDefault("type",	"");
		this.trans_bankslip	=	map.getOrDefault("trans_bankslip",	"");
		this.recurrence_canceled_at	=	map.getOrDefault("recurrence_canceled_at",	"");
		this.product_chargetype	=	map.getOrDefault("product_chargetype",	"");
		this.refund_date	=	map.getOrDefault("refund_date",	"");

	}
	
	private String api_key; 
	private String pro_cod; 
	private String pro_name; 
	private String pro_email; 
	private String pro_document_number; 
	private String pro_value; 
	private String trans_cod; 
	private String trans_checkoutid; 
	private String trans_value; 
	private String trans_paid; 
	private String trans_status; 
	private String trans_paymentmethod; 
	private String trans_createdate; 
	private String trans_paiddate; 
	private String trans_duedate; 
	private String trans_createtime; 
	private String trans_paidtime; 
	private String trans_duetime; 
	private String trans_items_quantity; 
	private String trans_key; 
	private String recurrence_cod; 
	private String recurrence_type; 
	private String recurrence_plan; 
	private String product_cod; 
	private String product_name; 
	private String product_refund; 
	private String product_sku; 
	private String discount_coupon_code; 
	private String cus_cod; 
	private String cus_taxnumber; 
	private String cus_name; 
	private String cus_email;
	private String cus_tel; 
	private String cus_tel2; 
	private String cus_cel; 
	private String cus_apikey; 
	private String cus_address; 
	private String cus_address_number; 
	private String cus_address_country; 
	private String cus_address_district; 
	private String cus_address_comp; 
	private String cus_address_city; 
	private String cus_address_state; 
	private String cus_address_zip_code; 
	private String recurrence_startdate; 
	private String recurrence_status; 
	private String recurrence_status_name; 
	private String recurrence_interval; 
	private String recurrence_interval_type; 
	private String recurrence_count; 
	private String recurrence_first_payment; 
	private String aff_cod; 
	private String aff_name; 
	private String aff_email; 
	private String aff_document_number; 
	private String aff_value; 
	private String tracker_trk; 
	private String tracker_trk2; 
	private String tracker_trk3; 
	private String tracker_utm_source; 
	private String tracker_utm_content; 
	private String tracker_utm_medium; 
	private String tracker_utm_campaign; 
	private String utm_source; 
	private String utm_content; 
	private String utm_medium; 
	private String utm_campaign; 
	private String sku_reference; 
	private String eduzz_value; 
	private String other_values; 
	private String trans_itemsitem_id; 
	private String trans_itemsitem_name; 
	private String trans_itemsitem_value; 
	private String trans_itemsitem_coupon_code; 
	private String trans_itemsitem_coupon_value; 
	private String trans_itemsitem_product_id; 
	private String trans_itemsitem_product_name; 
	private String trans_itemsitem_product_refund; 
	private String trans_itemsitem_product_sku_reference; 
	private String trans_itemsitem_product_partner_cod; 
	private String trans_itemsitem_product_chargetype; 
	private String trans_barcode; 
	private String trans_orderid; 
	private String trans_currency; 
	private String trans_job_id; 
	private String trans_job_status; 
	private String request_token; 
	private String billet_url; 
	private String page_checkout_url; 
	private String type; 
	private String trans_bankslip; 
	private String recurrence_canceled_at; 
	private String product_chargetype; 
	private String refund_date;
	private String hoton_tok;
	
	public String getApi_key() {
		return api_key;
	}
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	public String getPro_cod() {
		return pro_cod;
	}
	public void setPro_cod(String pro_cod) {
		this.pro_cod = pro_cod;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public String getPro_email() {
		return pro_email;
	}
	public void setPro_email(String pro_email) {
		this.pro_email = pro_email;
	}
	public String getPro_document_number() {
		return pro_document_number;
	}
	public void setPro_document_number(String pro_document_number) {
		this.pro_document_number = pro_document_number;
	}
	public String getPro_value() {
		return pro_value;
	}
	public void setPro_value(String pro_value) {
		this.pro_value = pro_value;
	}
	public String getTrans_cod() {
		return trans_cod;
	}
	public void setTrans_cod(String trans_cod) {
		this.trans_cod = trans_cod;
	}
	public String getTrans_checkoutid() {
		return trans_checkoutid;
	}
	public void setTrans_checkoutid(String trans_checkoutid) {
		this.trans_checkoutid = trans_checkoutid;
	}
	public String getTrans_value() {
		return trans_value;
	}
	public void setTrans_value(String trans_value) {
		this.trans_value = trans_value;
	}
	public String getTrans_paid() {
		return trans_paid;
	}
	public void setTrans_paid(String trans_paid) {
		this.trans_paid = trans_paid;
	}
	public String getTrans_status() {
		return trans_status;
	}
	public void setTrans_status(String trans_status) {
		this.trans_status = trans_status;
	}
	public String getTrans_paymentmethod() {
		return trans_paymentmethod;
	}
	public void setTrans_paymentmethod(String trans_paymentmethod) {
		this.trans_paymentmethod = trans_paymentmethod;
	}
	public String getTrans_createdate() {
		return trans_createdate;
	}
	public void setTrans_createdate(String trans_createdate) {
		this.trans_createdate = trans_createdate;
	}
	public String getTrans_paiddate() {
		return trans_paiddate;
	}
	public void setTrans_paiddate(String trans_paiddate) {
		this.trans_paiddate = trans_paiddate;
	}
	public String getTrans_duedate() {
		return trans_duedate;
	}
	public void setTrans_duedate(String trans_duedate) {
		this.trans_duedate = trans_duedate;
	}
	public String getTrans_createtime() {
		return trans_createtime;
	}
	public void setTrans_createtime(String trans_createtime) {
		this.trans_createtime = trans_createtime;
	}
	public String getTrans_paidtime() {
		return trans_paidtime;
	}
	public void setTrans_paidtime(String trans_paidtime) {
		this.trans_paidtime = trans_paidtime;
	}
	public String getTrans_duetime() {
		return trans_duetime;
	}
	public void setTrans_duetime(String trans_duetime) {
		this.trans_duetime = trans_duetime;
	}
	public String getTrans_items_quantity() {
		return trans_items_quantity;
	}
	public void setTrans_items_quantity(String trans_items_quantity) {
		this.trans_items_quantity = trans_items_quantity;
	}
	public String getTrans_key() {
		return trans_key;
	}
	public void setTrans_key(String trans_key) {
		this.trans_key = trans_key;
	}
	public String getRecurrence_cod() {
		return recurrence_cod;
	}
	public void setRecurrence_cod(String recurrence_cod) {
		this.recurrence_cod = recurrence_cod;
	}
	public String getRecurrence_type() {
		return recurrence_type;
	}
	public void setRecurrence_type(String recurrence_type) {
		this.recurrence_type = recurrence_type;
	}
	public String getRecurrence_plan() {
		return recurrence_plan;
	}
	public void setRecurrence_plan(String recurrence_plan) {
		this.recurrence_plan = recurrence_plan;
	}
	public String getProduct_cod() {
		return product_cod;
	}
	public void setProduct_cod(String product_cod) {
		this.product_cod = product_cod;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_refund() {
		return product_refund;
	}
	public void setProduct_refund(String product_refund) {
		this.product_refund = product_refund;
	}
	public String getProduct_sku() {
		return product_sku;
	}
	public void setProduct_sku(String product_sku) {
		this.product_sku = product_sku;
	}
	public String getDiscount_coupon_code() {
		return discount_coupon_code;
	}
	public void setDiscount_coupon_code(String discount_coupon_code) {
		this.discount_coupon_code = discount_coupon_code;
	}
	public String getCus_cod() {
		return cus_cod;
	}
	public void setCus_cod(String cus_cod) {
		this.cus_cod = cus_cod;
	}
	public String getCus_taxnumber() {
		return cus_taxnumber;
	}
	public void setCus_taxnumber(String cus_taxnumber) {
		this.cus_taxnumber = cus_taxnumber;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_email() {
		return cus_email;
	}
	public void setCus_email(String cus_email) {
		this.cus_email = cus_email;
	}
	public String getCus_tel() {
		return cus_tel;
	}
	public void setCus_tel(String cus_tel) {
		this.cus_tel = cus_tel;
	}
	public String getCus_tel2() {
		return cus_tel2;
	}
	public void setCus_tel2(String cus_tel2) {
		this.cus_tel2 = cus_tel2;
	}
	public String getCus_cel() {
		return cus_cel;
	}
	public void setCus_cel(String cus_cel) {
		this.cus_cel = cus_cel;
	}
	public String getCus_apikey() {
		return cus_apikey;
	}
	public void setCus_apikey(String cus_apikey) {
		this.cus_apikey = cus_apikey;
	}
	public String getCus_address() {
		return cus_address;
	}
	public void setCus_address(String cus_address) {
		this.cus_address = cus_address;
	}
	public String getCus_address_number() {
		return cus_address_number;
	}
	public void setCus_address_number(String cus_address_number) {
		this.cus_address_number = cus_address_number;
	}
	public String getCus_address_country() {
		return cus_address_country;
	}
	public void setCus_address_country(String cus_address_country) {
		this.cus_address_country = cus_address_country;
	}
	public String getCus_address_district() {
		return cus_address_district;
	}
	public void setCus_address_district(String cus_address_district) {
		this.cus_address_district = cus_address_district;
	}
	public String getCus_address_comp() {
		return cus_address_comp;
	}
	public void setCus_address_comp(String cus_address_comp) {
		this.cus_address_comp = cus_address_comp;
	}
	public String getCus_address_city() {
		return cus_address_city;
	}
	public void setCus_address_city(String cus_address_city) {
		this.cus_address_city = cus_address_city;
	}
	public String getCus_address_state() {
		return cus_address_state;
	}
	public void setCus_address_state(String cus_address_state) {
		this.cus_address_state = cus_address_state;
	}
	public String getCus_address_zip_code() {
		return cus_address_zip_code;
	}
	public void setCus_address_zip_code(String cus_address_zip_code) {
		this.cus_address_zip_code = cus_address_zip_code;
	}
	public String getRecurrence_startdate() {
		return recurrence_startdate;
	}
	public void setRecurrence_startdate(String recurrence_startdate) {
		this.recurrence_startdate = recurrence_startdate;
	}
	public String getRecurrence_status() {
		return recurrence_status;
	}
	public void setRecurrence_status(String recurrence_status) {
		this.recurrence_status = recurrence_status;
	}
	public String getRecurrence_status_name() {
		return recurrence_status_name;
	}
	public void setRecurrence_status_name(String recurrence_status_name) {
		this.recurrence_status_name = recurrence_status_name;
	}
	public String getRecurrence_interval() {
		return recurrence_interval;
	}
	public void setRecurrence_interval(String recurrence_interval) {
		this.recurrence_interval = recurrence_interval;
	}
	public String getRecurrence_interval_type() {
		return recurrence_interval_type;
	}
	public void setRecurrence_interval_type(String recurrence_interval_type) {
		this.recurrence_interval_type = recurrence_interval_type;
	}
	public String getRecurrence_count() {
		return recurrence_count;
	}
	public void setRecurrence_count(String recurrence_count) {
		this.recurrence_count = recurrence_count;
	}
	public String getRecurrence_first_payment() {
		return recurrence_first_payment;
	}
	public void setRecurrence_first_payment(String recurrence_first_payment) {
		this.recurrence_first_payment = recurrence_first_payment;
	}
	public String getAff_cod() {
		return aff_cod;
	}
	public void setAff_cod(String aff_cod) {
		this.aff_cod = aff_cod;
	}
	public String getAff_name() {
		return aff_name;
	}
	public void setAff_name(String aff_name) {
		this.aff_name = aff_name;
	}
	public String getAff_email() {
		return aff_email;
	}
	public void setAff_email(String aff_email) {
		this.aff_email = aff_email;
	}
	public String getAff_document_number() {
		return aff_document_number;
	}
	public void setAff_document_number(String aff_document_number) {
		this.aff_document_number = aff_document_number;
	}
	public String getAff_value() {
		return aff_value;
	}
	public void setAff_value(String aff_value) {
		this.aff_value = aff_value;
	}
	public String getTracker_trk() {
		return tracker_trk;
	}
	public void setTracker_trk(String tracker_trk) {
		this.tracker_trk = tracker_trk;
	}
	public String getTracker_trk2() {
		return tracker_trk2;
	}
	public void setTracker_trk2(String tracker_trk2) {
		this.tracker_trk2 = tracker_trk2;
	}
	public String getTracker_trk3() {
		return tracker_trk3;
	}
	public void setTracker_trk3(String tracker_trk3) {
		this.tracker_trk3 = tracker_trk3;
	}
	public String getTracker_utm_source() {
		return tracker_utm_source;
	}
	public void setTracker_utm_source(String tracker_utm_source) {
		this.tracker_utm_source = tracker_utm_source;
	}
	public String getTracker_utm_content() {
		return tracker_utm_content;
	}
	public void setTracker_utm_content(String tracker_utm_content) {
		this.tracker_utm_content = tracker_utm_content;
	}
	public String getTracker_utm_medium() {
		return tracker_utm_medium;
	}
	public void setTracker_utm_medium(String tracker_utm_medium) {
		this.tracker_utm_medium = tracker_utm_medium;
	}
	public String getTracker_utm_campaign() {
		return tracker_utm_campaign;
	}
	public void setTracker_utm_campaign(String tracker_utm_campaign) {
		this.tracker_utm_campaign = tracker_utm_campaign;
	}
	public String getUtm_source() {
		return utm_source;
	}
	public void setUtm_source(String utm_source) {
		this.utm_source = utm_source;
	}
	public String getUtm_content() {
		return utm_content;
	}
	public void setUtm_content(String utm_content) {
		this.utm_content = utm_content;
	}
	public String getUtm_medium() {
		return utm_medium;
	}
	public void setUtm_medium(String utm_medium) {
		this.utm_medium = utm_medium;
	}
	public String getUtm_campaign() {
		return utm_campaign;
	}
	public void setUtm_campaign(String utm_campaign) {
		this.utm_campaign = utm_campaign;
	}
	public String getSku_reference() {
		return sku_reference;
	}
	public void setSku_reference(String sku_reference) {
		this.sku_reference = sku_reference;
	}
	public String getEduzz_value() {
		return eduzz_value;
	}
	public void setEduzz_value(String eduzz_value) {
		this.eduzz_value = eduzz_value;
	}
	public String getOther_values() {
		return other_values;
	}
	public void setOther_values(String other_values) {
		this.other_values = other_values;
	}
	public String getTrans_itemsitem_id() {
		return trans_itemsitem_id;
	}
	public void setTrans_itemsitem_id(String trans_itemsitem_id) {
		this.trans_itemsitem_id = trans_itemsitem_id;
	}
	public String getTrans_itemsitem_name() {
		return trans_itemsitem_name;
	}
	public void setTrans_itemsitem_name(String trans_itemsitem_name) {
		this.trans_itemsitem_name = trans_itemsitem_name;
	}
	public String getTrans_itemsitem_value() {
		return trans_itemsitem_value;
	}
	public void setTrans_itemsitem_value(String trans_itemsitem_value) {
		this.trans_itemsitem_value = trans_itemsitem_value;
	}
	public String getTrans_itemsitem_coupon_code() {
		return trans_itemsitem_coupon_code;
	}
	public void setTrans_itemsitem_coupon_code(String trans_itemsitem_coupon_code) {
		this.trans_itemsitem_coupon_code = trans_itemsitem_coupon_code;
	}
	public String getTrans_itemsitem_coupon_value() {
		return trans_itemsitem_coupon_value;
	}
	public void setTrans_itemsitem_coupon_value(String trans_itemsitem_coupon_value) {
		this.trans_itemsitem_coupon_value = trans_itemsitem_coupon_value;
	}
	public String getTrans_itemsitem_product_id() {
		return trans_itemsitem_product_id;
	}
	public void setTrans_itemsitem_product_id(String trans_itemsitem_product_id) {
		this.trans_itemsitem_product_id = trans_itemsitem_product_id;
	}
	public String getTrans_itemsitem_product_name() {
		return trans_itemsitem_product_name;
	}
	public void setTrans_itemsitem_product_name(String trans_itemsitem_product_name) {
		this.trans_itemsitem_product_name = trans_itemsitem_product_name;
	}
	public String getTrans_itemsitem_product_refund() {
		return trans_itemsitem_product_refund;
	}
	public void setTrans_itemsitem_product_refund(String trans_itemsitem_product_refund) {
		this.trans_itemsitem_product_refund = trans_itemsitem_product_refund;
	}
	public String getTrans_itemsitem_product_sku_reference() {
		return trans_itemsitem_product_sku_reference;
	}
	public void setTrans_itemsitem_product_sku_reference(String trans_itemsitem_product_sku_reference) {
		this.trans_itemsitem_product_sku_reference = trans_itemsitem_product_sku_reference;
	}
	public String getTrans_itemsitem_product_partner_cod() {
		return trans_itemsitem_product_partner_cod;
	}
	public void setTrans_itemsitem_product_partner_cod(String trans_itemsitem_product_partner_cod) {
		this.trans_itemsitem_product_partner_cod = trans_itemsitem_product_partner_cod;
	}
	public String getTrans_itemsitem_product_chargetype() {
		return trans_itemsitem_product_chargetype;
	}
	public void setTrans_itemsitem_product_chargetype(String trans_itemsitem_product_chargetype) {
		this.trans_itemsitem_product_chargetype = trans_itemsitem_product_chargetype;
	}
	public String getTrans_barcode() {
		return trans_barcode;
	}
	public void setTrans_barcode(String trans_barcode) {
		this.trans_barcode = trans_barcode;
	}
	public String getTrans_orderid() {
		return trans_orderid;
	}
	public void setTrans_orderid(String trans_orderid) {
		this.trans_orderid = trans_orderid;
	}
	public String getTrans_currency() {
		return trans_currency;
	}
	public void setTrans_currency(String trans_currency) {
		this.trans_currency = trans_currency;
	}
	public String getTrans_job_id() {
		return trans_job_id;
	}
	public void setTrans_job_id(String trans_job_id) {
		this.trans_job_id = trans_job_id;
	}
	public String getTrans_job_status() {
		return trans_job_status;
	}
	public void setTrans_job_status(String trans_job_status) {
		this.trans_job_status = trans_job_status;
	}
	public String getRequest_token() {
		return request_token;
	}
	public void setRequest_token(String request_token) {
		this.request_token = request_token;
	}
	public String getBillet_url() {
		return billet_url;
	}
	public void setBillet_url(String billet_url) {
		this.billet_url = billet_url;
	}
	public String getPage_checkout_url() {
		return page_checkout_url;
	}
	public void setPage_checkout_url(String page_checkout_url) {
		this.page_checkout_url = page_checkout_url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTrans_bankslip() {
		return trans_bankslip;
	}
	public void setTrans_bankslip(String trans_bankslip) {
		this.trans_bankslip = trans_bankslip;
	}
	public String getRecurrence_canceled_at() {
		return recurrence_canceled_at;
	}
	public void setRecurrence_canceled_at(String recurrence_canceled_at) {
		this.recurrence_canceled_at = recurrence_canceled_at;
	}
	public String getProduct_chargetype() {
		return product_chargetype;
	}
	public void setProduct_chargetype(String product_chargetype) {
		this.product_chargetype = product_chargetype;
	}
	public String getRefund_date() {
		return refund_date;
	}
	public void setRefund_date(String refund_date) {
		this.refund_date = refund_date;
	} 
	
	public String getHoton_tok() {
		return hoton_tok;
	}

	public void setHoton_tok(String hoton_tok) {
		this.hoton_tok = hoton_tok;
	}

	public Venda obterVenda(String plataforma) {
		
		Venda venda = new Venda();
		
		venda.setTest(this.api_key.equals(this.TESTE));
		venda.setVendaStatusId(StatusVendaEnum.getID(this.trans_status));
		venda.setVendaStatus(StatusVendaEnum.getDesc(venda.getVendaStatusId()));
		venda.setVendaId(this.trans_cod);
		venda.setContatoStatusId(StatusContatoEnum.getID(this.trans_status));
		venda.setContatoStatus(StatusContatoEnum.getDesc(venda.getContatoStatusId()));
		venda.setFormaPagamentosId(FormaPagamentoEnum.getIDLogic(this.trans_paymentmethod));
		venda.setFormaPagamento(FormaPagamentoEnum.getDesc(venda.getFormaPagamentosId()));
		venda.setProduto(this.trans_itemsitem_product_name);
		venda.setCodProdu(this.trans_itemsitem_product_id);
		venda.setDocTipo("TAXNUMBER");
		venda.setDocumento(this.cus_taxnumber);
		venda.setMoeda(this.trans_currency);
		venda.setTipoVenda(this.aff_cod == null||this.aff_cod.equals("")?"Produtor":"Afiliado");
		venda.setValor(convertBigDecimal(this.trans_paid)); 
		venda.setValorTotal(convertBigDecimal(this.trans_value)); 
		venda.setParcelaAtual("1");
		venda.setNome(this.cus_name);
		venda.setEmail(this.cus_email);
		venda.setDdd(retornaTelefone("ddd"));
		venda.setTelefone(retornaTelefone("telefone"));
		venda.setDtCompra(formataData(this.trans_createdate));
		venda.setPlataforma(plataforma);
		venda.setCodInscrito(this.cus_cod);
		venda.setCodigoBarra(this.trans_barcode);
		venda.setLinkCodigoBarra(this.billet_url);
		venda.setComissao((this.aff_cod==null||this.aff_cod.equals(""))?convertBigDecimal(this.pro_value):convertBigDecimal(this.aff_value));
		venda.setDate(criaData(formataData(this.trans_createdate)));
		return venda;
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
		String mes = data.substring(4, 6);
		String dia = data.substring(6, 8);
		return dia+"/"+mes+"/"+ano;
	}
	
	private BigDecimal convertBigDecimal(String price) {
		if(price == null || price.equals(""))
			return new BigDecimal("0");
		
		return new BigDecimal(price);
	}

	private String retornaTelefone(String tipo) {
		
		if(tipo.equalsIgnoreCase("telefone") && this.cus_cel != null && 
				!this.cus_cel.equalsIgnoreCase("") && this.cus_cel.length() > 3)return this.cus_cel.substring(2);
		
		
		if(tipo.equalsIgnoreCase("ddd") && this.cus_cel != null && 
				!this.cus_cel.equalsIgnoreCase("") && this.cus_cel.length() > 3)return this.cus_cel.substring(0, 2);
		
		return "";
	}
	
	public void verifyParams() throws Exception {
		if(this.trans_cod == null || this.trans_cod.equalsIgnoreCase(""))throw new AtributoPostbackException("venda_codigo invalido", "TRANS_STATUS_INVALIDO");
		if(this.trans_status == null || this.trans_status.equalsIgnoreCase(""))throw new AtributoPostbackException("status invalido", "STATUS_INVALIDO");	
		if(this.trans_paymentmethod == null || this.trans_paymentmethod.equalsIgnoreCase(""))throw new AtributoPostbackException("forma pagamento invalido", "TRANS_PAYMENTMETHOD_INVALIDO");
		if(this.hoton_tok == null || this.hoton_tok.equalsIgnoreCase(""))throw new AtributoPostbackException("Token Hoton invalido", "HOTON_TOK_INVALIDO");
		if(this.api_key == null || this.api_key.equalsIgnoreCase(""))throw new AtributoPostbackException("Token Eduzz invalido", "API_KEY_INVALIDO");
		if(this.trans_createdate == null || this.trans_createdate.equalsIgnoreCase(""))throw new AtributoPostbackException("Data da compra invalido", "TRANS_CREATEDATE_INVALIDO");
	}
}
