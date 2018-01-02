package com.senla.rakickaya.courseplanner.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.csv.CsvEntity;
import com.senla.rakickaya.courseplanner.csv.CsvProperty;
import com.senla.rakickaya.courseplanner.csv.PropertyType;
import com.senla.rakickaya.courseplanner.utils.GeneratorId;
@CsvEntity(entityId = "id", filename = "courses.csv")
public class Course implements ICourse {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8297816945287329962L;
	@CsvProperty(columnNumber = 0, propertyType = PropertyType.SimpleProperty)
	private long id;
	@CsvProperty(columnNumber = 1, propertyType = PropertyType.SimpleProperty)
	private String name;
	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SimpleProperty)
	private String description;
	@CsvProperty(columnNumber = 3, propertyType = PropertyType.SimpleProperty)
	private Date startDate;
	@CsvProperty(columnNumber = 4, propertyType = PropertyType.SimpleProperty)
	private Date endDate;
	@CsvProperty(columnNumber = 5, propertyType = PropertyType.CompositeProperty, keyField="id")
	private ILector lector;
	@CsvProperty(columnNumber = 6, propertyType = PropertyType.CompositeProperty, keyField="id")
	private List<IStudent> students;
	@CsvProperty(columnNumber = 7, propertyType = PropertyType.CompositeProperty, keyField="idLecture")
	private List<ILecture> lectures;
	
	public Course() {
		super();
	}

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
		return "Course [id=" + id + ", name=" + name + ", description=" + description + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", lector=" + lector + ", students=" + students + ", lectures=" + lectures
				+ "]";
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
		ICourse course = (ICourse) super.clone();
		course.setStudents(new ArrayList<>());
		Date cloneStartDate = startDate != null ? (Date) startDate.clone() : null;
		Date cloneEndDate = endDate != null ? (Date) endDate.clone() : null;
		@SuppressWarnings("unchecked")
		ArrayList<ILecture> cloneLectures = (ArrayList<ILecture>) ((ArrayList<ILecture>) lectures).clone();
		course.setId(GeneratorId.getInstance().nextIdCourse());
		course.setStartDate(cloneStartDate);
		course.setEndDate(cloneEndDate);
		course.setLectures(cloneLectures);
		return course;
	}
}
