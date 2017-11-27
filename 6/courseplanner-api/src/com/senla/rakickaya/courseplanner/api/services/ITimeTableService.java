package com.senla.rakickaya.courseplanner.api.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;

public interface ITimeTableService {
	void addLesson(ILesson pLesson);

	void createLesson(long idLecture, Date dateForLecture, int countStudent) throws Exception;

	void removeLesson(long pId);

	void updateLesson(ILesson pLesson);

	ILesson getLesson(long pId);

	List<ILesson> getSortedList(Comparator<ILesson> pComparator);

	List<ILesson> getLessons(Date pDate);

	void removeLessonByLecture(long pIdLecture) throws EntityNotFoundException;

	void exportCSV(String path);

	void importCSV(String path);
}
