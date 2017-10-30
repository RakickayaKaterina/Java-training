package services;

import java.util.Comparator;
import java.util.Date;

import models.Lesson;

public interface IServiceTimeTable {
	public void addLesson(Lesson pLesson);

	public void createLesson(long idLecture, Date dateForLecture);

	public void removeLesson(long pId);

	public void updateLesson(Lesson pLesson);

	public Lesson getLesson(long pId);

	public Lesson[] sort(Comparator<Lesson> pComparator);

	public Lesson[] getListLesson(Date pDate);

	public void saveState();

	public void removeLessonByLectureId(long idLecture);
}
