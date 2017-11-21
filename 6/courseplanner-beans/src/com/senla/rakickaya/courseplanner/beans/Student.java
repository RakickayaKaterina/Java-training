package com.senla.rakickaya.courseplanner.beans;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;

public class Student implements IStudent {
	/**
	 * 
	 */
	private static final long serialVersionUID = -427046811176993970L;
	private long id;
	private String nameStudent;
	private List<ICourse> courses;

	public Student(long id, String nameStudent) {
		super();
		this.id = id;
		this.nameStudent = nameStudent;
		courses = new ArrayList<>();
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public String getNameStudent() {
		return nameStudent;
	}

	public void setCourses(List<ICourse> courses) {
		this.courses = courses;
	}

	public List<ICourse> getCourses() {
		return courses;
	}
 
	@Override
	public String toString() {
		return "Student [id=" + id + ", nameStudent=" + nameStudent +"]";
	}

}
