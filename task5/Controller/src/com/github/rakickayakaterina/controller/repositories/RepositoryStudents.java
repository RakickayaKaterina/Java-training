package com.github.rakickayakaterina.controller.repositories;

import java.util.ArrayList;
import java.util.List;

import com.github.rakickayakaterina.controller.api.repositories.IRepositoryStudents;
import com.github.rakickayakaterina.model.beans.Student;
import com.github.rakickayakaterina.utils.ListWorker;

public class RepositoryStudents implements IRepositoryStudents {
	private List<Student> mStudents;

	public RepositoryStudents() {
		super();
		mStudents = new ArrayList<>();
	}

	public RepositoryStudents(List<Student> mStudents) {
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
