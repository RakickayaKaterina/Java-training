package com.senla.rakickaya.courseplanner.api.data_exchange;

import java.util.Map;

public interface IRequest {
	public void addEl(String el, String object);

	public String getObject(String el);

	public Map<String, String> getMap();
}
