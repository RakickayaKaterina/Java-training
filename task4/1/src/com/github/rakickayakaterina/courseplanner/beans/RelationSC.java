package com.github.rakickayakaterina.courseplanner.beans;

public class RelationSC extends Entity {
	private long id;
	private Student mStudent;
	private Course mCourse;

	public RelationSC(long id, Student mStudent, Course mCourse) {
		super();
		this.id = id;
		this.mStudent = mStudent;
		this.mCourse = mCourse;
	}

	@Override
	public long getId() {
		return id;

	}

	@Override
	public void setId(long id) {
		this.id = id;

	}

	public Student getStudent() {
		return mStudent;
	}

	public void setStudent(Student mStudent) {
		this.mStudent = mStudent;
	}

	public Course getCourse() {
		return mCourse;
	}

	public void setCourse(Course mCourse) {
		this.mCourse = mCourse;
	}

}
