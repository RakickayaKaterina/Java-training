package com.senla.rakickaya.courseplanner.api.repositories;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IStudent;

public interface IStudentsRepository {
	public void addStudent(IStudent pStudent);

	public IStudent removeStudent(long id);

	public void updateStudent(IStudent pStudent);

	public IStudent getStudent(long pId);

	public List<IStudent> getListStudents();

}
