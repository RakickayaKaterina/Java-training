package com.senla.rakickaya.controller.api.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.model.beans.Lesson;


public interface ITimeTableService {
	public void addLesson(Lesson pLesson);

	public void createLesson(long idLecture, Date dateForLecture);

	public void removeLesson(long pId);

	public void updateLesson(Lesson pLesson);

	public Lesson getLesson(long pId);

	public List<Lesson> getSortedList(Comparator<Lesson> pComparator);

	public List<Lesson> getListLessons(Date pDate);

	public void removeLessonByLecture(long pIdLecture);
}
