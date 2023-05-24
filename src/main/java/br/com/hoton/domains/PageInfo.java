package br.com.hoton.domains;

public class PageInfo {

	private Integer total_results;
	private String next_page_token;
	private String prev_page_token;
	private Integer results_per_page;
	public PageInfo(Integer total_results, String next_page_token, String prev_page_token, Integer results_per_page) {
		super();
		this.total_results = total_results;
		this.next_page_token = next_page_token;
		this.prev_page_token = prev_page_token;
		this.results_per_page = results_per_page;
	}
	public Integer getTotal_results() {
		return total_results;
	}
	public void setTotal_results(Integer total_results) {
		this.total_results = total_results;
	}
	public String getNext_page_token() {
		return next_page_token;
	}
	public void setNext_page_token(String next_page_token) {
		this.next_page_token = next_page_token;
	}
	public String getPrev_page_token() {
		return prev_page_token;
	}
	public void setPrev_page_token(String prev_page_token) {
		this.prev_page_token = prev_page_token;
	}
	public Integer getResults_per_page() {
		return results_per_page;
	}
	public void setResults_per_page(Integer results_per_page) {
		this.results_per_page = results_per_page;
	}
	
}
