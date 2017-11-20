package com.senla.rakickaya.courseplanner.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.utils.GeneratorId;

public class Course implements ICourse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8297816945287329962L;
	private long id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private ILector lector;
	private List<IStudent> students;
	private List<ILecture> lectures;

	public Course(long id, String name, String description, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		lectures = new ArrayList<>();
		students = new ArrayList<>();
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

	public ILector getLector() {
		return lector;
	}

	public List<ILecture> getLectures() {
		return lectures;
	}

	public void setLectures(List<ILecture> lectures) {
		this.lectures = lectures;
	}

	public void setLector(ILector lector) {
		this.lector = lector;
	}

	public void setStudents(List<IStudent> students) {
		this.students = students;
	}

	public List<IStudent> getStudents() {
		return students;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public ICourse clone() throws CloneNotSupportedException {
		ICourse course = (ICourse)super.clone();
		course.setStudents(new ArrayList<>());
		Date cloneStartDate = startDate !=null ? (Date)startDate.clone() : null;
		Date cloneEndDate = endDate !=null ? (Date)endDate.clone() : null;
		ArrayList<ILecture> cloneLectures = (ArrayList<ILecture>)((ArrayList<ILecture>)lectures).clone();
		course.setId(GeneratorId.getInstance().getIdCourse());
		course.setStartDate(cloneStartDate);
		course.setEndDate(cloneEndDate);
		course.setLectures(cloneLectures);
		return course;
	}
}
