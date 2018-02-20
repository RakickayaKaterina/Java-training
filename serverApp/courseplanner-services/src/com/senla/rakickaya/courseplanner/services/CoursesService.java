package com.senla.rakickaya.courseplanner.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.dao.ICourseDao;
import com.senla.rakickaya.courseplanner.api.dao.ILectorDao;
import com.senla.rakickaya.courseplanner.api.dao.IStudentDao;
import com.senla.rakickaya.courseplanner.api.dao.enums.SortColumnCourse;
import com.senla.rakickaya.courseplanner.api.services.ICoursesService;
import com.senla.rakickaya.courseplanner.beans.Course;
import com.senla.rakickaya.courseplanner.csv.converters.ConverterFromCsv;
import com.senla.rakickaya.courseplanner.csv.converters.ConverterToCsv;
import com.senla.rakickaya.courseplanner.csv.converters.entities.CsvResponse;
import com.senla.rakickaya.courseplanner.dao.connection.DbConnector;
import com.senla.rakickaya.courseplanner.dependency.ServiceDI;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;
import com.senla.rakickaya.courseplanner.services.comparators.CountStudentsComparator;
import com.senla.rakickaya.courseplanner.utils.FileWorker;

public class CoursesService implements ICoursesService {

	private static final Logger logger = Logger.getLogger(CoursesService.class.getName());

	private ServiceDI serviceDI = ServiceDI.getInstance();

	public CoursesService() {
		super();
	}

