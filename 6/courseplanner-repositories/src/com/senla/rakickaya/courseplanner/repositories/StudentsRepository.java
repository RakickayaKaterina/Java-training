package com.senla.rakickaya.courseplanner.repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.repositories.IStudentsRepository;
import com.senla.rakickaya.courseplanner.repositories.filler.FillerRepositories;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class StudentsRepository implements IStudentsRepository {
	private List<IStudent> mStudents;
	private static StudentsRepository studentsRepository;

	public StudentsRepository() {
		super();
		mStudents = new ArrayList<>();
	}

	public static StudentsRepository getInstance() {
		if (studentsRepository == null) {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			studentsRepository = new StudentsRepository(fillerRepositories.getStudents());
		}
		return studentsRepository;
	}

	public StudentsRepository(List<IStudent> mStudents) {
		super();
		this.mStudents = mStudents;
	}

	@Override
	public boolean addStudent(IStudent pStudent) {
		if (getStudent(pStudent.getId()) == null) {
			mStudents.add(pStudent);
			save();
			return true;
		}
		return false;
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
	public List<IStudent> getListStudents() {
		return mStudents;
	}

	@Override
	public void save() {
		try {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			fillerRepositories.writeStudentToFile(mStudents);
		} catch (IOException e) {
			// TODO LOGGER
			e.printStackTrace();
		}

	}

}
