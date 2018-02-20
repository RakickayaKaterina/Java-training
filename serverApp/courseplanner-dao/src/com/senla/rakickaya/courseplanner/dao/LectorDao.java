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

public class LectorDao extends AbstractEntityDao<ILector, Long> implements ILectorDao {

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
		String sqlJoin = "select * from lector LEFT JOIN course ON Lector_idLector = idLector";
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
		String sql = "select count(*) from Lector";
		return getCountFromQuery(sql, connection);
	}

	@Override
	protected String getCreateSql() {
		return "insert into Lector(nameLector) values (?)";
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
		return "delete from Lector where idLector = ?";
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
		return "update Lector set name = ? where idLector = ?";
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
		return "select * from Lector";
	}

	@Override
	protected String getFindByIdSql() {
		return getReadSql() + " where idLector = ? ";
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
			Long idLecture = resultSet.getLong("idLecture");

			String nameLector = resultSet.getString("nameLector");

			lector = new Lector(idLecture, nameLector);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return lector;
	}

	protected ICourse parseCourse(ResultSet resultSet) {
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

	@Override
	public int getCountCoursesByLector(long idLector, Connection connection) throws Exception {
		String query = "select count(*) from lector INNER JOIN course ON Lector_idLector = idLector WHERE idLector = "
				+ idLector;
		return getCountFromQuery(query, connection);
	}

	private int getCountFromQuery(String query, Connection connection) throws Exception {
		try (Statement statement = connection.createStatement()) {
			ResultSet result = statement.executeQuery(query);
			return result.getInt(1);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}
}
