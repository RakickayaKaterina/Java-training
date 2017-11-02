package com.github.rakickayakaterina.courseplanner.api.services;

import com.github.rakickayakaterina.courseplanner.beans.Student;

public interface IServiceStudents {
	public void addStudent(Student pStudent);

	public void removeStudent(long id);

	public void updateStudent(Student pStudent);

	public Student getStudent(long pId);

	public Student[] getListStudent();

	public int getTotalCountStudents();
}
