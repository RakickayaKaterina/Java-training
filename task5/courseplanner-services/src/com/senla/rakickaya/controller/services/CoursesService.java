package com.senla.rakickaya.controller.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.IRelationSC;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.repositories.ICoursesRepository;
import com.senla.rakickaya.courseplanner.api.repositories.ILectorsRepository;
import com.senla.rakickaya.courseplanner.api.repositories.IRelationsRepository;
import com.senla.rakickaya.courseplanner.api.repositories.IStudentsRepository;
import com.senla.rakickaya.courseplanner.api.services.ICoursesService;
import com.senla.rakickaya.courseplanner.beans.RelationSC;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;
import com.senla.rakickaya.courseplanner.repositories.CoursesRepository;
import com.senla.rakickaya.courseplanner.repositories.LectorsRepository;
import com.senla.rakickaya.courseplanner.repositories.RelationsRepository;
import com.senla.rakickaya.courseplanner.repositories.StudentsRepository;
import com.senla.rakickaya.utils.DateWorker;
import com.senla.rakickaya.utils.GeneratorId;
import com.senla.rakickaya.utils.ListWorker;

public class CoursesService implements ICoursesService {
	private final ICoursesRepository mRepositoryCourses;
	private final IStudentsRepository mRepositoryStudents;
	private final IRelationsRepository mRepositoryRelations;
	private final ILectorsRepository mRepositoryLectors;

	public CoursesService() {
		super();
		this.mRepositoryCourses = CoursesRepository.getInstance();
		this.mRepositoryStudents = StudentsRepository.getInstance();
		this.mRepositoryRelations = RelationsRepository.getInstance();
		this.mRepositoryLectors = LectorsRepository.getInstance();
	}

	@Override
	public void addCourse(ICourse pCourse) {
		mRepositoryCourses.addCourse(pCourse);

	}

	@Override
	public void removeCourse(long pId) throws EntityNotFoundException {
		ICourse course = mRepositoryCourses.removeCourse(pId);
		if (course == null)
			throw new EntityNotFoundException();
		List<IStudent> students = mRepositoryStudents.getListStudents();
		for (int i = 0; i < students.size(); i++) {
			ListWorker.removeItemById(students.get(i).getCourses(), pId);
		}
		List<IRelationSC> relations = mRepositoryRelations.getListRelations();
		for (int i = 0; i < relations.size(); i++) {
			if (relations.get(i).getCourse().getId() == pId) {
				mRepositoryRelations.removeRelation(relations.get(i).getId());
			}
		}

	}

	@Override
	public void updateCourse(ICourse pCourse) {
		mRepositoryCourses.updateCourse(pCourse);

	}

	@Override
	public ICourse getCourse(long pId) {
		return mRepositoryCourses.getCourse(pId);
	}

	@Override
	public List<ICourse> getListCourses() {
		return mRepositoryCourses.getListCourses();
	}

	@Override
	public void addStudentToCourse(long pIdStudent, long pIdCourse) {
		ICourse course = mRepositoryCourses.getCourse(pIdCourse);
		IStudent student = mRepositoryStudents.getStudent(pIdStudent);
		course.getStudents().add(student);
		student.getCourses().add(course);
		mRepositoryRelations.addRelation(new RelationSC(GeneratorId.getInstance().getIdRelation(), student, course));
	}

	@Override
	public void removeStudentFromCourse(long pIdStudent, long pIdCourse) throws EntityNotFoundException {
		ICourse course = mRepositoryCourses.getCourse(pIdCourse);
		if (course == null) {
			throw new EntityNotFoundException();
		}
		IStudent student = ListWorker.removeItemById(course.getStudents(), pIdStudent);
		if (student == null) {
			throw new EntityNotFoundException();
		}
		List<IRelationSC> relations = mRepositoryRelations.getListRelations();
		for (int i = 0; i < relations.size(); i++) {
			if (relations.get(i).getStudent().getId() == pIdStudent) {
				mRepositoryRelations.removeRelation(relations.get(i).getId());
			}
		}

	}

