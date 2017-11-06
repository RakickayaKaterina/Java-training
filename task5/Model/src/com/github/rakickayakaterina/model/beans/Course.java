package com.github.rakickayakaterina.model.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course extends Entity {
	private long id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private Lector lector;
	private List<Student> students;
	private List<Lecture> lectures;

	public Course(long id, String name, String description, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		lectures = new ArrayList<>();
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
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Lector getLector() {
		return lector;
	}

	public List<Lecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<Lecture> lectures) {
		this.lectures = lectures;
	}

	public void setLector(Lector lector) {
		this.lector = lector;
	}

	public void addStudent(Student pStudent) {
		students.add(pStudent);
	}

	public void addLecture(Lecture pLecture) {
		lectures.add(pLecture);
	}

	public List<Student> getStudents() {
		return students;
	}

	/*
	 * @Override public String toString() { return String.
	 * format("Course [id=%s, name=%s, startDate=%s, endDate=%s, lector=%s, count=%s]"
	 * , id, name, DateWorker.dateFormat.format(startDate),
	 * DateWorker.dateFormat.format(endDate), lector,
	 * ArrayWorker.getLenghtArray(students)); }
	 */
}
