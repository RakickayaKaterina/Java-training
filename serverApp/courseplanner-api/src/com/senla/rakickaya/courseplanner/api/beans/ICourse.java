package com.senla.rakickaya.courseplanner.api.beans;

import java.util.Date;
import java.util.List;

public interface ICourse extends IEntity, Cloneable {
	String getName();

	String getDescription();

	Date getStartDate();

	Date getEndDate();

	ILector getLector();

	List<ILecture> getLectures();

	void setLectures(List<ILecture> lectures);

	void setLector(ILector lector);

	List<IStudent> getStudents();

	void setStudents(List<IStudent> students);

	ICourse clone() throws CloneNotSupportedException;

	void setStartDate(Date startDate);

	void setEndDate(Date endDate);
}
