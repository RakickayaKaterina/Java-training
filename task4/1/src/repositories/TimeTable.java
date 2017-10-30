package repositories;

import models.Lesson;
import utils.ArrayWorker;

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
		ArrayWorker.addToArray(pLesson, mLessons);

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

	@Override
	public void saveState() {
		// TODO save

	}

}
