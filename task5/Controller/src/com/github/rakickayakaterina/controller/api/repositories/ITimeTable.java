package com.github.rakickayakaterina.controller.api.repositories;

import java.util.List;

import com.github.rakickayakaterina.model.beans.Lesson;

public interface ITimeTable {
	public void addLesson(Lesson pLesson);

	public Lesson removeLesson(long pId);

	public void updateLesson(Lesson pLesson);

	public Lesson getLesson(long pId);

	public List<Lesson> getListLessons();

}
