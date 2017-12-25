package com.senla.rakickaya.courseplanner.beans;

import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.csv.CsvEntity;
import com.senla.rakickaya.courseplanner.csv.CsvProperty;
import com.senla.rakickaya.courseplanner.csv.PropertyType;
@CsvEntity(entityId = "id", filename = "lectors.csv")
public class Lector implements ILector {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7879323810348630802L;
	@CsvProperty(columnNumber = 0, propertyType = PropertyType.SimpleProperty)
	private long id;
	@CsvProperty(columnNumber = 1, propertyType = PropertyType.SimpleProperty)
	private String nameLector;
	
	
	public Lector() {
		super();
	}

	public Lector(long id, String nameLector) {
		super();
		this.id = id;
		this.nameLector = nameLector;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return nameLector;
	}

	@Override
	public String toString() {
		return String.format("Lector [id=%s, nameLector=%s]", id, nameLector);
	}
}
