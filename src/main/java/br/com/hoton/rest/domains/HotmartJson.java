package br.com.hoton.rest.domains;

public class HotmartJson {

	private String hottok; 				
	private String subscriptionId; 		
	private String subscriberCode; 		
	private String cancellationDate; 	
	private String dateNextCharge; 		
	private String actualRecurrenceValue; 
	private String userName; 			
	private String userEmail; 			
	private String productName; 			
	private String subscriptionPlanName;
	private String hoton_tok;
	
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
	public String getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public String getSubscriberCode() {
		return subscriberCode;
	}
	public void setSubscriberCode(String subscriberCode) {
		this.subscriberCode = subscriberCode;
	}
	public String getCancellationDate() {
		return cancellationDate;
	}
	public void setCancellationDate(String cancellationDate) {
		this.cancellationDate = cancellationDate;
	}
	public String getDateNextCharge() {
		return dateNextCharge;
	}
	public void setDateNextCharge(String dateNextCharge) {
		this.dateNextCharge = dateNextCharge;
	}
	public String getActualRecurrenceValue() {
		return actualRecurrenceValue;
	}
	public void setActualRecurrenceValue(String actualRecurrenceValue) {
		this.actualRecurrenceValue = actualRecurrenceValue;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSubscriptionPlanName() {
		return subscriptionPlanName;
	}
	public void setSubscriptionPlanName(String subscriptionPlanName) {
		this.subscriptionPlanName = subscriptionPlanName;
	}
	@Override
	public String toString() {
		return "HotonDomainJson [hottok=" + hottok + ", subscriptionId=" + subscriptionId + ", subscriberCode="
				+ subscriberCode + ", cancellationDate=" + cancellationDate + ", dateNextCharge=" + dateNextCharge
				+ ", actualRecurrenceValue=" + actualRecurrenceValue + ", userName=" + userName + ", userEmail="
				+ userEmail + ", productName=" + productName + ", subscriptionPlanName=" + subscriptionPlanName + "]";
	}
	public void verifyParams(String hoton_tok) throws Exception {
		if(this.subscriberCode == null || this.subscriberCode.equalsIgnoreCase(""))throw new Exception("subscriberCode invalido", new Throwable("SUBSCRIBERCODE"));
		if(this.productName == null || this.productName.equalsIgnoreCase(""))throw new Exception("productName invalido", new Throwable("PRODUCTNAME"));	
		if(this.hottok == null || this.hottok.equalsIgnoreCase(""))throw new Exception("Token Hotmart invalido", new Throwable("HOTTOK"));
		if(this.userEmail == null || this.userEmail.equalsIgnoreCase(""))throw new Exception("User Email invalido", new Throwable("USEREMAIL"));
		this.hoton_tok = hoton_tok;
		if(this.hoton_tok == null || this.hoton_tok.equalsIgnoreCase(""))throw new Exception("Token Hotmart invalido", new Throwable("HOTTOK"));
		if(this.subscriberCode==null || this.subscriberCode.equalsIgnoreCase(""))throw new Exception("Codigo subscriber invalido", new Throwable("subscriberCode"));
	} 	
}
