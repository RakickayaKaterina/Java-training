package com.senla.rakickaya.controller.repositories;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.controller.api.repositories.IStudentsRepository;
import com.senla.rakickaya.model.beans.Student;
import com.senla.rakickaya.utils.ListWorker;

public class StudentsRepository implements IStudentsRepository {
	private List<Student> mStudents;

	public StudentsRepository() {
		super();
		mStudents = new ArrayList<>();
	}

	public StudentsRepository(List<Student> mStudents) {
		super();
		this.mStudents = mStudents;
	}

	@Override
	public void addStudent(Student pStudent) {
		mStudents.add(pStudent);
	}

	@Override
	public Student removeStudent(long pId) {
		return (Student) ListWorker.removeItemById(mStudents, pId);
	}

	@Override
	public void updateStudent(Student pStudent) {
		ListWorker.updateItem(mStudents, pStudent);

	}

	@Override
	public Student getStudent(long pId) {
		return (Student) ListWorker.getItemById(mStudents, pId);
	}

	@Override
	public List<Student> getListStudents() {
		return mStudents;
	}

}
