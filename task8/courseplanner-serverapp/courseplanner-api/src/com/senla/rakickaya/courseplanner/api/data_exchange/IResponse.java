package com.senla.rakickaya.courseplanner.api.data_exchange;

import java.io.Serializable;

import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;

public interface IResponse extends Serializable{
	void addHead(TagsResponse tag, Object object);

	Object getObject(TagsResponse tag);
}
