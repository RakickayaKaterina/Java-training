package com.senla.rakickaya.courseplanner.api.services;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.dao.enums.SortColumnLector;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;

public interface ILectorsService {
	void addLector(ILector pLector);

	void removeLector(long pId) throws EntityNotFoundException;

	void updateLector(ILector pLector);

	ILector getLector(long pId);

	List<ILector> getLectors();

	int getCountCoursesByLector(long pIdLector);

	Map<ILector, Integer> getLectorsInformation();

	int getTotalCountLectors();

	void exportCSV(String path) throws Exception;

	void importCSV(String path) throws SQLException;

	List<ILector> getSortedList(SortColumnLector column);

	List<ILector> getLectorsWithCourses();

}
