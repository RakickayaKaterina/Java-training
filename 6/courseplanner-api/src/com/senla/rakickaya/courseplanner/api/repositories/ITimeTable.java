package com.senla.rakickaya.courseplanner.api.repositories;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILesson;

public interface ITimeTable {
	public void addLesson(ILesson pLesson);

	public ILesson removeLesson(long pId);

	public void updateLesson(ILesson pLesson);

	public ILesson getLesson(long pId);

	public List<ILesson> getListLessons();

	public void save();
}
