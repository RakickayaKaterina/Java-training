package com.senla.rakickaya.courseplanner.api.beans;

import java.util.Date;
import java.util.List;

public interface ICourse extends IEntity, Cloneable {
	public String getName();

	public String getDescription();

	public Date getStartDate();

	public Date getEndDate();

	public ILector getLector();

	public List<ILecture> getLectures();

	public void setLectures(List<ILecture> lectures);

	public void setLector(ILector lector);

	public List<IStudent> getStudents();

	public void setStudents(List<IStudent> students);

	public ICourse clone() throws CloneNotSupportedException;

	void setStartDate(Date startDate);

	void setEndDate(Date endDate);
}
