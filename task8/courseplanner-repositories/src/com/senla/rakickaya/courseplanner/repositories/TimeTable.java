package com.senla.rakickaya.courseplanner.repositories;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.api.repositories.ITimeTable;
import com.senla.rakickaya.courseplanner.repositories.filler.FillerRepositories;
import com.senla.rakickaya.courseplanner.utils.GeneratorId;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class TimeTable implements ITimeTable {

	private static TimeTable timeTable;
	private List<ILesson> mLessons;
	private GeneratorId generatorId = GeneratorId.getInstance();

	private TimeTable() {
		super();
		mLessons = new ArrayList<>();
	}

	private TimeTable(List<ILesson> mLessons) {
		super();
		this.mLessons = mLessons;
	}

	public static TimeTable getInstance() {
		if (timeTable == null) {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			timeTable = new TimeTable(fillerRepositories.getTimeTable());
		}
		return timeTable;
	}

	@Override
	public boolean addLesson(ILesson pLesson) {
		if (pLesson.getId() == 0L) {
			pLesson.setId(generatorId.nextIdLesson());
		} else {
			if (getLesson(pLesson.getId()) != null) {
				return false;
			}
		}
		mLessons.add(pLesson);
		save();
		return true;
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
