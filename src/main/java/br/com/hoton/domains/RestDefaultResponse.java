package br.com.hoton.domains;

public class RestDefaultResponse {

	private String status;
	private String message;
	
	public RestDefaultResponse() {}
	
	public RestDefaultResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "HotmartResponse [status=" + status + ", message=" + message + "]";
	}
	
}
