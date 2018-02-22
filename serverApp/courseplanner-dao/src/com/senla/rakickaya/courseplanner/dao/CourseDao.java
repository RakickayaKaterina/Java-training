package com.senla.rakickaya.courseplanner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.dao.ICourseDao;
import com.senla.rakickaya.courseplanner.api.dao.enums.SortColumnCourse;
import com.senla.rakickaya.courseplanner.beans.Course;
import com.senla.rakickaya.courseplanner.beans.Lector;
import com.senla.rakickaya.courseplanner.beans.Lecture;
import com.senla.rakickaya.courseplanner.beans.Student;
import com.senla.rakickaya.courseplanner.utils.DateWorker;

public class CourseDao extends AbstractDao<ICourse, Long> implements ICourseDao {
	private static final String COURSE_TABLE = "course";
	private static final String STUDENT_TABLE = "student";
	private static final String LECTURE_TABLE = "lecture";
	private static final String LECTOR_TABLE = "lector";
	private static final String COURSE_STUDENT_TABLE = "Course_Student";
	private static final String ID_COURSE = "idCourse";
	private static final String ID_STUDENT = "idStudent";
	private static final String ID_LECTURE = "idLecture";
	private static final String ID_LECTOR = "idLector";
	private static final String NAME_LECTOR = "nameLector";
	private static final String FK_KEY_COURSE = "Course_idCourse";
	private static final String FK_KEY_STUDENT = "Student_idStudent";
	private static final String FK_KEY_LECTOR = "Lector_idLector";
	private static final String NAME_COURSE = "name";
	private static final String DESCRIPTION_COURSE = "description";
	private static final String START_DATE_COURSE = "start_date";
	private static final String END_DATE_COURSE = "end_date";
	private static final String NAME_STUDENT = "nameStudent";
	private static final String NAME_LECTURE = "name";

	private static Logger logger = Logger.getLogger(CourseDao.class);

