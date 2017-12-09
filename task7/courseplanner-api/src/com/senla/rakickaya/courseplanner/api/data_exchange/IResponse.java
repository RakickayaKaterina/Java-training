package com.senla.rakickaya.courseplanner.api.data_exchange;

import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;

public interface IResponse {
	void addHead(TagsResponse tag, Object object);

	Object getObject(TagsResponse tag);
}
