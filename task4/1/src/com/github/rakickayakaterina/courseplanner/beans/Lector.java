package com.github.rakickayakaterina.courseplanner.beans;

public class Lector extends Entity {
	private long id;
	private String nameLector;

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
