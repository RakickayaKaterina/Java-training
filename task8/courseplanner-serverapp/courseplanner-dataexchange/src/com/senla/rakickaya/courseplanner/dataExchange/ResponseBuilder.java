package com.senla.rakickaya.courseplanner.dataExchange;

import java.util.List;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.beans.IEntity;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsResponse;

public class ResponseBuilder {
	public IResponse build(List<? extends IEntity> data, String error) {
		IResponse response = new Response();
		if (!data.isEmpty()) {
			response.addHead(TagsResponse.DATA, data);
		} else {
			response.addHead(TagsResponse.MESSAGE, error);
		}
		return response;
	}

	public IResponse build(Map<ILector, Integer> data, String error) {
		IResponse response = new Response();
		if (!data.isEmpty()) {
			response.addHead(TagsResponse.DATA, data);
		} else {
			response.addHead(TagsResponse.MESSAGE, error);
		}
		return response;
	}

	public IResponse build(List<? extends IEntity> data, String message, String error) {
		IResponse response = new Response();
		if (!data.isEmpty()) {
			response.addHead(TagsResponse.DATA, data);
			response.addHead(TagsResponse.MESSAGE, message);
		} else {
			response.addHead(TagsResponse.MESSAGE, error);
		}
		return response;
	}

	public IResponse build(String message) {
		IResponse response = new Response();
		response.addHead(TagsResponse.MESSAGE, message);
		return response;
	}

	public IResponse build(Integer count) {
		IResponse response = new Response();
		response.addHead(TagsResponse.TOTAL_COUNT, count);
		return response;
	}

}
