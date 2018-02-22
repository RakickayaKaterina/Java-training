package com.senla.rakickaya.courseplanner.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.api.dao.ILessonDao;
import com.senla.rakickaya.courseplanner.api.dao.enums.SortColumnLesson;
import com.senla.rakickaya.courseplanner.beans.Lecture;
import com.senla.rakickaya.courseplanner.beans.Lesson;
import com.senla.rakickaya.courseplanner.utils.DateWorker;

public class LessonDao extends AbstractDao<ILesson, Long> implements ILessonDao {

	private static final String LECTURE_TABLE = "lecture";
	private static final String LESSON_TABLE = "Lesson";
	private static final String ID_STUDENT = "idStudent";
	private static final String ID_LECTURE = "idLecture";
	private static final String ID_LESSON = "idLesson";
	private static final String FK_KEY_LECTURE = "Lecture_idLecture";
	private static final String NAME_LECTURE = "name";
	private static final String DATE_LESSON = "date";
	private static final String COUNT_STUDENTS = "count_students";

	private static Logger logger = Logger.getLogger(LessonDao.class);

	@Override
	public void deleteLessonByLecture(Long idLecture, Connection connection) throws Exception {
		String deleteSql = "delete from " + LESSON_TABLE + " where " + FK_KEY_LECTURE + " = " + idLecture;
		try (Statement statement = connection.createStatement()) {
			statement.executeQuery(deleteSql);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public List<ILesson> getSortedLessons(SortColumnLesson column, Connection connection) throws Exception {
		String selectSql = getReadSql() + " order by " + column.getOrderColumn();
		List<ILesson> lessons = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			ResultSet set = statement.executeQuery(selectSql);
			while (set.next()) {
				ILesson lesson = parseEntity(set);
				if (lesson != null) {
					lessons.add(lesson);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return lessons;
	}

	@Override
	protected String getCreateSql() {
		return "insert into " + LESSON_TABLE + "(" + DATE_LESSON + ", " + COUNT_STUDENTS + ", " + FK_KEY_LECTURE
				+ ") values (?, ?, ?)";
	}

	@Override
	protected void setValueToCreateStatement(PreparedStatement preparedStatement, ILesson entity) throws Exception {
		try {
			Date date = DateWorker.convert(entity.getDate());
			preparedStatement.setDate(1, date);
			preparedStatement.setInt(2, entity.getCountStudent());
			ILecture lecture = entity.getLecture();
			if (lecture != null) {
				preparedStatement.setLong(3, lecture.getId());
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}

	}

	@Override
	protected String getDeleteSql() {
		return "delete from " + LESSON_TABLE + " where " + ID_LESSON + " = ?";
	}

	@Override
	protected void setValueToDeleteStatement(PreparedStatement preparedStatement, Long idEntity) throws Exception {
		setOnlyIdToStatement(preparedStatement, idEntity);
	}

	@Override
	protected String getUpdateSql() {
		return "update " + LESSON_TABLE + " set " + DATE_LESSON + " = ? , " + COUNT_STUDENTS + " = ?, " + FK_KEY_LECTURE
				+ " = ? where " + ID_STUDENT + " = ? ";
	}

	@Override
	protected void setValueToUpdateStatement(PreparedStatement preparedStatement, ILesson entity) throws Exception {
		try {
			Date date = DateWorker.convert(entity.getDate());
			preparedStatement.setDate(1, date);
			preparedStatement.setLong(2, entity.getCountStudent());
			ILecture lecture = entity.getLecture();
			if (lecture != null) {
				preparedStatement.setLong(3, lecture.getId());
			}
			preparedStatement.setLong(4, entity.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}

	}

	@Override
	protected String getReadSql() {
		return "select * from " + LESSON_TABLE + " INNER JOIN " + LECTURE_TABLE + " ON " + FK_KEY_LECTURE + " = "
				+ ID_LECTURE;
	}

	@Override
	protected String getFindByIdSql() {
		return getReadSql() + "where " + ID_LESSON + " = ?";
	}

	@Override
	protected void setValueToFindStatement(PreparedStatement preparedStatement, Long idEntity) throws Exception {
		setOnlyIdToStatement(preparedStatement, idEntity);
	}

	private void setOnlyIdToStatement(PreparedStatement preparedStatement, Long idEntity) throws Exception {
		try {
			preparedStatement.setLong(1, idEntity);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	protected ILesson parseEntity(ResultSet resultSet) {
		ILesson lesson = null;
		try {
			Long idLesson = resultSet.getLong(ID_LESSON);
			java.util.Date date = resultSet.getDate(DATE_LESSON);
			int countStudents = resultSet.getInt(COUNT_STUDENTS);

			Long idLecture = resultSet.getLong(ID_LECTURE);
			String nameLecture = resultSet.getString(NAME_LECTURE);

			ILecture lecture = new Lecture(idLecture, nameLecture);
			lesson = new Lesson(idLesson, lecture, date, countStudents);

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return lesson;
	}

	@Override
	public List<ILesson> getAllEntities(java.util.Date date, Connection connection) throws Exception {
		String sql = getReadSql() + " where " + DATE_LESSON + " > ?";
		List<ILesson> lessons = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			Date dateAfter = DateWorker.convert(date);
			statement.setDate(1, dateAfter);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ILesson lesson = parseEntity(resultSet);
				if (lesson != null) {
					lessons.add(lesson);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return lessons;
	}

}
