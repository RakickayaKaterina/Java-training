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

public class StudentDao extends AbstractDao<IStudent, Long> implements IStudentDao {
	
	private static final String STUDENT_TABLE = "student";
	private static final String ID_STUDENT = "idStudent";
	private static final String NAME_STUDENT = "nameStudent";
	
	private static Logger logger = Logger.getLogger(StudentDao.class);

	@Override
	public int getCount(Connection connection) throws Exception {
		String sql = "select count(*) from "+STUDENT_TABLE;
		try(Statement statement = connection.createStatement()){
			ResultSet result = statement.executeQuery(sql);
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

	@Override
	protected String getCreateSql() {
		return " INSERT INTO "+STUDENT_TABLE+"("+NAME_STUDENT+") values (?) ";
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
		return "delete from "+STUDENT_TABLE+" where "+ID_STUDENT+" = ? ";
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
		return "update "+STUDENT_TABLE+" set "+NAME_STUDENT+" = ? where "+ID_STUDENT+" = ? ";
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
		return "select * from "+STUDENT_TABLE;
	}

	@Override
	protected String getFindByIdSql() {
		return "select * from "+STUDENT_TABLE+" where "+ID_STUDENT+" = ? ";
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
			Long id = resultSet.getLong(ID_STUDENT);
			String name = resultSet.getString(NAME_STUDENT);
			
			student = new Student(id, name);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return student;
	}

}
