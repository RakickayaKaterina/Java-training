package com.senla.rakickaya.courseplanner.api.services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.api.dao.enums.SortColumnLesson;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;

public interface ITimeTableService {
	void addLesson(ILesson pLesson);

	void createLesson(long idLecture, Date dateForLecture, int countStudent) throws Exception;

	void removeLesson(long pId);

	void updateLesson(ILesson pLesson);

	ILesson getLesson(long pId);


	void removeLessonByLecture(long pIdLecture) throws EntityNotFoundException;

	void exportCSV(String path);

	void importCSV(String path) throws SQLException;

	List<ILesson> getSortedList(SortColumnLesson column);

	List<ILesson> getLessons();

	List<ILesson> getLessons(Date date);
}
