package com.senla.rakickaya.courseplanner.beans;

import com.senla.rakickaya.courseplanner.api.beans.ILecture;

public class Lecture implements ILecture {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5270454231899654887L;
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

	@Override
	public String toString() {
		return "Lecture [ name=" + name + "]";
	}
	
}
