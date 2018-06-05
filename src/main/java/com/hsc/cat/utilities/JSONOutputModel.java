package com.hsc.cat.utilities;

public class JSONOutputModel {

	private int status;
	private Object data;
	private Object message;

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	
	public JSONOutputModel() {
		
	}
	public JSONOutputModel(int status, Object data, Object message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}
	
	
	
	
}