	@Override
	public void addLectorToCourse(long pIdLector, long pIdCourse) {
		ICourse course = mRepositoryCourses.getCourse(pIdCourse);
		ILector lector = mRepositoryLectors.getLector(pIdLector);
		course.setLector(lector);

	}

	@Override
	public void removeLectorFromCourse(long pIdLector, long pIdCourse) throws EntityNotFoundException {
		ICourse course = mRepositoryCourses.getCourse(pIdCourse);
		if (course == null) {
			throw new EntityNotFoundException();
		}
		ILector lector = course.getLector();
		if (lector == null) {
			throw new EntityNotFoundException();
		}
		if (lector != null && lector.getId() == pIdLector) {
			course.setLector(null);

		}
	}

	@Override
	public void addLectureToCourse(ILecture lecture, long pIdCourse) {
		ICourse course = mRepositoryCourses.getCourse(pIdCourse);

		course.getLectures().add(lecture);

	}

	@Override
	public void removeLectureFromCourse(long pIdLecture, long pIdCourse) throws EntityNotFoundException {
		ICourse course = mRepositoryCourses.getCourse(pIdCourse);
		if (course == null) {
			throw new EntityNotFoundException();
		}
		ILecture lecture = ListWorker.removeItemById(course.getLectures(), pIdLecture);
		if (lecture == null) {
			throw new EntityNotFoundException();
		}

	}

	@Override
	public List<ICourse> getSortedList(Comparator<ICourse> mComparator) {
		List<ICourse> listCourses = mRepositoryCourses.getListCourses();
		listCourses.sort(mComparator);
		return listCourses;
	}

	@Override
	public List<ICourse> getListCoursesAfterDate(Date pDate, Comparator<ICourse> pComparator) {
		List<ICourse> resultList = new ArrayList<>();
		List<ICourse> courses = mRepositoryCourses.getListCourses();
		for (int i = 0; i < courses.size(); i++) {
			if (DateWorker.isAfterDate(pDate, courses.get(i).getStartDate())) {
				resultList.add(courses.get(i));
			}
		}
		resultList.sort(pComparator);
		return resultList;
	}

	@Override
	public List<ICourse> getListCurrentCourses(Date pCurrentDate, Comparator<ICourse> pComparator) {
		List<ICourse> resultList = new ArrayList<>();
		List<ICourse> courses = mRepositoryCourses.getListCourses();
		for (int i = 0; i < courses.size(); i++) {
			if (DateWorker.isBetweenDate(pCurrentDate, courses.get(i).getStartDate(), courses.get(i).getEndDate())) {
				resultList.add(courses.get(i));
			}
		}
		resultList.sort(pComparator);
		return resultList;
	}

	@Override
	public int getTotalCountCourses() {
		return mRepositoryCourses.getListCourses().size();
	}

	@Override
	public List<ICourse> getPastCourses(Date startDateSub, Date endDateSub) {
		List<ICourse> resultList = new ArrayList<>();
		List<ICourse> courses = mRepositoryCourses.getListCourses();
		for (int i = 0; i < courses.size(); i++) {
			if (DateWorker.isSubInterval(courses.get(i).getStartDate(), courses.get(i).getEndDate(), startDateSub,
					endDateSub)) {
				resultList.add(courses.get(i));
			}
		}
		return resultList;
	}

	@Override
	public List<ILecture> getAllLectures() {
		List<ICourse> courses = mRepositoryCourses.getListCourses();
		List<ILecture> resultList = new ArrayList<>();
		for (int i = 0; i < courses.size(); i++) {
			resultList.addAll(courses.get(i).getLectures());
		}
		return resultList;
	}

	@Override
	public void save() {
		mRepositoryCourses.save();
		mRepositoryRelations.save();

	}

}
