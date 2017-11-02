package com.github.rakickayakaterina.courseplanner.services;

import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryCourses;
import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryRelations;
import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryStudents;
import com.github.rakickayakaterina.courseplanner.api.services.IServiceStudents;
import com.github.rakickayakaterina.courseplanner.beans.Course;
import com.github.rakickayakaterina.courseplanner.beans.RelationSC;
import com.github.rakickayakaterina.courseplanner.beans.Student;
import com.github.rakickayakaterina.courseplanner.utils.ArrayWorker;

public class ServiceStudents implements IServiceStudents {
	private final IRepositoryStudents mRepositoryStudents;
	private final IRepositoryCourses mRepositoryCourses;
	private final IRepositoryRelations mRepositoryRelations;

	public ServiceStudents(IRepositoryStudents mRepositoryStudents, IRepositoryCourses mRepositoryCourses,
			IRepositoryRelations mRepositoryRelations) {
		super();
		this.mRepositoryStudents = mRepositoryStudents;
		this.mRepositoryCourses = mRepositoryCourses;
		this.mRepositoryRelations = mRepositoryRelations;
	}

	@Override
	public void addStudent(Student pStudent) {
		mRepositoryStudents.addStudent(pStudent);

	}

	@Override
	public void removeStudent(long id) {
		mRepositoryStudents.removeStudent(id);
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			ArrayWorker.removeFromArray(id, courses[i].getStudents());
		}
		RelationSC[] relations = mRepositoryRelations.getListRelations();
		for (int i = 0; i < ArrayWorker.getLenghtArray(relations); i++) {
			if (relations[i] != null) {
				Student student = relations[i].getStudent();
				if (student != null && student.getId() == id) {

					ArrayWorker.removeFromArray(relations[i].getId(), relations);
				}
			}
		}
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
	public int getTotalCountStudents() {
		return ArrayWorker.getLenghtArray(mRepositoryStudents.getListStudent());
	}

}