	@Override
	public void addCourse(ICourse pCourse) {
		Connection connection = DbConnector.getInstance().getConnection();
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		try {
			courseDao.create(pCourse, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public void removeCourse(long pId) throws EntityNotFoundException {
		Connection connection = DbConnector.getInstance().getConnection();
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		try {
			courseDao.delete(pId, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public void updateCourse(ICourse pCourse) {
		Connection connection = DbConnector.getInstance().getConnection();
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		try {
			courseDao.update(pCourse, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public ICourse getCourse(long pId) {
		Connection connection = DbConnector.getInstance().getConnection();
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		ICourse course = null;
		try {
			course = courseDao.findById(pId, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return course;
	}

	@Override
	public List<ICourse> getCourses() {
		Connection connection = DbConnector.getInstance().getConnection();
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		List<ICourse> courses = null;
		try {
			courses = courseDao.getAllEntities(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return courses;
	}

	@Override
	public List<ICourse> getCoursesWithStudents() {
		Connection connection = DbConnector.getInstance().getConnection();
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		List<ICourse> courses = null;
		try {
			courses = courseDao.getCoursesWithStudents(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return courses;
	}

	@Override
	public List<ICourse> getCoursesSortedByCountStudent() {
		List<ICourse> courses = getCoursesWithStudents();
		courses.sort(new Comparator<ICourse>() {

			@Override
			public int compare(ICourse firstCourse, ICourse secondCourse) {
				Integer countStudents = firstCourse.getStudents().size();
				return countStudents.compareTo(secondCourse.getStudents().size());
			}
		});

		return courses;
	}

	@Override
	public List<ICourse> getCoursesWithLector() {
		Connection connection = DbConnector.getInstance().getConnection();
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		List<ICourse> courses = null;
		try {
			courses = courseDao.getCoursesWithLectorSortByLectorName(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return courses;
	}

	@Override
	public List<ICourse> getCoursesWithLectures() {
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		Connection connection = DbConnector.getInstance().getConnection();
		List<ICourse> courses = null;
		try {
			courses = courseDao.getCoursesWithLectures(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return courses;
	}

	@Override
	public synchronized void addStudentToCourse(long pIdStudent, long pIdCourse) {
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			courseDao.addStudentToCourse(pIdCourse, pIdStudent, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public synchronized void removeStudentFromCourse(long pIdStudent, long pIdCourse) throws EntityNotFoundException {
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			courseDao.removeStudentFromCourse(pIdCourse, pIdStudent, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public synchronized void addLectorToCourse(long pIdLector, long pIdCourse) {
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			courseDao.addLectorToCourse(pIdCourse, pIdLector, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public synchronized void removeLectorFromCourse(long pIdLector, long pIdCourse) throws EntityNotFoundException {
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			courseDao.removeLectorFromCourse(pIdCourse, pIdLector, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void addLectureToCourse(ILecture lecture, long pIdCourse) {
		Connection connection = DbConnector.getInstance().getConnection();
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		try {
			courseDao.addLectureToCourse(pIdCourse, lecture, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public void removeLectureFromCourse(long pIdLecture) throws EntityNotFoundException {
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			courseDao.removeLectureFromCourse(pIdLecture, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public List<ICourse> getSortedList(SortColumnCourse column) {
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		Connection connection = DbConnector.getInstance().getConnection();
		List<ICourse> courses = null;
		try {
			courses = courseDao.getSortedCourses(column, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return courses;
	}

	@Override
	public List<ICourse> getCoursesWithLectorAfterDate(Date pDate, SortColumnCourse column) {
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		Connection connection = DbConnector.getInstance().getConnection();
		List<ICourse> courses = null;
		try {
			courses = courseDao.getSortedCoursesByAfterDate(pDate, column, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return courses;
	}

	@Override
	public List<ICourse> getCoursesAfterDateWithStudents(Date pDate) {
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		Connection connection = DbConnector.getInstance().getConnection();
		List<ICourse> courses = null;
		try {
			courses = courseDao.getSortedCoursesWithStudentsAfterDate(pDate, connection);
			courses.sort(new CountStudentsComparator());

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return courses;
	}

	@Override
	public List<ICourse> getCurrentCoursesWithLector(Date pCurrentDate, SortColumnCourse column) {
		Connection connection = DbConnector.getInstance().getConnection();
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		List<ICourse> courses = null;
		try {
			courses = courseDao.getSortedCoursesByCurrentDate(pCurrentDate, column, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return courses;
	}

	@Override
	public int getTotalCountCourses() {
		Connection connection = DbConnector.getInstance().getConnection();
		int result = 0;
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		try {
			result = courseDao.getCount(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	@Override
	public List<ICourse> getPastCourses(Date startDateSub, Date endDateSub) {
		Connection connection = DbConnector.getInstance().getConnection();
		List<ICourse> courses = null;
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		try {
			courses = courseDao.getPastCourses(startDateSub, endDateSub, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return courses;

	}

	@Override
	public List<ILecture> getAllLectures() {
		Connection connection = DbConnector.getInstance().getConnection();
		List<ILecture> lectures = null;
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		try {
			lectures = courseDao.getAllLectures(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return lectures;
	}

	@Override
	public void cloneCourseById(long pId) throws CloneNotSupportedException, EntityNotFoundException {
		ICourse course = getCourse(pId);
		if (course == null) {
			throw new EntityNotFoundException();
		}
		ICourse cloneCourse = course.clone();
		addCourse(cloneCourse);
	}

	@Override
	public void exportCSV(String path) {
		FileWorker worker = new FileWorker(path);
		List<String> csvEntities = new ArrayList<>();
		List<ICourse> courses = getCourses();
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

	@SuppressWarnings("unchecked")
	@Override
	public void importCSV(String path) throws Exception {
		final String LECTOR = "lector";
		final String STUDENTS = "students";
		final String LECTURES = "lectures";
		List<ICourse> csvReadCourses = new ArrayList<>();
		ICourseDao courseDao = (ICourseDao) serviceDI.getObject(ICourseDao.class);
		IStudentDao studentDao = (IStudentDao) serviceDI.getObject(IStudentDao.class);
		ILectorDao lectorDao = (ILectorDao) serviceDI.getObject(ILectorDao.class);
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			connection.setAutoCommit(false);
			FileWorker worker = new FileWorker(path);
			List<String> list = worker.read();
			for (String str : list) {
				CsvResponse response = ConverterFromCsv.convert(str, Course.class);
				ICourse course = (ICourse) response.getEntity();
				Map<String, Object> map = response.getRelation();
				if (map.containsKey(LECTOR)) {
					Long idLector = (Long) map.get(LECTOR);
					ILector lector = lectorDao.findById(idLector, connection);
					course.setLector(lector);
				}
				if (map.containsKey(STUDENTS)) {
					List<Long> idStudents = (List<Long>) map.get(STUDENTS);
					List<IStudent> students = new ArrayList<>();
					for (Long idS : idStudents) {
						IStudent student = studentDao.findById(idS, connection);
						if (student != null) {
							students.add(student);
						}
					}
					course.setStudents(students);
				}
				if (map.containsKey(LECTURES)) {
					List<Long> idLectures = (List<Long>) map.get(LECTURES);
					List<ILecture> lectures = new ArrayList<>();
					for (Long idL : idLectures) {
						ILecture lecture = courseDao.findLectureById(idL, connection);
						if (lecture != null) {
							lectures.add(lecture);
						}
					}
					course.setLectures(lectures);
				}
				csvReadCourses.add(course);
			}
			Set<Long> maps = new HashSet<>();
			List<ICourse> existCourses = courseDao.getAllEntities(connection);
			existCourses.forEach(new Consumer<ICourse>() {
				@Override
				public void accept(ICourse t) {
					maps.add(t.getId());
				}
			});

			for (ICourse course : csvReadCourses) {
				if (maps.contains(course.getId())) {
					courseDao.update(course, connection);
				} else {
					courseDao.create(course, connection);
				}
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (Exception e1) {
			connection.rollback();
			logger.error(e1.getMessage());
		}

	}

}
