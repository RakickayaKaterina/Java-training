package services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import models.Lesson;
import repositories.ITimeTable;
import utils.ArrayWorker;
import utils.DateWorker;

public class ServiceTimeTable implements IServiceTimeTable {
	private ITimeTable mTimeTable;

	public ServiceTimeTable(ITimeTable mTimeTable) {
		super();
		this.mTimeTable = mTimeTable;
	}

	@Override
	public void addLesson(Lesson pLesson) {
		mTimeTable.addLesson(pLesson);

	}

	@Override
	public void removeLesson(long pId) {
		// TODO remove

	}

	@Override
	public void updateLesson(Lesson pLesson) {
		mTimeTable.updateLesson(pLesson);

	}

	@Override
	public Lesson getLesson(long pId) {
		return mTimeTable.getLesson(pId);
	}

	@Override
	public Lesson[] sort(Comparator<Lesson> pComparator) {
		Lesson[] listLesson = mTimeTable.getListLesson();
		Arrays.sort(listLesson, pComparator);
		return listLesson;

	}

	@Override
	public void saveState() {
		// TODO save

	}

	@Override
	public Lesson[] getListLesson(Date pDate) {
		Lesson[] resultList = new Lesson[7];
		Lesson[] allLessons = mTimeTable.getListLesson();
		for (int i = 0; i < ArrayWorker.getLenghtArray(allLessons); i++) {
			if(DateWorker.isEqualsDate(pDate, allLessons[i].getDate()))
				ArrayWorker.addToArray(allLessons[i], resultList);
		}
		return resultList;
	}

}
