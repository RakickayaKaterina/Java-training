package com.github.rakickayakaterina.controller.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.github.rakickayakaterina.controller.api.repositories.IRepositoryCourses;
import com.github.rakickayakaterina.controller.api.repositories.ITimeTable;
import com.github.rakickayakaterina.controller.api.services.IServiceTimeTable;
import com.github.rakickayakaterina.model.beans.Course;
import com.github.rakickayakaterina.model.beans.Lecture;
import com.github.rakickayakaterina.model.beans.Lesson;
import com.github.rakickayakaterina.utils.DateWorker;
import com.github.rakickayakaterina.utils.GeneratorId;
import com.github.rakickayakaterina.utils.ListWorker;

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
	public List<Lesson> getListLessons(Date pDate) {
		List<Lesson> resultList = new ArrayList<>();
		List<Lesson> allLessons = mTimeTable.getListLessons();
		for (int i = 0; i < allLessons.size(); i++) {
			if (DateWorker.isEqualsDate(pDate, allLessons.get(i).getDate()))
				resultList.add(allLessons.get(i));
		}
		return resultList;
	}

	@Override
	public void removeLessonByLecture(long idLecture) {
		List<Lesson> lessons = mTimeTable.getListLessons();
		for (int i = 0; i < lessons.size(); i++) {
			Lecture lecture = lessons.get(i).getLecture();
			if (lecture.getId() == idLecture)
				ListWorker.removeItemById(lessons, lessons.get(i).getId());
		}
	}

	private Lecture getLectureCourse(long id) {
		for (Course course : mRepositoryCourses.getListCourses()) {
			for (Lecture lecture : course.getLectures()) {
				if (lecture.getId() == id) {
					return lecture;
				}
			}
		}
		return null;

	}

	@Override
	public List<Lesson> getSortedList(Comparator<Lesson> pComparator) {
		List<Lesson> listLesson = mTimeTable.getListLessons();
		listLesson.sort(pComparator);
		return listLesson;

	}

}
