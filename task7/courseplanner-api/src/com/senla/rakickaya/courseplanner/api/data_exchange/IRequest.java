package com.senla.rakickaya.courseplanner.api.data_exchange;

import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;

public interface IRequest {
	String getObject(TagsRequest el);

}
