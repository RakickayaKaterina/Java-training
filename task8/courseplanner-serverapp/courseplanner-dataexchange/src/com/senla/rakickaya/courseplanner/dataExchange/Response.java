package com.senla.rakickaya.courseplanner.dataExchange;

import java.util.HashMap;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;

public class Response implements IResponse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8973541153679410500L;
	private Map<TagsResponse, Object> map;

	public Response() {
		super();
		map = new HashMap<>();
	}

	@Override
	public void addHead(TagsResponse tag, Object object) {
		map.put(tag, object);
	}

	@Override
	public Object getObject(TagsResponse tag) {
		return map.get(tag);
	}

}
