package com.senla.rakickaya.courseplanner.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.api.dao.ICourseDao;
import com.senla.rakickaya.courseplanner.api.dao.ILessonDao;
import com.senla.rakickaya.courseplanner.api.dao.enums.SortColumnLesson;
import com.senla.rakickaya.courseplanner.api.services.ITimeTableService;
import com.senla.rakickaya.courseplanner.beans.Lesson;
import com.senla.rakickaya.courseplanner.configuration.Config;
import com.senla.rakickaya.courseplanner.csv.converters.ConverterFromCsv;
import com.senla.rakickaya.courseplanner.csv.converters.ConverterToCsv;
import com.senla.rakickaya.courseplanner.csv.converters.entities.CsvResponse;
import com.senla.rakickaya.courseplanner.dao.connection.DbConnector;
import com.senla.rakickaya.courseplanner.dependency.ServiceDI;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;
import com.senla.rakickaya.courseplanner.utils.FileWorker;

public class TimeTableService implements ITimeTableService {
	private static final Logger logger = Logger.getLogger(TimeTableService.class.getName());

	private ILessonDao lessonDao = (ILessonDao) ServiceDI.getInstance().getObject(ILessonDao.class);;

	public TimeTableService() {
		super();
	}

	@Override
	public void addLesson(ILesson pLesson) {
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			lessonDao.create(pLesson, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public void createLesson(long idLecture, Date dateForLecture, int countStudent) throws Exception {
		ICourseDao courseDao = (ICourseDao) ServiceDI.getInstance().getObject(ICourseDao.class);
		Connection connection = DbConnector.getInstance().getConnection();
		ILecture lecture = courseDao.findLectureById(idLecture, connection);

		List<ILesson> timeTable = getLessons(dateForLecture);
		int amount = 0;
		for (ILesson lesson : timeTable) {
			amount += lesson.getCountStudent();
		}
		if (lecture != null && amount + countStudent <= Config.getInstance().getAmountStudents()) {
			lessonDao.create(new Lesson(0L, lecture, dateForLecture, countStudent), connection);
		} else {
			throw new Exception("Limit Students");
		}

	}

	@Override
	public void removeLesson(long pId) {
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			lessonDao.delete(pId, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public void updateLesson(ILesson pLesson) {
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			lessonDao.update(pLesson, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public ILesson getLesson(long pId) {
		Connection connection = DbConnector.getInstance().getConnection();
		ILesson lesson = null;
		try {
			lesson = lessonDao.findById(pId, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return lesson;
	}

	@Override
	public List<ILesson> getLessons() {
		Connection connection = DbConnector.getInstance().getConnection();
		List<ILesson> lessons = null;
		try {
			lessons = lessonDao.getAllEntities(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return lessons;
	}

	@Override
	public List<ILesson> getLessons(Date date) {
		Connection connection = DbConnector.getInstance().getConnection();
		List<ILesson> lessons = null;
		try {
			lessons = lessonDao.getAllEntities(date, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return lessons;
	}

	@Override
	public void removeLessonByLecture(long idLecture) throws EntityNotFoundException {
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			lessonDao.deleteLessonByLecture(idLecture, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void exportCSV(String path) {
		FileWorker worker = new FileWorker(path);
		List<String> csvEntities = new ArrayList<>();
		List<ILesson> lessons = getLessons();
		for (ILesson lesson : lessons) {
			try {
				String csvString;
				csvString = ConverterToCsv.convert(lesson);
				csvEntities.add(csvString);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}
		worker.write(csvEntities);
	}

	@Override
	public void importCSV(String path) throws SQLException {

		final String LECTURE = "mLecture";
		List<ILesson> lessons = new ArrayList<>();
		ICourseDao courseDao = (ICourseDao) ServiceDI.getInstance().getObject(ICourseDao.class);
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			connection.setAutoCommit(false);
			FileWorker worker = new FileWorker(path);
			List<String> list = worker.read();
			for (String str : list) {
				CsvResponse response = ConverterFromCsv.convert(str, Lesson.class);
				ILesson lesson = (ILesson) response.getEntity();
				Map<String, Object> map = response.getRelation();
				if (map.containsKey(LECTURE)) {
					Long idLecture = (Long) map.get(LECTURE);
					ILecture lecture = courseDao.findLectureById(idLecture, connection);
					lesson.setLecture(lecture);
				}
				lessons.add(lesson);
			}
			Set<Long> maps = new HashSet<>();
			List<ILesson> existLesson = lessonDao.getAllEntities(connection);
			existLesson.forEach(new Consumer<ILesson>() {
				@Override
				public void accept(ILesson t) {
					maps.add(t.getId());
				}
			});

			for (ILesson lesson : lessons) {
				if (maps.contains(lesson.getId())) {
					lessonDao.update(lesson, connection);
				} else {
					lessonDao.create(lesson, connection);
				}
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			logger.error(e.getMessage());
			connection.rollback();
		}
	}

	@Override
	public List<ILesson> getSortedList(SortColumnLesson column) {
		Connection connection = DbConnector.getInstance().getConnection();
		List<ILesson> lessons = null;
		try {
			lessons = lessonDao.getSortedLessons(column, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return lessons;

	}
}
