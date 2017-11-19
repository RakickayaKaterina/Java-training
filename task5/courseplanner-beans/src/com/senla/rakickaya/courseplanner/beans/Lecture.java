package com.senla.rakickaya.courseplanner.beans;

import com.senla.rakickaya.courseplanner.api.beans.ILecture;

public class Lecture implements ILecture {
	private long idLecture;
	private String name;

	public Lecture(long idLecture, String name) {
		super();
		this.idLecture = idLecture;
		this.name = name;
	}

	@Override
	public long getId() {
		return idLecture;
	}

	@Override
	public void setId(long id) {
		this.idLecture = id;
	}

	public String getName() {
		return name;
	}
}
