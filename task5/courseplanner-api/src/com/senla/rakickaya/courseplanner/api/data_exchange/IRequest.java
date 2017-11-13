package com.senla.rakickaya.courseplanner.api.data_exchange;

import java.util.Map;

import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;

public interface IRequest {
	public void addEl(TagsRequest el, String object);

	public String getObject(TagsRequest el);

	public Map<TagsRequest, String> getMap();
}
