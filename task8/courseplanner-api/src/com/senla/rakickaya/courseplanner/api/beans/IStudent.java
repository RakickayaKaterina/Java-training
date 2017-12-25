package com.senla.rakickaya.courseplanner.api.beans;

import java.util.List;

public interface IStudent extends IEntity {
	String getNameStudent();

	void setCourses(List<ICourse> courses);

	List<ICourse> getCourses();
}
