package com.senla.rakickaya.courseplanner.api.beans;

import java.util.List;

public interface IStudent extends IEntity {
	public String getNameStudent();

	public void setCourses(List<ICourse> courses);

	public List<ICourse> getCourses();
}
