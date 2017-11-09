package com.senla.rakickaya.controller.api.services;

import java.util.List;

import com.senla.rakickaya.model.beans.Student;

public interface IStudentsService {
	public void addStudent(Student pStudent);

	public void removeStudent(long pId);

	public void updateStudent(Student pStudent);

	public Student getStudent(long pId);

	public List<Student> getListStudents();

	public int getTotalCountStudents();
}
