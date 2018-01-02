package com.senla.rakickaya.courseplanner.repositories;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.repositories.IStudentsRepository;
import com.senla.rakickaya.courseplanner.repositories.filler.FillerRepositories;
import com.senla.rakickaya.courseplanner.utils.GeneratorId;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class StudentsRepository implements IStudentsRepository {

	private static StudentsRepository studentsRepository;
	private volatile List<IStudent> mStudents;
	private GeneratorId generator = GeneratorId.getInstance();

	public StudentsRepository() {
		super();
		mStudents = new ArrayList<>();
	}

	private StudentsRepository(List<IStudent> mStudents) {
		super();
		this.mStudents = mStudents;
	}

	public static StudentsRepository getInstance() {
		if (studentsRepository == null) {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			studentsRepository = new StudentsRepository(fillerRepositories.getStudents());
		}
		return studentsRepository;
	}

	@Override
	public boolean addStudent(IStudent pStudent) {
		if (pStudent.getId() == 0L) {
			pStudent.setId(generator.nextIdStudent());
		} else {
			if (getStudent(pStudent.getId()) != null) {
				return false;
			}
		}
		mStudents.add(pStudent);
		save();
		return true;
	}

	@Override
	public IStudent removeStudent(long pId) {
		IStudent student = ListWorker.removeItemById(mStudents, pId);
		if (student != null) {
			save();
		}
		return student;
	}

	@Override
	public void updateStudent(IStudent pStudent) {
		ListWorker.updateItem(mStudents, pStudent);
		save();

	}

	@Override
	public IStudent getStudent(long pId) {
		return (IStudent) ListWorker.getItemById(mStudents, pId);
	}

	@Override
	public List<IStudent> getStudents() {
		return mStudents;
	}

	@Override
	public void save() {
		FillerRepositories fillerRepositories = FillerRepositories.getInstance();
		fillerRepositories.writeStudentToFile(mStudents);
	}

}
