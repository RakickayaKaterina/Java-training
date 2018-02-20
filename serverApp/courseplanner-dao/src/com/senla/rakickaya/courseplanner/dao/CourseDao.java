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

public class CourseDao extends AbstractEntityDao<ICourse, Long> implements ICourseDao {

	private static Logger logger = Logger.getLogger(CourseDao.class);

	@Override
	public List<ICourse> getCoursesWithStudents(Connection connection) throws Exception {
		String sqlJoin = "select course.* , student.* from course LEFT JOIN `Course_Student` ON idCourse = Course_idCourse"
				+ " LEFT JOIN student ON student_idStudent = idStudent";
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
		String insertQuery = "INSERT INTO course_student(Course_idCourse,Student_idStudent) values (?,?)";
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
	public ILecture findLectureById(Long idLecture, Connection connection) throws Exception{
		
		String insertQuery = "select * from lecture where idLecture = " + idLecture;
		ILecture lecture = null;
		try (Statement statement = connection.createStatement()) {
			
			ResultSet resultSet = statement.executeQuery(insertQuery);
			if(resultSet.next()){
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
		String insertQuery = "delete from course_student where Course_idCourse = ? AND Student_idStudent = ? ";
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
		String insertQuery = "update course set  Lector_idLector = ? where idCourse = ?";
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
		String insertQuery = "update course set  Lector_idLector = NULL where Lector_idLector = ? AND idCourse = ?";
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
		String sqlJoin = "select * from course LEFT JOIN lecture ON Course_idCourse = idCourse";
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
		String sql = "select * from course LEFT JOIN lector ON Lector_idLector = idLector order by nameLector ";
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
		String sqlInsert = "insert into Lecture(name, Course_idCourse) values (?, ?)";
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
		String deleteLecture = "delete from Lecture where idLecture = " + pIdLecture;
		try (Statement statement = connection.createStatement()) {
			statement.executeQuery(deleteLecture);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public List<ICourse> getSortedCourses(SortColumnCourse column, Connection connection) throws Exception {
		String sql = "select * from course order by " + column.getOrderColumn();
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
		String sql = "select * from course where start_date > ? order by " + column.getOrderColumn();
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
		String sql = "select * from course where ? between start_date and end_date order by " + column.getOrderColumn();
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
		String query = "select count(*) from course";
		try (Statement statement = connection.createStatement()) {
			ResultSet result = statement.executeQuery(query);
			return result.getInt(1);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public List<ICourse> getPastCourses(Date startDateSub, Date endDateSub, Connection connection) throws Exception {
		String query = "select * from course where start_date < ? and end_date > ?";
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
		String sqlJoin = "select course.* , student.* from course LEFT JOIN `Course_Student` ON idCourse = Course_idCourse"
				+ " LEFT JOIN student ON student_idStudent = idStudent WHERE start_date > ?";
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
		return "INSERT INTO course(name,description,start_date,end_date) values (?,?,?,?)";
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
		return "delete from course where idCourse = ?";
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
		return "update course set name = ? ,description = ? , start_date = ? , end_date = ? where idCourse = ?";
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
		return "select * from course ";
	}

	@Override
	protected String getFindByIdSql() {
		return getReadSql() + " where idCourse = ?";
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
			Long idCourse = resultSet.getLong("idCourse");

			String name = resultSet.getString("name");

			String description = resultSet.getString("description");

			Date startDate = resultSet.getDate("start_date");

			Date endDate = resultSet.getDate("end_date");

			course = new Course(idCourse, name, description, startDate, endDate);

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return course;
	}

	private IStudent parseStudent(ResultSet resultSet) {
		IStudent student = null;
		try {
			Long id = resultSet.getLong("idStudent");
			String name = resultSet.getString("nameStudent");

			student = new Student(id, name);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return student;
	}

	private ILecture parseLecture(ResultSet resultSet) {
		ILecture lecture = null;
		try {
			Long idLecture = resultSet.getLong("idLecture");

			String nameLecture = resultSet.getString("name");

			lecture = new Lecture(idLecture, nameLecture);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return lecture;
	}

	private ILector parseLector(ResultSet resultSet) {
		ILector lector = null;
		try {
			Long idLecture = resultSet.getLong("idLecture");

			String nameLector = resultSet.getString("nameLector");

			lector = new Lector(idLecture, nameLector);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return lector;
	}

	@Override
	public List<ILecture> getAllLectures(Connection connection) throws Exception {
		String sql = "select * from Lecture";
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
