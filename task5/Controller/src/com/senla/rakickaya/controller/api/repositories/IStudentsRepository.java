package com.senla.rakickaya.controller.api.repositories;

import java.util.List;

import com.senla.rakickaya.model.beans.Student;

public interface IStudentsRepository {
	public void addStudent(Student pStudent);

	public Student removeStudent(long id);

	public void updateStudent(Student pStudent);

	public Student getStudent(long pId);

	public List<Student> getListStudents();

}
