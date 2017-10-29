package models;

import utils.ArrayWorker;

public class Lector extends Entity {
	private long id;
	private String nameLector;
	private Course[] courses;

	public Lector(long id, String nameLector, int countCourse) {
		super();
		this.id = id;
		this.nameLector = nameLector;
		courses = new Course[countCourse];
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

	public Course[] getCourses() {
		return courses;
	}

	@Override
	public String toString() {
		return String.format("Lector [id=%s, nameLector=%s,countCourses=%s]", id, nameLector,ArrayWorker.getLenghtArray(courses));
	}
	

}
