package com.github.rakickayakaterina.courseplanner.repositories;

import com.github.rakickayakaterina.courseplanner.api.repositories.ITimeTable;
import com.github.rakickayakaterina.courseplanner.beans.Lesson;
import com.github.rakickayakaterina.courseplanner.utils.ArrayWorker;

public class TimeTable implements ITimeTable {
	private Lesson[] mLessons;

	public TimeTable(int countLessons) {
		super();
		mLessons = new Lesson[countLessons];
	}

	public TimeTable(Lesson[] mLessons) {
		super();
		this.mLessons = mLessons;
	}

	@Override
	public void addLesson(Lesson pLesson) {
		mLessons = (Lesson[]) ArrayWorker.addToArray(pLesson, mLessons);

	}

	@Override
	public Lesson removeLesson(long pId) {
		return (Lesson) ArrayWorker.removeFromArray(pId, mLessons);
	}

	@Override
	public void updateLesson(Lesson pLesson) {
		ArrayWorker.updatePosition(pLesson, mLessons);

	}

	@Override
	public Lesson getLesson(long pId) {
		int position = ArrayWorker.getPositionById(pId, mLessons);
		if (position >= 0) {
			return mLessons[position];
		}
		return null;
	}

	@Override
	public Lesson[] getListLesson() {
		return mLessons;
	}

}
