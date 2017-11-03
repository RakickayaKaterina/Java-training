package com.github.rakickayakaterina.courseplanner.beans;

import com.github.rakickayakaterina.courseplanner.utils.ArrayWorker;

public class Student extends Entity {
	private long id;
	private String nameStudent;
	private Course[] courses;

	public Student(long id, String nameStudent) {
		super();
		this.id = id;
		this.nameStudent = nameStudent;
		courses = new Course[15];
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
		ArrayWorker.addToArray(pCourse, courses);
	}

	public Course[] getCourses() {
		return courses;
	}

}
