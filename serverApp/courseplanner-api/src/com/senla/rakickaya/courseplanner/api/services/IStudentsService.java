package com.senla.rakickaya.courseplanner.api.services;

import java.sql.SQLException;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;

public interface IStudentsService {
	void addStudent(IStudent pStudent);

	void removeStudent(long pId) throws EntityNotFoundException;

	void updateStudent(IStudent pStudent);

	IStudent getStudent(long pId);

	List<IStudent> getStudents();

	int getTotalCountStudents();

	void exportCSV(String path);

	void importCSV(String path) throws SQLException;
}
