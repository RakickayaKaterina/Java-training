package com.senla.rakickaya.view.dataExchange;

import java.util.HashMap;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;

public class Request implements IRequest {
	private Map<String, String> map;

	public Request(Map<String, String> map) {
		super();
		map = new HashMap<>();
	}

	@Override
	public void addEl(String el, String object) {
		map.put(el, object);
	}

	@Override
	public String getObject(String el) {
		return map.get(el);
	}

	@Override
	public Map<String, String> getMap() {
		return map;
	}

}
