package com.senla.rakickaya.controller.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.controller.api.repositories.ICoursesRepository;
import com.senla.rakickaya.controller.api.repositories.ILectorsRepository;
import com.senla.rakickaya.controller.api.repositories.IRelationsRepository;
import com.senla.rakickaya.controller.api.repositories.IStudentsRepository;
import com.senla.rakickaya.controller.api.services.ICoursesService;
import com.senla.rakickaya.model.beans.Course;
import com.senla.rakickaya.model.beans.Lector;
import com.senla.rakickaya.model.beans.Lecture;
import com.senla.rakickaya.model.beans.RelationSC;
import com.senla.rakickaya.model.beans.Student;
import com.senla.rakickaya.model.exception.EntityNotFoundException;
import com.senla.rakickaya.utils.DateWorker;
import com.senla.rakickaya.utils.ListWorker;
import com.senla.rakickaya.utils.launcher.Launcher;

public class CoursesService implements ICoursesService {
	private final ICoursesRepository mRepositoryCourses;
	private final IStudentsRepository mRepositoryStudents;
	private final IRelationsRepository mRepositoryRelations;
	private final ILectorsRepository mRepositoryLectors;

	public CoursesService(ICoursesRepository mRepositoryCourses, IStudentsRepository mRepositoryStudents,
			IRelationsRepository mRepositoryRelations, ILectorsRepository mRepositoryLectors) {
		super();
		this.mRepositoryCourses = mRepositoryCourses;
		this.mRepositoryStudents = mRepositoryStudents;
		this.mRepositoryRelations = mRepositoryRelations;
		this.mRepositoryLectors = mRepositoryLectors;
	}

	@Override
	public void addCourse(Course pCourse) {
		mRepositoryCourses.addCourse(pCourse);

	}

	@Override
	public void removeCourse(long pId) throws EntityNotFoundException{
		Course course = mRepositoryCourses.removeCourse(pId);
		if(course==null)
			throw new EntityNotFoundException();
		List<Student> students = mRepositoryStudents.getListStudents();
		for (int i = 0; i < students.size(); i++) {
			ListWorker.removeItemById(students.get(i).getCourses(), pId);
		}
		List<RelationSC> relations = mRepositoryRelations.getListRelations();
		for (int i = 0; i < relations.size(); i++) {
			if (relations.get(i).getCourse().getId() == pId) {
				mRepositoryRelations.removeRelation(relations.get(i).getId());
			}
		}

	}

	@Override
	public void updateCourse(Course pCourse) {
		mRepositoryCourses.updateCourse(pCourse);

	}

	@Override
	public Course getCourse(long pId) {
		return mRepositoryCourses.getCourse(pId);
	}

	@Override
	public List<Course> getListCourses() {
		return mRepositoryCourses.getListCourses();
	}

	@Override
	public void addStudentToCourse(long pIdStudent, long pIdCourse) {
		Course course = mRepositoryCourses.getCourse(pIdCourse);
		Student student = mRepositoryStudents.getStudent(pIdStudent);
		course.addStudent(student);
		student.addCourse(course);
		mRepositoryRelations
				.addRelation(new RelationSC(Launcher.getInstance().getGeneratorId().getIdRelation(), student, course));
	}

	@Override
	public void removeStudentFromCourse(long pIdStudent, long pIdCourse) throws EntityNotFoundException {
		Course course = mRepositoryCourses.getCourse(pIdCourse);
		if(course == null){
			throw new EntityNotFoundException();
		}
		Student student = ListWorker.removeItemById(course.getStudents(), pIdStudent);
		if(student == null){
			throw new EntityNotFoundException();
		}
		List<RelationSC> relations = mRepositoryRelations.getListRelations();
		for (int i = 0; i < relations.size(); i++) {
			if (relations.get(i).getStudent().getId() == pIdStudent) {
				mRepositoryRelations.removeRelation(relations.get(i).getId());
			}
		}

	}

	@Override
	public void addLectorToCourse(long pIdLector, long pIdCourse) {
		Course course = mRepositoryCourses.getCourse(pIdCourse);
		Lector lector = mRepositoryLectors.getLector(pIdLector);
		course.setLector(lector);

	}

	// lector can be nullable
	@Override
	public void removeLectorFromCourse(long pIdLector, long pIdCourse) throws EntityNotFoundException {
		Course course = mRepositoryCourses.getCourse(pIdCourse);
		if(course ==null){
			throw new EntityNotFoundException();
		}
		Lector lector = course.getLector();
		if(lector == null){
			throw new EntityNotFoundException();
		}
		if (lector != null && lector.getId() == pIdLector) {
			course.setLector(null);

		}
	}

	@Override
	public void addLectureToCourse(Lecture lecture, long pIdCourse) {
		Course course = mRepositoryCourses.getCourse(pIdCourse);

		course.addLecture(lecture);

	}

	@Override
	public void removeLectureFromCourse(long pIdLecture, long pIdCourse) throws EntityNotFoundException{
		Course course = mRepositoryCourses.getCourse(pIdCourse);
		if(course == null){
			throw new EntityNotFoundException();
		}
		Lecture lecture = ListWorker.removeItemById(course.getLectures(), pIdLecture);
		if(lecture == null){
			throw new EntityNotFoundException();
		}
			

	}

	@Override
	public List<Course> getSortedList(Comparator<Course> mComparator) {
		List<Course> listCourses = mRepositoryCourses.getListCourses();
		listCourses.sort(mComparator);
		return listCourses;
	}

	@Override
	public List<Course> getListCoursesAfterDate(Date pDate, Comparator<Course> pComparator) {
		List<Course> resultList = new ArrayList<>();
		List<Course> courses = mRepositoryCourses.getListCourses();
		for (int i = 0; i < courses.size(); i++) {
			if (DateWorker.isAfterDate(pDate, courses.get(i).getStartDate())) {
				resultList.add(courses.get(i));
			}
		}
		resultList.sort(pComparator);
		return resultList;
	}

	@Override
	public List<Course> getListCurrentCourses(Date pCurrentDate, Comparator<Course> pComparator) {
		List<Course> resultList = new ArrayList<>();
		List<Course> courses = mRepositoryCourses.getListCourses();
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
	public List<Course> getPastCourses(Date startDateSub, Date endDateSub) {
		List<Course> resultList = new ArrayList<>();
		List<Course> courses = mRepositoryCourses.getListCourses();
		for (int i = 0; i < courses.size(); i++) {
			if (DateWorker.isSubInterval(courses.get(i).getStartDate(), courses.get(i).getEndDate(), startDateSub,
					endDateSub)) {
				resultList.add(courses.get(i));
			}
		}
		return resultList;
	}

}
