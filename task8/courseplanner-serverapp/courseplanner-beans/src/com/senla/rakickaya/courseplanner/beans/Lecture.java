package com.senla.rakickaya.courseplanner.beans;

import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.utils.GeneratorId;

public class Lecture implements ILecture {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5270454231899654887L;
	private long idLecture;
	private String name;

	
	
	public Lecture() {
		super();
	}

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
		return String.format("Lecture [id=%s, name lecture =%s]", idLecture, name);
	}

	@Override
	public ILecture clone() throws CloneNotSupportedException {
		ILecture lecture = (ILecture) super.clone();
		lecture.setId(GeneratorId.getInstance().nextIdLecture());
		return lecture;
	}

}
