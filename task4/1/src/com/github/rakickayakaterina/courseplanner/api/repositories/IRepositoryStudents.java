package com.github.rakickayakaterina.courseplanner.api.repositories;

import com.github.rakickayakaterina.courseplanner.beans.Student;

public interface IRepositoryStudents {
	public void addStudent(Student pStudent);

	public Student removeStudent(long id);

	public void updateStudent(Student pStudent);

	public Student getStudent(long pId);

	public Student[] getListStudent();

}
