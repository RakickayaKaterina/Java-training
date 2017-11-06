package com.github.rakickayakaterina.controller.repositories;

import java.util.ArrayList;
import java.util.List;

import com.github.rakickayakaterina.controller.api.repositories.ITimeTable;
import com.github.rakickayakaterina.model.beans.Lesson;
import com.github.rakickayakaterina.utils.ListWorker;

public class TimeTable implements ITimeTable {
	private List<Lesson> mLessons;

	public TimeTable() {
		super();
		mLessons = new ArrayList<>();
	}

	public TimeTable(List<Lesson> mLessons) {
		super();
		this.mLessons = mLessons;
	}

	@Override
	public void addLesson(Lesson pLesson) {
		mLessons.add(pLesson);

	}

	@Override
	public Lesson removeLesson(long pId) {
		return (Lesson) ListWorker.removeItemById(mLessons, pId);
	}

	@Override
	public void updateLesson(Lesson pLesson) {
		ListWorker.updateItem(mLessons, pLesson);;

	}

	@Override
	public Lesson getLesson(long pId) {
		return (Lesson) ListWorker.getItemById(mLessons, pId);
	}

	@Override
	public List<Lesson> getListLessons() {
		return mLessons;
	}

}
