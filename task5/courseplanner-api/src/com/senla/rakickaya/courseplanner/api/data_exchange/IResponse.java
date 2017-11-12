package com.senla.rakickaya.courseplanner.api.data_exchange;

import java.util.List;

public interface IResponse {
	public void addNote(String note);

	public List<String> getData();

	public String getStatusCode();

	public void setStatusCode(String statusCode);
}
