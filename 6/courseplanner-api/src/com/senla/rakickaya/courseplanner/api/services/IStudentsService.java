package com.senla.rakickaya.courseplanner.api.services;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;

public interface IStudentsService {
	public void addStudent(IStudent pStudent);

	public void removeStudent(long pId) throws EntityNotFoundException;

	public void updateStudent(IStudent pStudent);

	public IStudent getStudent(long pId);

	public List<IStudent> getListStudents();

	public int getTotalCountStudents();

	public void save();
}
