package com.senla.rakickaya.courseplanner.repositories;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.repositories.IStudentsRepository;
import com.senla.rakickaya.courseplanner.repositories.filler.FillerRepositories;
import com.senla.rakickaya.utils.ListWorker;

public class StudentsRepository implements IStudentsRepository {
	private List<IStudent> mStudents;
	private static StudentsRepository studentsRepository;

	public static StudentsRepository getInstance() {
		if (studentsRepository == null) {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			studentsRepository = new StudentsRepository(fillerRepositories.getStudents());
		}
		return studentsRepository;
	}

	public StudentsRepository() {
		super();
		mStudents = new ArrayList<>();
	}

	public StudentsRepository(List<IStudent> mStudents) {
		super();
		this.mStudents = mStudents;
	}

	@Override
	public void addStudent(IStudent pStudent) {
		mStudents.add(pStudent);
	}

	@Override
	public IStudent removeStudent(long pId) {
		return ListWorker.removeItemById(mStudents, pId);
	}

	@Override
	public void updateStudent(IStudent pStudent) {
		ListWorker.updateItem(mStudents, pStudent);

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
		FillerRepositories fillerRepositories = FillerRepositories.getInstance();
		fillerRepositories.writeStudentToFile(mStudents);
		
	}

}
