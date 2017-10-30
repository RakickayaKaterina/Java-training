package repositories;

import models.Student;
import utils.ArrayWorker;

public class RepositoryStudents implements IRepositoryStudents {
	private Student[] mStudents;

	public RepositoryStudents(int countStudents) {
		super();
		mStudents = new Student[countStudents];
	}
	
	

	public RepositoryStudents(Student[] mStudents) {
		super();
		this.mStudents = mStudents;
	}



	@Override
	public void addStudent(Student pStudent) {
		ArrayWorker.addToArray(pStudent, mStudents);
	}

	@Override
	public Student removeStudent(long pId) {
		return (Student) ArrayWorker.removeFromArray(pId, mStudents);
	}

	@Override
	public void updateStudent(Student pStudent) {
		ArrayWorker.updatePosition(pStudent, mStudents);

	}

	@Override
	public Student getStudent(long pId) {
		int position = ArrayWorker.getPositionById(pId, mStudents);
		if (position >= 0) {
			return mStudents[position];
		}
		return null;
	}

	@Override
	public Student[] getListStudent() {
		return mStudents;
	}

	@Override
	public void saveState() {
		// TODO saveMethod

	}

}
