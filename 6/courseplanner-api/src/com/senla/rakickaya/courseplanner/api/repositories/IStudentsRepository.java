package com.senla.rakickaya.courseplanner.api.repositories;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IStudent;

public interface IStudentsRepository {
	boolean addStudent(IStudent pStudent);

	IStudent removeStudent(long id);

	void updateStudent(IStudent pStudent);

	IStudent getStudent(long pId);

	List<IStudent> getStudents();

	void save();
}
