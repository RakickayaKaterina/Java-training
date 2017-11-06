package com.github.rakickayakaterina.controller.services;

import java.util.List;

import com.github.rakickayakaterina.controller.api.repositories.IRepositoryCourses;
import com.github.rakickayakaterina.controller.api.repositories.IRepositoryRelations;
import com.github.rakickayakaterina.controller.api.repositories.IRepositoryStudents;
import com.github.rakickayakaterina.controller.api.services.IServiceStudents;
import com.github.rakickayakaterina.model.beans.Course;
import com.github.rakickayakaterina.model.beans.RelationSC;
import com.github.rakickayakaterina.model.beans.Student;
import com.github.rakickayakaterina.utils.ListWorker;

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
		List<Course> courses = mRepositoryCourses.getListCourses();
		for (int i = 0; i < courses.size(); i++) {
			ListWorker.removeItemById(courses.get(i).getStudents(), id);
		}
		List<RelationSC> relations = mRepositoryRelations.getListRelations();
		for (int i = 0; i < relations.size(); i++) {
			if (relations.get(i).getStudent().getId() == id) {
				mRepositoryRelations.removeRelation(relations.get(i).getId());
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
	public List<Student> getListStudents() {
		return mRepositoryStudents.getListStudents();
	}

	@Override
	public int getTotalCountStudents() {
		return mRepositoryStudents.getListStudents().size();
	}

}
