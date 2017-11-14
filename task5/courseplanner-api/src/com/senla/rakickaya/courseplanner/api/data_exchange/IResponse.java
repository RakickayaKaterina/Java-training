package com.senla.rakickaya.courseplanner.api.data_exchange;

import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;

public interface IResponse {
	public void addHead(TagsResponse tag, Object object);

	public Object getObject(TagsResponse tag);
}
