package ro.ucv.inf.model;

public class RequestHeaderDto {

	private String headerName = "";
	
	private String headerValue = "";

	public RequestHeaderDto() {
	}
	
	public RequestHeaderDto(String headerName, String headerValue) {
		this.headerName = headerName;
		this.headerValue = headerValue;
	}
	
	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}
	
}
