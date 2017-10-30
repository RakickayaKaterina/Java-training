package services;

import models.Student;
import repositories.IRepositoryStudents;
import utils.ArrayWorker;

public class ServiceStudent implements IServiceStudent {
	private IRepositoryStudents mRepositoryStudents;
	private IServiceCourses mServiceCourses;

	public ServiceStudent(IRepositoryStudents mRepositoryStudents) {
		super();
		this.mRepositoryStudents = mRepositoryStudents;
	}

	@Override
	public void addStudent(Student pStudent) {
		mRepositoryStudents.addStudent(pStudent);

	}

	@Override
	public void removeStudent(long pId) {
		mRepositoryStudents.removeStudent(pId);
		mServiceCourses.removeStudentFromCourse(pId);

	}

	@Override
	public void updateStudent(Student pStudent) {
		mRepositoryStudents.updateStudent(pStudent);

	}

	@Override
	public Student getStudent(long pId) {
		return mRepositoryStudents.getStudent(pId);
	}

	@Override
	public Student[] getListStudent() {
		return mRepositoryStudents.getListStudent();
	}

	@Override
	public void saveState() {
		// TODO save

	}

	@Override
	public void addCourseToStudent(long pIdCourse, long pIdStudent) {
		Student[] students = mRepositoryStudents.getListStudent();
		for (int i = 0; i < ArrayWorker.getLenghtArray(students); i++) {
			if (students[i].getId() == pIdStudent) {
				ArrayWorker.addToArray(mServiceCourses.getCourse(pIdCourse), students[i].getCourses());
				break;
			}
		}

	}

	@Override
	public void removeCourseFromStudent(long pIdCourse) {
		Student[] students = mRepositoryStudents.getListStudent();
		for (int i = 0; i < ArrayWorker.getLenghtArray(students); i++) {
			ArrayWorker.removeFromArray(pIdCourse, students[i].getCourses());
		}

	}

}
