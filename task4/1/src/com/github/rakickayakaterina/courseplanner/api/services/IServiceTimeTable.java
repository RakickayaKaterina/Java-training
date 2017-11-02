package com.github.rakickayakaterina.courseplanner.api.services;

import java.util.Comparator;
import java.util.Date;

import com.github.rakickayakaterina.courseplanner.beans.Lesson;

public interface IServiceTimeTable {
	public void addLesson(Lesson pLesson);

	public void createLesson(long idLecture, Date dateForLecture);

	public void removeLesson(long pId);

	public void updateLesson(Lesson pLesson);

	public Lesson getLesson(long pId);

	public Lesson[] getSortedList(Comparator<Lesson> pComparator);

	public Lesson[] getListLesson(Date pDate);

	public void removeLessonByLecture(long idLecture);
}
