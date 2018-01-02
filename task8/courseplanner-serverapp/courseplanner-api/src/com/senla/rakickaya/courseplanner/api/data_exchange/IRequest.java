package com.senla.rakickaya.courseplanner.api.data_exchange;

import java.io.Serializable;

import com.senla.rakickaya.courseplanner.api.data_exchange.enums.Intentions;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;

public interface IRequest extends Serializable{
	String getObject(TagsRequest el);
	Intentions getIntent();
	void setIntent(Intentions intent);
}
