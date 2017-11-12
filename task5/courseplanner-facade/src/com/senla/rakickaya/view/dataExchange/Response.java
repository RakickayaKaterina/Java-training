package com.senla.rakickaya.view.dataExchange;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;

public class Response implements IResponse {
	private String statusCode;
	private List<String> data;

	public Response(String statusCode) {
		super();
		this.statusCode = statusCode;
		data = new ArrayList<>();
	}

	@Override
	public void addNote(String note) {
		data.add(note);
	}

	@Override
	public List<String> getData() {
		return data;
	}

	@Override
	public String getStatusCode() {
		return statusCode;
	}

	@Override
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
