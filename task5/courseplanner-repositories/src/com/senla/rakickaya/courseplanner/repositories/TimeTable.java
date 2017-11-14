package com.senla.rakickaya.courseplanner.repositories;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.api.repositories.ITimeTable;
import com.senla.rakickaya.courseplanner.repositories.filler.FillerRepositories;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class TimeTable implements ITimeTable {
	private List<ILesson> mLessons;
	private static TimeTable timeTable;

	public static TimeTable getInstance() {
		if (timeTable == null) {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			timeTable = new TimeTable(fillerRepositories.getTimeTable());
		}
		return timeTable;
	}

	public TimeTable() {
		super();
		mLessons = new ArrayList<>();
	}

	public TimeTable(List<ILesson> mLessons) {
		super();
		this.mLessons = mLessons;
	}

	@Override
	public void addLesson(ILesson pLesson) {
		mLessons.add(pLesson);

	}

	@Override
	public ILesson removeLesson(long pId) {
		return ListWorker.removeItemById(mLessons, pId);
	}

	@Override
	public void updateLesson(ILesson pLesson) {
		ListWorker.updateItem(mLessons, pLesson);
		;

	}

	@Override
	public ILesson getLesson(long pId) {
		return (ILesson) ListWorker.getItemById(mLessons, pId);
	}

	@Override
	public List<ILesson> getListLessons() {
		return mLessons;
	}

	@Override
	public void save() {
		FillerRepositories fillerRepositories = FillerRepositories.getInstance();
		fillerRepositories.writeLessonToFile(mLessons);

	}

}
