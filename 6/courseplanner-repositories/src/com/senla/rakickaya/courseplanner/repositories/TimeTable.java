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

	public TimeTable() {
		super();
		mLessons = new ArrayList<>();
	}

	public static TimeTable getInstance() {
		if (timeTable == null) {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			timeTable = new TimeTable(fillerRepositories.getTimeTable());
		}
		return timeTable;
	}

	public TimeTable(List<ILesson> mLessons) {
		super();
		this.mLessons = mLessons;
	}

	@Override
	public boolean addLesson(ILesson pLesson) {
		if (getLesson(pLesson.getId()) == null) {
			mLessons.add(pLesson);
			save();
			return true;
		}
		return false;

	}

	@Override
	public ILesson removeLesson(long pId) {
		ILesson lesson = ListWorker.removeItemById(mLessons, pId);
		if (lesson != null) {
			save();
		}
		return lesson;
	}

	@Override
	public void updateLesson(ILesson pLesson) {
		ListWorker.updateItem(mLessons, pLesson);
		save();

	}

	@Override
	public ILesson getLesson(long pId) {
		return (ILesson) ListWorker.getItemById(mLessons, pId);
	}

	@Override
	public List<ILesson> getLessons() {
		return mLessons;
	}

	@Override
	public void save() {
		FillerRepositories fillerRepositories = FillerRepositories.getInstance();
		fillerRepositories.writeLessonToFile(mLessons);

	}

}
