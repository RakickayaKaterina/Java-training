package services;

import models.Student;

public interface IServiceStudent {
	public void addStudent(Student pStudent);

	public void removeStudent(long pId);

	public void updateStudent(Student pStudent);

	public Student getStudent(long pId);

	public Student[] getListStudent();

	public void saveState();

	public void addCourseToStudent(long pIdCourse, long pIdStudents);

	public void removeCourseFromStudent(long pIdCourse);
}
