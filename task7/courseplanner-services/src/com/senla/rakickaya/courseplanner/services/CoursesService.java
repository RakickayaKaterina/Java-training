package com.senla.rakickaya.courseplanner.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.repositories.ICoursesRepository;
import com.senla.rakickaya.courseplanner.api.repositories.ILectorsRepository;
import com.senla.rakickaya.courseplanner.api.repositories.IStudentsRepository;
import com.senla.rakickaya.courseplanner.api.services.ICoursesService;
import com.senla.rakickaya.courseplanner.csv.CsvConverter;
import com.senla.rakickaya.courseplanner.csv.CsvObject;
import com.senla.rakickaya.courseplanner.csv.CsvWorker;
import com.senla.rakickaya.courseplanner.csv.converters.ConverterToCsv;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;
import com.senla.rakickaya.courseplanner.repositories.CoursesRepository;
import com.senla.rakickaya.courseplanner.repositories.LectorsRepository;
import com.senla.rakickaya.courseplanner.repositories.StudentsRepository;
import com.senla.rakickaya.courseplanner.utils.DateWorker;
import com.senla.rakickaya.courseplanner.utils.FileWorker;
import com.senla.rakickaya.courseplanner.utils.GeneratorId;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class CoursesService implements ICoursesService {
	private static final Logger logger = Logger.getLogger(CoursesRepository.class.getName());

	private final ICoursesRepository mRepositoryCourses;
	private final IStudentsRepository mRepositoryStudents;
	private final ILectorsRepository mRepositoryLectors;

	public CoursesService() {
		super();
		this.mRepositoryCourses = CoursesRepository.getInstance();
		this.mRepositoryStudents = StudentsRepository.getInstance();
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
		List<IStudent> students = mRepositoryStudents.getStudents();
		for (int i = 0; i < students.size(); i++) {
			ListWorker.removeItemById(students.get(i).getCourses(), pId);
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
	public List<ICourse> getCourses() {
		return mRepositoryCourses.getCourses();
	}

	@Override
	public void addStudentToCourse(long pIdStudent, long pIdCourse) {
		ICourse course = mRepositoryCourses.getCourse(pIdCourse);
		IStudent student = mRepositoryStudents.getStudent(pIdStudent);
		course.getStudents().add(student);
		student.getCourses().add(course);

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
		List<ICourse> listCourses = mRepositoryCourses.getCourses();
		listCourses.sort(mComparator);
		return listCourses;
	}

	@Override
	public List<ICourse> getCoursesAfterDate(Date pDate, Comparator<ICourse> pComparator) {
		List<ICourse> resultList = new ArrayList<>();
		List<ICourse> courses = mRepositoryCourses.getCourses();
		for (int i = 0; i < courses.size(); i++) {
			if (DateWorker.isAfterDate(pDate, courses.get(i).getStartDate())) {
				resultList.add(courses.get(i));
			}
		}
		resultList.sort(pComparator);
		return resultList;
	}

	@Override
	public List<ICourse> getCurrentCourses(Date pCurrentDate, Comparator<ICourse> pComparator) {
		List<ICourse> resultList = new ArrayList<>();
		List<ICourse> courses = mRepositoryCourses.getCourses();
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
		return mRepositoryCourses.getCourses().size();
	}

	@Override
	public List<ICourse> getPastCourses(Date startDateSub, Date endDateSub) {
		List<ICourse> resultList = new ArrayList<>();
		List<ICourse> courses = mRepositoryCourses.getCourses();
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
		return mRepositoryCourses.getAllLectures();
	}

	@Override
	public void cloneCourseById(long pId) throws CloneNotSupportedException, EntityNotFoundException {
		ICourse course = mRepositoryCourses.getCourse(pId);
		if (course == null) {
			throw new EntityNotFoundException();
		}
		ICourse cloneCourse = course.clone();
		mRepositoryCourses.addCourse(cloneCourse);

	}

	@Override
	public void exportCSV(String path) {
		FileWorker worker = new FileWorker(path);
		List<String> csvEntities = new ArrayList<>();
		List<ICourse> courses = mRepositoryCourses.getCourses();
		for (ICourse course : courses) {
			try {
				String csvString;
				csvString = ConverterToCsv.convert(course);
				csvEntities.add(csvString);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}
		worker.write(csvEntities);
	}

	@Override
	public void importCSV(String path) {
		CsvWorker worker = new CsvWorker(path);
		List<CsvObject> objects = worker.readCSV();
		List<ICourse> courses = new ArrayList<>();
		for (CsvObject obj : objects) {
			courses.add(
					CsvConverter.parseCourse(obj, mRepositoryStudents.getStudents(), mRepositoryLectors.getLectors()));
		}
		for (ICourse course : courses) {
			if (!mRepositoryCourses.addCourse(course)) {
				mRepositoryCourses.updateCourse(course);
			} else {
				GeneratorId generatorId = GeneratorId.getInstance();
				long id = generatorId.getIdCourse();
				if (course.getId() > id) {
					generatorId.setIdCourse(id);
				}
			}
		}
	}

}