	@Override
	public List<ICourse> getCoursesWithStudents(Connection connection) throws Exception {
		String sqlJoin = "select " + COURSE_TABLE + ".* , " + STUDENT_TABLE + ".* from " + COURSE_TABLE + " LEFT JOIN "
				+ COURSE_STUDENT_TABLE + " ON " + ID_COURSE + " = " + FK_KEY_COURSE + " LEFT JOIN " + STUDENT_TABLE
				+ " ON " + FK_KEY_STUDENT + " = " + ID_STUDENT;
		Map<Long, ICourse> courseMap = new HashMap<>();
		try (Statement statement = connection.createStatement()) {

			ResultSet resultSet = statement.executeQuery(sqlJoin);
			while (resultSet.next()) {
				ICourse course = parseEntity(resultSet);
				IStudent student = parseStudent(resultSet);

				ICourse previouslyReadCourse = courseMap.get(course.getId());
				if (previouslyReadCourse == null) {
					List<IStudent> students = new ArrayList<>();
					if (student != null) {
						students.add(student);
					}
					course.setStudents(students);
					courseMap.put(course.getId(), course);
					continue;
				}
				if (student != null) {
					previouslyReadCourse.getStudents().add(student);
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return new ArrayList<>(courseMap.values());
	}

	@Override
	public void addStudentToCourse(Long pIdCourse, Long pIdStudent, Connection connection) throws Exception {
		String insertQuery = "INSERT INTO " + COURSE_STUDENT_TABLE + "(" + FK_KEY_COURSE + "," + FK_KEY_STUDENT
				+ ") values (?,?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
			preparedStatement.setLong(1, pIdCourse);
			preparedStatement.setLong(2, pIdStudent);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public ILecture findLectureById(Long idLecture, Connection connection) throws Exception {

		String insertQuery = "select * from " + LECTURE_TABLE + " where " + ID_LECTURE + " = " + idLecture;
		ILecture lecture = null;
		try (Statement statement = connection.createStatement()) {

			ResultSet resultSet = statement.executeQuery(insertQuery);
			if (resultSet.next()) {
				lecture = parseLecture(resultSet);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return lecture;
	}

	@Override
	public void removeStudentFromCourse(Long pIdCourse, Long pIdStudent, Connection connection) throws Exception {
		String insertQuery = "delete from " + COURSE_STUDENT_TABLE + " where " + FK_KEY_COURSE + " = ? AND "
				+ FK_KEY_STUDENT + " = ? ";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
			preparedStatement.setLong(1, pIdCourse);
			preparedStatement.setLong(2, pIdStudent);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public void addLectorToCourse(Long pIdCourse, Long pIdLector, Connection connection) throws Exception {
		String insertQuery = "update " + COURSE_TABLE + " set  " + FK_KEY_LECTOR + " = ? where " + ID_COURSE + " = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
			preparedStatement.setLong(1, pIdLector);
			preparedStatement.setLong(2, pIdCourse);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public void removeLectorFromCourse(Long pIdCourse, Long pIdLector, Connection connection) throws Exception {
		String insertQuery = "update " + COURSE_TABLE + " set  " + FK_KEY_LECTOR + " = NULL where " + FK_KEY_LECTOR
				+ " = ? AND " + ID_COURSE + " = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
			preparedStatement.setLong(1, pIdLector);
			preparedStatement.setLong(2, pIdCourse);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public List<ICourse> getCoursesWithLectures(Connection connection) throws Exception {
		String sqlJoin = "select * from " + COURSE_TABLE + " LEFT JOIN " + LECTURE_TABLE + " ON " + FK_KEY_COURSE
				+ " = " + ID_COURSE;
		Map<Long, ICourse> courseMap = new HashMap<>();
		try (Statement statement = connection.createStatement()) {

			ResultSet resultSet = statement.executeQuery(sqlJoin);
			while (resultSet.next()) {
				ICourse course = parseEntity(resultSet);
				ILecture lecture = parseLecture(resultSet);

				ICourse previouslyReadCourse = courseMap.get(course.getId());
				if (previouslyReadCourse == null) {
					List<ILecture> lectures = new ArrayList<>();
					if (lecture != null) {
						lectures.add(lecture);
					}
					course.setLectures(lectures);
					courseMap.put(course.getId(), course);
					continue;
				}
				if (lecture != null) {
					previouslyReadCourse.getLectures().add(lecture);
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return new ArrayList<>(courseMap.values());
	}

	@Override
	public List<ICourse> getCoursesWithLectorSortByLectorName(Connection connection) throws Exception {
		String sql = "select * from " + COURSE_TABLE + " LEFT JOIN " + LECTOR_TABLE + " ON " + FK_KEY_LECTOR + " = "
				+ ID_LECTOR + " order by " + NAME_LECTOR;
		List<ICourse> courses = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {

				ICourse course = parseEntity(resultSet);
				if (course != null) {
					ILector lector = parseLector(resultSet);
					course.setLector(lector);
					courses.add(course);
				}

			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return courses;
	}

	@Override
	public void addLectureToCourse(Long pIdCourse, ILecture lecture, Connection connection) throws Exception {
		String sqlInsert = "insert into " + LECTURE_TABLE + "(" + NAME_LECTURE + ", " + FK_KEY_COURSE
				+ ") values (?, ?)";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
			preparedStatement.setString(1, lecture.getName());
			preparedStatement.setLong(2, pIdCourse);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}

	}

	@Override
	public void removeLectureFromCourse(Long pIdLecture, Connection connection) throws Exception {
		String deleteLecture = "delete from " + LECTURE_TABLE + " where " + ID_LECTURE + " = " + pIdLecture;
		try (Statement statement = connection.createStatement()) {
			statement.executeQuery(deleteLecture);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public List<ICourse> getSortedCourses(SortColumnCourse column, Connection connection) throws Exception {
		String sql = "select * from " + COURSE_TABLE + " order by " + column.getOrderColumn();
		List<ICourse> courses = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {

				ICourse course = parseEntity(resultSet);
				if (course != null) {
					courses.add(course);
				}

			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return courses;
	}

	@Override
	public List<ICourse> getSortedCoursesByAfterDate(Date pDate, SortColumnCourse column, Connection connection)
			throws Exception {
		String sql = "select * from " + COURSE_TABLE + " where " + START_DATE_COURSE + " > ? order by "
				+ column.getOrderColumn();
		List<ICourse> courses = new ArrayList<>();
		java.sql.Date date = DateWorker.convert(pDate);
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setDate(1, date);
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {

				ICourse course = parseEntity(resultSet);
				if (course != null) {
					courses.add(course);
				}

			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return courses;
	}

	@Override
	public List<ICourse> getSortedCoursesByCurrentDate(Date pCurrentDate, SortColumnCourse column,
			Connection connection) throws Exception {
		String sql = "select * from " + COURSE_TABLE + " where ? between " + START_DATE_COURSE + " and "
				+ END_DATE_COURSE + " order by " + column.getOrderColumn();
		List<ICourse> courses = new ArrayList<>();
		java.sql.Date date = DateWorker.convert(pCurrentDate);
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setDate(1, date);
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {

				ICourse course = parseEntity(resultSet);
				if (course != null) {
					courses.add(course);
				}

			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return courses;
	}

	@Override
	public int getCount(Connection connection) throws Exception {
		String query = "select count(*) from " + COURSE_TABLE;
		int count = 0;
		try (Statement statement = connection.createStatement()) {
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				count = result.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return count;
	}

	@Override
	public List<ICourse> getPastCourses(Date startDateSub, Date endDateSub, Connection connection) throws Exception {
		String query = "select * from " + COURSE_TABLE + " where " + START_DATE_COURSE + " < ? and " + END_DATE_COURSE
				+ " > ?";
		java.sql.Date startDate = DateWorker.convert(startDateSub);
		java.sql.Date endDate = DateWorker.convert(endDateSub);
		List<ICourse> courses = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setDate(1, startDate);
			preparedStatement.setDate(2, endDate);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				ICourse course = parseEntity(resultSet);
				if (course != null) {
					courses.add(course);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return courses;
	}

	@Override
	public List<ICourse> getSortedCoursesWithStudentsAfterDate(Date pDate, Connection connection) throws Exception {
		String sqlJoin = "select " + COURSE_TABLE + ".* , " + STUDENT_TABLE + ".* from " + COURSE_TABLE + " LEFT JOIN "
				+ COURSE_STUDENT_TABLE + " ON " + ID_COURSE + " = " + FK_KEY_COURSE + " LEFT JOIN " + STUDENT_TABLE
				+ " ON " + FK_KEY_STUDENT + " = " + ID_STUDENT + " WHERE " + START_DATE_COURSE + " > ?";
		java.sql.Date date = DateWorker.convert(pDate);
		Map<Long, ICourse> courseMap = new HashMap<>();
		try (PreparedStatement statement = connection.prepareStatement(sqlJoin)) {
			statement.setDate(1, date);
			ResultSet resultSet = statement.executeQuery(sqlJoin);
			while (resultSet.next()) {
				ICourse course = parseEntity(resultSet);
				IStudent student = parseStudent(resultSet);

				ICourse previouslyReadCourse = courseMap.get(course.getId());
				if (previouslyReadCourse == null) {
					List<IStudent> students = new ArrayList<>();
					if (student != null) {
						students.add(student);
					}
					course.setStudents(students);
					courseMap.put(course.getId(), course);
					continue;
				}
				if (student != null) {
					previouslyReadCourse.getStudents().add(student);
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return new ArrayList<>(courseMap.values());
	}

	@Override
	protected String getCreateSql() {
		return "INSERT INTO " + COURSE_TABLE + "(" + NAME_COURSE + "," + DESCRIPTION_COURSE + "," + START_DATE_COURSE
				+ "," + END_DATE_COURSE + ") values (?,?,?,?)";
	}

	@Override
	protected void setValueToCreateStatement(PreparedStatement preparedStatement, ICourse entity) throws Exception {
		try {
			preparedStatement.setString(1, entity.getName());
			java.sql.Date startDate = DateWorker.convert(entity.getStartDate());
			java.sql.Date endDate = DateWorker.convert(entity.getEndDate());
			preparedStatement.setString(2, entity.getDescription());
			preparedStatement.setDate(3, startDate);
			preparedStatement.setDate(4, endDate);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	protected String getDeleteSql() {
		return "delete from " + COURSE_TABLE + " where " + ID_COURSE + " = ?";
	}

	@Override
	protected void setValueToDeleteStatement(PreparedStatement preparedStatement, Long idEntity) throws Exception {
		try {
			preparedStatement.setLong(1, idEntity);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	protected String getUpdateSql() {
		return "update " + COURSE_TABLE + " set " + NAME_COURSE + " = ? ," + DESCRIPTION_COURSE + " = ? , "
				+ START_DATE_COURSE + " = ? , " + END_DATE_COURSE + " = ? where " + ID_COURSE + " = ?";
	}

	@Override
	protected void setValueToUpdateStatement(PreparedStatement preparedStatement, ICourse entity) throws Exception {
		try {
			preparedStatement.setString(1, entity.getName());
			java.sql.Date startDate = DateWorker.convert(entity.getStartDate());
			java.sql.Date endDate = DateWorker.convert(entity.getEndDate());
			preparedStatement.setString(2, entity.getDescription());
			preparedStatement.setDate(3, startDate);
			preparedStatement.setDate(4, endDate);
			preparedStatement.setLong(5, entity.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}

	}

	@Override
	protected String getReadSql() {
		return "select * from " + COURSE_TABLE;
	}

	@Override
	protected String getFindByIdSql() {
		return getReadSql() + " where " + ID_COURSE + " = ?";
	}

	@Override
	protected void setValueToFindStatement(PreparedStatement preparedStatement, Long idEntity) throws Exception {
		try {
			preparedStatement.setLong(1, idEntity);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	protected ICourse parseEntity(ResultSet resultSet) {
		ICourse course = null;
		try {
			Long idCourse = resultSet.getLong(ID_COURSE);

			String name = resultSet.getString(NAME_COURSE);

			String description = resultSet.getString(DESCRIPTION_COURSE);

			Date startDate = resultSet.getDate(START_DATE_COURSE);

			Date endDate = resultSet.getDate(END_DATE_COURSE);

			course = new Course(idCourse, name, description, startDate, endDate);

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return course;
	}

	private IStudent parseStudent(ResultSet resultSet) {
		IStudent student = null;
		try {
			Long id = resultSet.getLong(ID_STUDENT);
			String name = resultSet.getString(NAME_STUDENT);

			student = new Student(id, name);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return student;
	}

	private ILecture parseLecture(ResultSet resultSet) {
		ILecture lecture = null;
		try {
			Long idLecture = resultSet.getLong(ID_LECTURE);

			String nameLecture = resultSet.getString(NAME_LECTURE);

			lecture = new Lecture(idLecture, nameLecture);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return lecture;
	}

	private ILector parseLector(ResultSet resultSet) {
		ILector lector = null;
		try {
			Long idLector = resultSet.getLong(ID_LECTOR);

			String nameLector = resultSet.getString(NAME_LECTOR);

			lector = new Lector(idLector, nameLector);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return lector;
	}

	@Override
	public List<ILecture> getAllLectures(Connection connection) throws Exception {
		String sql = "select * from " + LECTURE_TABLE;
		List<ILecture> lectures = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {

				ILecture lecture = parseLecture(resultSet);
				if (lecture != null) {
					lectures.add(lecture);
				}

			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return lectures;
	}

}
