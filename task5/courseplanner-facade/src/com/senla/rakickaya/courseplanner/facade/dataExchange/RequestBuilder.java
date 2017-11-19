package com.senla.rakickaya.courseplanner.facade.dataExchange;

import java.util.HashMap;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;

public class RequestBuilder {
	private Map<TagsRequest, String> map;

	public RequestBuilder() {
		super();
		map = new HashMap<>();
	}

	public RequestBuilder setHead(TagsRequest tag, String obj) {
		map.put(tag, obj);
		return this;
	}

	public IRequest build() {
		return new Request(map);
	}

}
