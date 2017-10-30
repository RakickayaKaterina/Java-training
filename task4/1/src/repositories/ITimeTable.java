package repositories;

import models.Lesson;

public interface ITimeTable {
	public void addLesson(Lesson pLesson);

	public Lesson removeLesson(long pId);

	public void updateLesson(Lesson pLesson);

	public Lesson getLesson(long pId);

	public Lesson[] getListLesson();

	public void saveState();
}
