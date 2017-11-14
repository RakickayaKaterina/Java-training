package com.senla.rakickaya.controller.services;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.IRelationSC;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.repositories.ICoursesRepository;
import com.senla.rakickaya.courseplanner.api.repositories.IRelationsRepository;
import com.senla.rakickaya.courseplanner.api.repositories.IStudentsRepository;
import com.senla.rakickaya.courseplanner.api.services.IStudentsService;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;
import com.senla.rakickaya.courseplanner.repositories.CoursesRepository;
import com.senla.rakickaya.courseplanner.repositories.RelationsRepository;
import com.senla.rakickaya.courseplanner.repositories.StudentsRepository;
import com.senla.rakickaya.utils.ListWorker;

public class StudentsService implements IStudentsService {
	private final IStudentsRepository mRepositoryStudents;
	private final ICoursesRepository mRepositoryCourses;
	private final IRelationsRepository mRepositoryRelations;

	public StudentsService() {
		super();
		this.mRepositoryStudents = StudentsRepository.getInstance();
		this.mRepositoryCourses = CoursesRepository.getInstance();
		this.mRepositoryRelations = RelationsRepository.getInstance();
	}

	@Override
	public void addStudent(IStudent pStudent) {
		mRepositoryStudents.addStudent(pStudent);

	}

	@Override
	public void removeStudent(long id) throws EntityNotFoundException {
		IStudent removedStudent = mRepositoryStudents.removeStudent(id);
		if (removedStudent == null) {
			throw new EntityNotFoundException();
		}
		List<ICourse> courses = mRepositoryCourses.getListCourses();
		for (int i = 0; i < courses.size(); i++) {
			ListWorker.removeItemById(courses.get(i).getStudents(), id);
		}
		List<IRelationSC> relations = mRepositoryRelations.getListRelations();
		for (int i = 0; i < relations.size(); i++) {
			if (relations.get(i).getStudent().getId() == id) {
				mRepositoryRelations.removeRelation(relations.get(i).getId());
			}
		}
	}

	@Override
	public void updateStudent(IStudent pStudent) {
		mRepositoryStudents.updateStudent(pStudent);

	}

	@Override
	public IStudent getStudent(long pId) {
		return mRepositoryStudents.getStudent(pId);
	}

	@Override
	public List<IStudent> getListStudents() {
		return mRepositoryStudents.getListStudents();
	}

	@Override
	public int getTotalCountStudents() {
		return mRepositoryStudents.getListStudents().size();
	}

	public void save() {
		mRepositoryStudents.save();
	}

}
