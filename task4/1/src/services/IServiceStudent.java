package services;

import models.Student;

public interface IServiceStudent {
	public void addStudents(Student pStudent);

	public void removeStudent(Student pStudent);

	public void updateStudent(Student pStudent);

	public Student getStudent();

	public Student[] getListStudent();

	public void saveState();
}
