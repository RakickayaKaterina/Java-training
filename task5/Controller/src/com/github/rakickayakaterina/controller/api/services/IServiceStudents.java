package com.github.rakickayakaterina.controller.api.services;

import java.util.List;

import com.github.rakickayakaterina.model.beans.Student;

public interface IServiceStudents {
	public void addStudent(Student pStudent);

	public void removeStudent(long pId);

	public void updateStudent(Student pStudent);

	public Student getStudent(long pId);

	public List<Student> getListStudents();

	public int getTotalCountStudents();
}
