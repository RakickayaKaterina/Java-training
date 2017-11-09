package com.senla.rakickaya.controller.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.controller.api.repositories.ICoursesRepository;
import com.senla.rakickaya.controller.api.repositories.ITimeTable;
import com.senla.rakickaya.controller.api.services.ITimeTableService;
import com.senla.rakickaya.model.beans.Course;
import com.senla.rakickaya.model.beans.Lecture;
import com.senla.rakickaya.model.beans.Lesson;
import com.senla.rakickaya.model.exception.EntityNotFoundException;
import com.senla.rakickaya.utils.DateWorker;
import com.senla.rakickaya.utils.ListWorker;
import com.senla.rakickaya.utils.launcher.Launcher;

public class TimeTableService implements ITimeTableService {
	private final ITimeTable mTimeTable;
	private final ICoursesRepository mRepositoryCourses;

	public TimeTableService(ITimeTable mTimeTable, ICoursesRepository mRepositoryCourses) {
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
			mTimeTable.addLesson(
					new Lesson(Launcher.getInstance().getGeneratorId().getIdLesson(), lecture, dateForLecture));

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
	public void removeLessonByLecture(long idLecture) throws EntityNotFoundException {
		List<Lesson> lessons = mTimeTable.getListLessons();
		boolean exist = false;
		for (int i = 0; i < lessons.size(); i++) {
			Lecture lecture = lessons.get(i).getLecture();
			if (lecture.getId() == idLecture) {
				ListWorker.removeItemById(lessons, lessons.get(i).getId());
				exist = true;
			}
		}
		if(!exist){
			throw new EntityNotFoundException();
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
