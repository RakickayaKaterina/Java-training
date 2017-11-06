package com.github.rakickayakaterina.model.beans;

import java.util.ArrayList;
import java.util.List;

public class Student extends Entity {
	private long id;
	private String nameStudent;
	private List<Course> courses;

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

	public void addCourse(Course pCourse) {
		courses.add(pCourse);
	}

	public List<Course> getCourses() {
		return courses;
	}

}
