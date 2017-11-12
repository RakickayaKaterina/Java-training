package com.senla.rakickaya.courseplanner.api.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;

public interface ITimeTableService {
	public void addLesson(ILesson pLesson);

	public void createLesson(long idLecture, Date dateForLecture);

	public void removeLesson(long pId);

	public void updateLesson(ILesson pLesson);

	public ILesson getLesson(long pId);

	public List<ILesson> getSortedList(Comparator<ILesson> pComparator);

	public List<ILesson> getListLessons(Date pDate);

	public void removeLessonByLecture(long pIdLecture) throws EntityNotFoundException;
}
