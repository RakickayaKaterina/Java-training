package com.senla.rakickaya.controller.services;

import java.util.List;

import com.senla.rakickaya.controller.api.repositories.ICoursesRepository;
import com.senla.rakickaya.controller.api.repositories.IRelationsRepository;
import com.senla.rakickaya.controller.api.repositories.IStudentsRepository;
import com.senla.rakickaya.controller.api.services.IStudentsService;
import com.senla.rakickaya.model.beans.Course;
import com.senla.rakickaya.model.beans.RelationSC;
import com.senla.rakickaya.model.beans.Student;
import com.senla.rakickaya.model.exception.EntityNotFoundException;
import com.senla.rakickaya.utils.ListWorker;

public class StudentsService implements IStudentsService {
	private final IStudentsRepository mRepositoryStudents;
	private final ICoursesRepository mRepositoryCourses;
	private final IRelationsRepository mRepositoryRelations;

	public StudentsService(IStudentsRepository mRepositoryStudents, ICoursesRepository mRepositoryCourses,
			IRelationsRepository mRepositoryRelations) {
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
	public void removeStudent(long id) throws EntityNotFoundException {
		Student removedStudent = mRepositoryStudents.removeStudent(id);
		if(removedStudent == null){
			throw new EntityNotFoundException();
		}
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
