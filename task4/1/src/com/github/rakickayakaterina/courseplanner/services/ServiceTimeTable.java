package com.github.rakickayakaterina.courseplanner.services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryCourses;
import com.github.rakickayakaterina.courseplanner.api.repositories.ITimeTable;
import com.github.rakickayakaterina.courseplanner.api.services.IServiceTimeTable;
import com.github.rakickayakaterina.courseplanner.beans.Course;
import com.github.rakickayakaterina.courseplanner.beans.Lecture;
import com.github.rakickayakaterina.courseplanner.beans.Lesson;
import com.github.rakickayakaterina.courseplanner.utils.ArrayWorker;
import com.github.rakickayakaterina.courseplanner.utils.DateWorker;
import com.github.rakickayakaterina.courseplanner.utils.GeneratorId;

public class ServiceTimeTable implements IServiceTimeTable {
	private final ITimeTable mTimeTable;
	private final IRepositoryCourses mRepositoryCourses;

	public ServiceTimeTable(ITimeTable mTimeTable, IRepositoryCourses mRepositoryCourses) {
		super();
		this.mTimeTable = mTimeTable;
		this.mRepositoryCourses = mRepositoryCourses;
	}

	@Override
	public void addLesson(Lesson pLesson) {
		mTimeTable.addLesson(pLesson);

	}

	@Override
	public void createLesson(long idLecture, Date dateForLecture) {
		Lecture lecture = getLectureCourse(idLecture);
		if (lecture != null)
			mTimeTable.addLesson(new Lesson(GeneratorId.getIdLesson(), lecture, dateForLecture));

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
	public Lesson[] getListLesson(Date pDate) {
		Lesson[] resultList = new Lesson[7];
		Lesson[] allLessons = mTimeTable.getListLesson();
		for (int i = 0; i < ArrayWorker.getLenghtArray(allLessons); i++) {
			if (DateWorker.isEqualsDate(pDate, allLessons[i].getDate()))
				resultList = (Lesson[]) ArrayWorker.addToArray(allLessons[i], resultList);
		}
		return resultList;
	}

	@Override
	public void removeLessonByLecture(long idLecture) {
		Lesson[] lessons = mTimeTable.getListLesson();
		for (int i = 0; i < ArrayWorker.getLenghtArray(lessons); i++) {
			if (lessons[i] != null) {
				Lecture lecture = lessons[i].getLecture();
				if (lecture != null && lecture.getId() == idLecture)
					ArrayWorker.removeFromArray(lessons[i].getId(), lessons);
			}
		}

	}

	private Lecture getLectureCourse(long id) {
		for (Course course : mRepositoryCourses.getListCourse()) {
			if (course != null)
				for (Lecture lecture : course.getLectures()) {
					if (lecture != null && lecture.getId() == id) {
						return lecture;
					}
				}

		}
		return null;

	}

	@Override
	public Lesson[] getSortedList(Comparator<Lesson> pComparator) {
		Lesson[] listLesson = mTimeTable.getListLesson();
		Arrays.sort(listLesson, pComparator);
		return listLesson;

	}

}
