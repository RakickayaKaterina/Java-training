package repositories;

import models.Student;

public interface IRepositoryStudents {
	public void addStudent(Student pStudent);

	public Student removeStudent(long id);

	public void updateStudent(Student pStudent);

	public Student getStudent(long pId);

	public Student[] getListStudent();

	public void saveState();
}
