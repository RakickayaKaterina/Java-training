package com.senla.rakickaya.courseplanner.api.dao;

import java.sql.Connection;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.dao.enums.SortColumnLector;

public interface ILectorDao extends GenericDao<ILector, Long>{

	List<ILector> getSortedLectors(SortColumnLector column, Connection connection) throws Exception;

	List<ILector> getLectorsWithCourses(Connection connection) throws Exception;

	int getCount(Connection connection) throws Exception;

	int getCountCoursesByLector(long idLector, Connection connection) throws Exception;

}
