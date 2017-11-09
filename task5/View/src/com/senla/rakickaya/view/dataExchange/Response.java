package com.senla.rakickaya.view.dataExchange;

import java.util.ArrayList;
import java.util.List;

public class Response {
	private String statusCode;
	private List<String> data;
	public Response(String statusCode) {
		super();
		this.statusCode = statusCode;
		data = new ArrayList<>();
	}
	public void addNote(String note){
		data.add(note);
	}
	public List<String> getData() {
		return data;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	

}
