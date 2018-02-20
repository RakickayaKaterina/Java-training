package com.senla.rakickaya.courseplanner.api.dao;

import java.sql.Connection;

import com.senla.rakickaya.courseplanner.api.beans.IStudent;

public interface IStudentDao extends EntityDao<IStudent, Long>{

	int getCount(Connection connection) throws Exception;

}
