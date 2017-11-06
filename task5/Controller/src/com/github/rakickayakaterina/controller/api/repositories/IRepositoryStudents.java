package com.github.rakickayakaterina.controller.api.repositories;

import java.util.List;

import com.github.rakickayakaterina.model.beans.Student;

public interface IRepositoryStudents {
	public void addStudent(Student pStudent);

	public Student removeStudent(long id);

	public void updateStudent(Student pStudent);

	public Student getStudent(long pId);

	public List<Student> getListStudents();

}
