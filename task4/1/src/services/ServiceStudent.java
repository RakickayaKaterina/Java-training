package services;

import models.Student;
import repositories.IRepositoryStudents;

public class ServiceStudent implements IServiceStudent {
	private IRepositoryStudents mRepositoryStudents;
	
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
		// TODO remove

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

}
