package com.senla.rakickaya.courseplanner.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.api.repositories.ICoursesRepository;
import com.senla.rakickaya.courseplanner.api.repositories.ITimeTable;
import com.senla.rakickaya.courseplanner.api.services.ITimeTableService;
import com.senla.rakickaya.courseplanner.beans.Lesson;
import com.senla.rakickaya.courseplanner.configuration.Config;
import com.senla.rakickaya.courseplanner.csv.CsvConverter;
import com.senla.rakickaya.courseplanner.csv.CsvObject;
import com.senla.rakickaya.courseplanner.csv.CsvWorker;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;
import com.senla.rakickaya.courseplanner.repositories.CoursesRepository;
import com.senla.rakickaya.courseplanner.repositories.TimeTable;
import com.senla.rakickaya.courseplanner.utils.DateWorker;
import com.senla.rakickaya.courseplanner.utils.GeneratorId;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class TimeTableService implements ITimeTableService {
	private final ITimeTable mTimeTable;
	private final ICoursesRepository mRepositoryCourses;

	public TimeTableService() {
		super();
		this.mTimeTable = TimeTable.getInstance();
		this.mRepositoryCourses = CoursesRepository.getInstance();
	}

	@Override
	public void addLesson(ILesson pLesson) {
		mTimeTable.addLesson(pLesson);

	}

	@Override
	public void createLesson(long idLecture, Date dateForLecture, int countStudent) throws Exception {
		ILecture lecture = getLectureCourse(idLecture);
		List<ILesson> timeTable = getLessons(dateForLecture);
		int amount = 0;
		for (ILesson lesson : timeTable) {
			amount += lesson.getCountStudent();
		}
		if (lecture != null && amount + countStudent <= Config.getInstance().getAmountStudents()) {
			mTimeTable.addLesson(
					new Lesson(GeneratorId.getInstance().nextIdLesson(), lecture, dateForLecture, countStudent));
		} else {
			throw new Exception("Limit Students");
		}

	}

	@Override
	public void removeLesson(long pId) {
		mTimeTable.removeLesson(pId);

	}

	@Override
	public void updateLesson(ILesson pLesson) {
		mTimeTable.updateLesson(pLesson);

	}

	@Override
	public ILesson getLesson(long pId) {
		return mTimeTable.getLesson(pId);
	}

	@Override
	public List<ILesson> getLessons(Date pDate) {
		List<ILesson> resultList = new ArrayList<>();
		List<ILesson> allLessons = mTimeTable.getLessons();
		for (int i = 0; i < allLessons.size(); i++) {
			if (DateWorker.isEqualsDate(pDate, allLessons.get(i).getDate()))
				resultList.add(allLessons.get(i));
		}
		return resultList;
	}

	@Override
	public void removeLessonByLecture(long idLecture) throws EntityNotFoundException {
		List<ILesson> lessons = mTimeTable.getLessons();
		boolean exist = false;
		for (int i = 0; i < lessons.size(); i++) {
			ILecture lecture = lessons.get(i).getLecture();
			if (lecture.getId() == idLecture) {
				ListWorker.removeItemById(lessons, lessons.get(i).getId());
				exist = true;
			}
		}
		if (!exist) {
			throw new EntityNotFoundException();
		}
	}

	private ILecture getLectureCourse(long id) {
		for (ICourse course : mRepositoryCourses.getCourses()) {
			for (ILecture lecture : course.getLectures()) {
				if (lecture.getId() == id) {
					return lecture;
				}
			}
		}
		return null;

	}
	@Override
	public void exportCSV(String path) {
		CsvWorker worker = new CsvWorker(path);
		List<ILesson> lessons = mTimeTable.getLessons();
		List<CsvObject> objects = new ArrayList<CsvObject>();
		for (ILesson lesson : lessons) {
			objects.add(CsvObject.valueOf(lesson));
		}
		worker.writeCSV(objects);
	}

	@Override
	public void importCSV(String path) {
		CsvWorker worker = new CsvWorker(path);
		List<CsvObject> objects = worker.readCSV();
		List<ILesson> lessons = new ArrayList<>();
		for (CsvObject obj : objects) {
			lessons.add(CsvConverter.parseLesson(obj, mRepositoryCourses.getAllLectures()));
		}
		for (ILesson lesson : lessons) {
			if (!mTimeTable.addLesson(lesson)) {
				mTimeTable.updateLesson(lesson);
			}
			else{
				GeneratorId generatorId = GeneratorId.getInstance();
				long id = generatorId.getIdLesson();
				if(lesson.getId() > id){
					generatorId.setIdLesson(id);
				}
			}
		}
	}
	@Override
	public List<ILesson> getSortedList(Comparator<ILesson> pComparator) {
		List<ILesson> listLesson = mTimeTable.getLessons();
		listLesson.sort(pComparator);
		return listLesson;

	}
}
