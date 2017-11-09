package com.senla.rakickaya.controller.api.services;

import java.util.List;

import com.senla.rakickaya.model.beans.Student;
import com.senla.rakickaya.model.exception.EntityNotFoundException;

public interface IStudentsService {
	public void addStudent(Student pStudent);

	public void removeStudent(long pId) throws EntityNotFoundException;

	public void updateStudent(Student pStudent);

	public Student getStudent(long pId);

	public List<Student> getListStudents();

	public int getTotalCountStudents();
}
