package com.senla.rakickaya.courseplanner.api.repositories;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILesson;

public interface ITimeTable {
	boolean addLesson(ILesson pLesson);

	ILesson removeLesson(long pId);

	void updateLesson(ILesson pLesson);

	ILesson getLesson(long pId);

	List<ILesson> getLessons();

	void save();
}
