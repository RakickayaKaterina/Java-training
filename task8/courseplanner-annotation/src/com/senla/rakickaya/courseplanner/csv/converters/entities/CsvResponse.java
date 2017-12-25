package com.senla.rakickaya.courseplanner.csv.converters.entities;

import java.util.*;

public class CsvResponse {
	private Object entity;
	private Map<String, Object> relation;

	public CsvResponse() {
		relation = new HashMap<>();
	}

	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public Map<String, Object> getRelation() {
		return relation;
	}

	public void setRelation(Map<String, Object> relation) {
		this.relation = relation;
	}

}
