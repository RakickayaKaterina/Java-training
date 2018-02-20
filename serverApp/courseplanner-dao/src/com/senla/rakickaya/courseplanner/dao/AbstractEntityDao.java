package com.senla.rakickaya.courseplanner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.dao.EntityDao;

public abstract class AbstractEntityDao<Entity, Id> implements EntityDao<Entity, Id> {

	private static Logger logger = Logger.getLogger(AbstractEntityDao.class);

	protected abstract String getCreateSql();

	protected abstract void setValueToCreateStatement(PreparedStatement preparedStatement, Entity entity) throws Exception;

	protected abstract String getDeleteSql();

	protected abstract void setValueToDeleteStatement(PreparedStatement preparedStatement, Id idEntity) throws Exception;

	protected abstract String getUpdateSql();

	protected abstract void setValueToUpdateStatement(PreparedStatement preparedStatement, Entity entity) throws Exception;

	protected abstract String getReadSql();

	protected abstract String getFindByIdSql();

	protected abstract void setValueToFindStatement(PreparedStatement preparedStatement, Id idEntity) throws Exception;

	protected abstract Entity parseEntity(ResultSet resultSet) throws Exception;

	@Override
	public void create(Entity entity, Connection connection) throws Exception {
		String sqlQuery = getCreateSql();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			setValueToCreateStatement(preparedStatement, entity);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public void delete(Id idEntity, Connection connection) throws Exception {
		String sqlQuery = getDeleteSql();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			setValueToDeleteStatement(preparedStatement, idEntity);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public void update(Entity entity, Connection connection) throws Exception {
		String sqlQuery = getUpdateSql();
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			setValueToUpdateStatement(preparedStatement, entity);
			preparedStatement.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	public List<Entity> getAllEntities(Connection connection) throws Exception {
		String sqlQuery = getReadSql();
		List<Entity> result = new ArrayList<>();
		try (Statement statement = connection.createStatement()) {
			ResultSet response = statement.executeQuery(sqlQuery);
			while (response.next()) {
				Entity entity = parseEntity(response);
				if (entity != null) {
					result.add(entity);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return result;
	}

	@Override
	public Entity findById(Id idEntity, Connection connection) throws Exception {
		String sqlQuery = getReadSql();
		Entity result = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
			setValueToFindStatement(preparedStatement, idEntity);
			ResultSet response = preparedStatement.executeQuery(sqlQuery);
			if (response.next()) {
				result = parseEntity(response);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		return result;
	}

}
