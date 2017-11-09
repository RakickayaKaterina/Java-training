package com.senla.rakickaya.view.dataExchange;

import java.util.HashMap;
import java.util.Map;

public class Response {
	private Map<String, String> map;

	public Response(Map<String, String> map) {
		super();
		map = new HashMap<>();
	}

	public void addEl(String el, String object) {
		map.put(el, object);
	}

	public String getObject(String el) {
		return map.get(el);
	}

	public Map<String, String> getMap() {
		return map;
	}

}
