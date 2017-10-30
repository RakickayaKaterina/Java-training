package services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import models.Course;
import models.Lector;
import models.Lecture;
import repositories.IRepositoryCourses;
import utils.ArrayWorker;
import utils.DateWorker;
import utils.Printer;

public class ServiceCourses implements IServiceCourses {
	private IRepositoryCourses mRepositoryCourses;
	private IServiceStudent mServiceStudents;
	private IServiceLectors mServiceLectors;
	private IServiceTimeTable mServiceTimeTable;

	public ServiceCourses(IRepositoryCourses mRepositoryCourses, IServiceStudent mServiceStudents,
			IServiceLectors mServiceLectors, IServiceTimeTable mServiceTimeTable) {
		super();
		this.mRepositoryCourses = mRepositoryCourses;
		this.mServiceStudents = mServiceStudents;
		this.mServiceLectors = mServiceLectors;
		this.mServiceTimeTable = mServiceTimeTable;
	}

	@Override
	public void addCourse(Course pCourse) {
		mRepositoryCourses.addCourse(pCourse);

	}

	@Override
	public void removeCourse(long pId) {
		mRepositoryCourses.removeCourse(pId);
		mServiceStudents.removeCourseFromStudent(pId);
		mServiceLectors.removeCourseFromLector(pId);

	}

	@Override
	public void updateCourse(Course pCourse) {
		mRepositoryCourses.updateCourse(pCourse);

	}

	@Override
	public Course getCourse(long pId) {
		return mRepositoryCourses.getCourse(pId);
	}

	@Override
	public void saveState() {
		// TODO save

	}

	@Override
	public void removeLectorFromCourses(Lector pLector) {
		for (Course course : mRepositoryCourses.getListCourse()) {
			if (course != null && course.getLector().getId() == pLector.getId()) {
				course.setLector(null);
			}

		}

	}

	@Override
	public Course[] sort(Comparator<Course> mComparator) {
		Course[] listCourses = mRepositoryCourses.getListCourse();
		Arrays.sort(listCourses, mComparator);
		return listCourses;
	}

	@Override
	public Course[] getListCourses() {
		return mRepositoryCourses.getListCourse();
	}

	@Override
	public Course[] getSortedListCoursesAfterDate(Date pDate, Comparator<Course> pComparator) {
		Course[] resultList = new Course[10];
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			if (DateWorker.isAfterDate(pDate, courses[i].getStartDate())) {
				ArrayWorker.addToArray(courses[i], resultList);
			}
		}
		Arrays.sort(resultList, pComparator);
		return resultList;
	}

	@Override
	public Course[] getSortedListCurrentCourses(Date pCurrentDate, Comparator<Course> pComparator) {
		Course[] resultList = new Course[10];
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			if (DateWorker.isBetweenDate(pCurrentDate, courses[i].getStartDate(), courses[i].getEndDate())) {
				ArrayWorker.addToArray(courses[i], resultList);
			}
		}
		Arrays.sort(resultList, pComparator);
		return resultList;
	}

	@Override
	public void addStudentToCourse(long pIdStudent, long pId) {
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			if (courses[i].getId() == pId) {
				ArrayWorker.addToArray(mServiceStudents.getStudent(pIdStudent), courses[i].getStudents());
				break;

			}
		}
		mServiceStudents.addCourseToStudent(pId, pIdStudent);

	}

	@Override
	public void addLectorToCourse(long pIdLector, long pId) {
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			if (courses[i].getId() == pId && courses[i].getLector() == null) {
				courses[i].setLector(mServiceLectors.getLector(pIdLector));
			} else if (courses[i].getLector() != null)
				Printer.show("Lector has existed already");
		}
		mServiceLectors.addCourseToLector(pId, pIdLector);

	}

	@Override
	public void addLectureToCourse(Lecture pLecture, long pId) {
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			if (courses[i].getId() == pId) {
				ArrayWorker.addToArray(pLecture, courses[i].getLectures());
				break;
			}
		}

	}

	@Override
	public Course[] getPastCourses(Date startDateSub, Date endDateSub) {
		Course[] resultList = new Course[5];
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			if (DateWorker.isSubInterval(courses[i].getStartDate(), courses[i].getEndDate(), startDateSub,
					endDateSub)) {
				ArrayWorker.addToArray(courses[i], resultList);
			}
		}
		return resultList;
	}

	@Override
	public void removeStudentFromCourse(long pIdStudent) {
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			ArrayWorker.removeFromArray(pIdStudent, courses[i].getStudents());
		}

	}

	@Override
	public void removeLectureFromCourse(long idLecture) {
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			ArrayWorker.removeFromArray(idLecture, courses[i].getLectures());
		}
		mServiceTimeTable.removeLessonByLectureId(idLecture);

	}

	@Override
	public Lecture getLectureCourse(long id) {
		for (Course c : mRepositoryCourses.getListCourse()) {
			if (c != null)
				for (Lecture l : c.getLectures()) {
					if (l != null && l.getId() == id) {
						return l;
					}
				}

		}
		return null;
	}
}