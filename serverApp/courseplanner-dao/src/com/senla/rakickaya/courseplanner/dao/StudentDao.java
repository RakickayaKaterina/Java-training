package com.senla.rakickaya.courseplanner.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.dao.IStudentDao;
import com.senla.rakickaya.courseplanner.beans.Student;

public class StudentDao extends AbstractEntityDao<IStudent, Long> implements IStudentDao {
	
	private static Logger logger = Logger.getLogger(StudentDao.class);

	@Override
	public int getCount(Connection connection) throws Exception {
		String sql = "select count(*) from Student";
		try(Statement statement = connection.createStatement()){
			ResultSet result = statement.executeQuery(sql);
			return result.getInt(1);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
	}

	@Override
	protected String getCreateSql() {
		return " INSERT INTO Student(nameStudent) values (?) ";
	}

	@Override
	protected void setValueToCreateStatement(PreparedStatement preparedStatement, IStudent entity) throws Exception {
		try {
			preparedStatement.setString(1, entity.getNameStudent());
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		
	}

	@Override
	protected String getDeleteSql() {
		return "delete from Student where idStudent = ? ";
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
		return "update Student set nameStudent = ? where idStudent = ? ";
	}

	@Override
	protected void setValueToUpdateStatement(PreparedStatement preparedStatement, IStudent entity) throws Exception {
		try {
			preparedStatement.setString(1, entity.getNameStudent());
			preparedStatement.setLong(2, entity.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		
	}

	@Override
	protected String getReadSql() {
		return "select * from Student";
	}

	@Override
	protected String getFindByIdSql() {
		return "select * frow Student where idStudent = ? ";
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
	protected IStudent parseEntity(ResultSet resultSet) {
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

}
