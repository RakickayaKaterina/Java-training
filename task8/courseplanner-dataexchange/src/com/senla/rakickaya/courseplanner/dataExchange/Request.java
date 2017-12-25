package com.senla.rakickaya.courseplanner.dataExchange;

import java.util.Map;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.Intentions;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;

public class Request implements IRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3474083449405207915L;
	
	private Intentions intentions;
	
	private Map<TagsRequest, String> map;

	public Request(Map<TagsRequest, String> map) {
		super();
		this.map = map;
	}

	@Override
	public String getObject(TagsRequest el) {
		return map.get(el);
	}
	

	@Override
	public Intentions getIntent() {
		return intentions;
	}

	@Override
	public void setIntent(Intentions intent) {
		this.intentions = intent;
		
	}

}
