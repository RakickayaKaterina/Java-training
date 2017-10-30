package services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import models.Lecture;
import models.Lesson;
import repositories.ITimeTable;
import utils.ArrayWorker;
import utils.DateWorker;

public class ServiceTimeTable implements IServiceTimeTable {
	private ITimeTable mTimeTable;
	private IServiceCourses mServiceCourses;

	public ServiceTimeTable(ITimeTable mTimeTable) {
		super();
		this.mTimeTable = mTimeTable;
	}
	
	

	public void setServiceCourses(IServiceCourses mServiceCourses) {
		this.mServiceCourses = mServiceCourses;
	}



	@Override
	public void addLesson(Lesson pLesson) {
		mTimeTable.addLesson(pLesson);

	}

	@Override
	public void removeLesson(long pId) {
		mTimeTable.removeLesson(pId);

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
		Lesson[] pListLesson = Arrays.copyOf(listLesson, ArrayWorker.getLenghtArray(listLesson));
		Arrays.sort(pListLesson, pComparator);
		return pListLesson;

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
			if (DateWorker.isEqualsDate(pDate, allLessons[i].getDate()))
				ArrayWorker.addToArray(allLessons[i], resultList);
		}
		return resultList;
	}

	@Override
	public void removeLessonByLectureId(long idLecture) {
		Lesson[] lessons = mTimeTable.getListLesson();
		for (int i = 0; i < ArrayWorker.getLenghtArray(lessons); i++) {
			if (lessons[i].getLecture().getId() == idLecture)
				ArrayWorker.removeFromArray(lessons[i].getId(), lessons);
		}

	}

	@Override
	public void createLesson(long idLecture, Date dateForLecture) {
		Lecture lecture = mServiceCourses.getLectureCourse(idLecture);
		if (lecture != null)
			mTimeTable.addLesson(new Lesson(lecture, dateForLecture));
	}

}
