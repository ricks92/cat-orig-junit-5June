package com.hsc.cat.TO;

public class ResponseTO {

	private String responseCode;
	private String responseMessage;
	private String role;
	private String firstName;
	private String lastName;
	private String userName;
	
	
	public ResponseTO() {
		
	}
	
	
	
public ResponseTO(String responseCode, String responseMessage, String role, String firstName, String lastName,
			String userName) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
	}



public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	


}
