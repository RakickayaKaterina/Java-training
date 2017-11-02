package com.github.rakickayakaterina.courseplanner.api.repositories;

import com.github.rakickayakaterina.courseplanner.beans.Lesson;

public interface ITimeTable {
	public void addLesson(Lesson pLesson);

	public Lesson removeLesson(long pId);

	public void updateLesson(Lesson pLesson);

	public Lesson getLesson(long pId);

	public Lesson[] getListLesson();

}
