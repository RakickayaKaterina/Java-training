package com.senla.rakickaya.courseplanner.api.beans;

import java.util.List;

public interface ILector extends IEntity {
	String getName();
	List<ICourse> getCourses();
	void setCourses(List<ICourse> courses);
}
