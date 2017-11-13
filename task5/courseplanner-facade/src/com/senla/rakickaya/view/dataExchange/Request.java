package com.senla.rakickaya.view.dataExchange;

import java.util.HashMap;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;

public class Request implements IRequest {
	private Map<TagsRequest, String> map;

	public Request(Map<TagsRequest, String> map) {
		super();
		map = new HashMap<>();
	}

	@Override
	public void addEl(TagsRequest el, String object) {
		map.put(el, object);
	}

	@Override
	public String getObject(TagsRequest el) {
		return map.get(el);
	}

	@Override
	public Map<TagsRequest, String> getMap() {
		return map;
	}


}
