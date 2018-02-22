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
import com.senla.rakickaya.courseplanner.api.dao.ILectorDao;
import com.senla.rakickaya.courseplanner.api.dao.enums.SortColumnLector;
import com.senla.rakickaya.courseplanner.beans.Course;
import com.senla.rakickaya.courseplanner.beans.Lector;

public class LectorDao extends AbstractDao<ILector, Long> implements ILectorDao {
	private static final String COURSE_TABLE = "course";
	private static final String LECTOR_TABLE = "lector";
	private static final String ID_COURSE = "idCourse";
	private static final String ID_LECTOR = "idLector";
	private static final String NAME_LECTOR = "nameLector";
	private static final String FK_KEY_LECTOR = "Lector_idLector";
	private static final String NAME_COURSE = "name";
	private static final String DESCRIPTION_COURSE = "description";
	private static final String START_DATE_COURSE = "start_date";
	private static final String END_DATE_COURSE = "end_date";

	private static Logger logger = Logger.getLogger(LectorDao.class);

	@Override
	public List<ILector> getSortedLectors(SortColumnLector column, Connection connection) throws Exception {
		String selectSql = getReadSql() + " order by " + column.getOrderColumn();
		List<ILector> lectors = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			ResultSet set = statement.executeQuery(selectSql);
			while (set.next()) {
				ILector lector = parseEntity(set);
				if (lector != null) {
					lectors.add(lector);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return lectors;
	}

	@Override
	public List<ILector> getLectorsWithCourses(Connection connection) throws Exception {
		String sqlJoin = "select * from " + LECTOR_TABLE + " LEFT JOIN " + COURSE_TABLE + " ON " + FK_KEY_LECTOR + " = "
				+ ID_LECTOR;
		Map<Long, ILector> lectorsMap = new HashMap<>();
		try (Statement statement = connection.createStatement()) {

			ResultSet resultSet = statement.executeQuery(sqlJoin);
			while (resultSet.next()) {
				ILector lector = parseEntity(resultSet);
				ICourse course = parseCourse(resultSet);

				ILector previouslyReadLector = lectorsMap.get(lector.getId());
				if (previouslyReadLector == null) {
					List<ICourse> courses = new ArrayList<>();
					if (course != null) {
						courses.add(course);
					}
					lector.setCourses(courses);
					lectorsMap.put(lector.getId(), lector);
					continue;
				}
				if (course != null) {
					previouslyReadLector.getCourses().add(course);
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return new ArrayList<>(lectorsMap.values());
	}

	@Override
	public int getCount(Connection connection) throws Exception {
		String sql = "select count(*) from " + LECTOR_TABLE;
		return getCountFromQuery(sql, connection);
	}

	@Override
	protected String getCreateSql() {
		return "insert into " + LECTOR_TABLE + "(" + NAME_LECTOR + ") values (?)";
	}

	@Override
	protected void setValueToCreateStatement(PreparedStatement preparedStatement, ILector entity) throws Exception {
		try {
			preparedStatement.setString(1, entity.getName());
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	protected String getDeleteSql() {
		return "delete from " + LECTOR_TABLE + " where " + ID_LECTOR + " = ?";
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
		return "update " + LECTOR_TABLE + " set " + NAME_LECTOR + " = ? where " + ID_LECTOR + " = ?";
	}

	@Override
	protected void setValueToUpdateStatement(PreparedStatement preparedStatement, ILector entity) throws Exception {
		try {
			preparedStatement.setString(1, entity.getName());
			preparedStatement.setLong(2, entity.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	protected String getReadSql() {
		return "select * from " + LECTOR_TABLE;
	}

	@Override
	protected String getFindByIdSql() {
		return getReadSql() + " where " + ID_LECTOR + " = ? ";
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
	protected ILector parseEntity(ResultSet resultSet) {
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

	protected ICourse parseCourse(ResultSet resultSet) {
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

	@Override
	public int getCountCoursesByLector(long idLector, Connection connection) throws Exception {
		String query = "select count(*) from " + LECTOR_TABLE + " INNER JOIN " + COURSE_TABLE + " ON " + FK_KEY_LECTOR
				+ " = " + ID_LECTOR + " WHERE " + ID_LECTOR + " = " + idLector;
		return getCountFromQuery(query, connection);
	}

	private int getCountFromQuery(String query, Connection connection) throws Exception {
		try (Statement statement = connection.createStatement()) {
			
			ResultSet result = statement.executeQuery(query);
			int count = 0;
			if (result.next()) {
				count = result.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}
}
