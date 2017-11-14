package com.senla.rakickaya.view.dataExchange;

import java.util.HashMap;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;

public class Request implements IRequest {
	private Map<TagsRequest, String> map;

	public Request(Map<TagsRequest, String> map) {
		super();
		this.map = map;
	}

	@Override
	public String getObject(TagsRequest el) {
		return map.get(el);
	}

}
