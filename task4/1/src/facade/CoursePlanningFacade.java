package facade;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;

import comparators.course.AlphabetCourseComparator;
import comparators.course.CountStudentsComparator;
import comparators.course.DateCourseComparator;
import comparators.course.LectorNameComparator;
import comparators.lector.AlphabetLectorComparator;
import comparators.lector.CountCoursesComparator;
import comparators.lesson.AlphabetLessonComparator;
import comparators.lesson.DateLessonComparator;
import models.Course;
import repositories.ITimeTable;
import services.IServiceCourses;
import services.IServiceLectors;
import services.IServiceStudent;
import services.IServiceTimeTable;
import services.ServiceLectors;
import utils.ArrayWorker;
import utils.Printer;

public class CoursePlanningFacade {
	private IServiceLectors mServiceLectors;
	private IServiceCourses mServiceCourses;
	private IServiceTimeTable mServiceTimeTable;
	private IServiceStudent mServiceStudent;

	public CoursePlanningFacade(IServiceLectors mServiceLectors, IServiceCourses mServiceCourses,
			IServiceTimeTable mServiceTimeTable, IServiceStudent mServiceStudent) {
		super();
		this.mServiceLectors = mServiceLectors;
		this.mServiceCourses = mServiceCourses;
		this.mServiceTimeTable = mServiceTimeTable;
		this.mServiceStudent = mServiceStudent;
	}

	public void showSortedLectorsByAlphabet() {
		Printer.show(mServiceLectors.sort(new AlphabetLectorComparator()));
	}

	public void showSortedLectorsByCountCourses() {
		Printer.show(mServiceLectors.sort(new CountCoursesComparator()));
	}

	public void showSortedCoursesByStartDate() {
		Printer.show(mServiceCourses.sort(new DateCourseComparator()));
	}

	public void showSortedCoursesByCountStudents() {
		Printer.show(mServiceCourses.sort(new CountStudentsComparator()));
	}

	public void showSortedCoursesByLectorName() {
		Printer.show(mServiceCourses.sort(new LectorNameComparator()));
	}

	public void showSortedCoursesByAlphabet() {
		Printer.show(mServiceCourses.sort(new AlphabetCourseComparator()));
	}

	public void showDetailedDescription(long pIdCourse) {
		Course course = mServiceCourses.getCourse(pIdCourse);
		String detailedDescription = String.format("Detailed description: [description=%s, lector=%s, students=%s]",
				course.getDescription(), course.getLector(), Arrays.toString(course.getStudents()));
		Printer.show(detailedDescription);
	}

	public void showSortedTimeTableByDate() {
		Printer.show(mServiceTimeTable.sort(new DateLessonComparator()));
	}

	public void showSortedTimeTableByAlphabet() {
		Printer.show(mServiceTimeTable.sort(new AlphabetLessonComparator()));
	}

	public void showSortedCoursesByStartDate(Date afterDate) {
		Printer.show(mServiceCourses.getSortedListCoursesAfterDate(afterDate, new DateCourseComparator()));
	}

	public void showSortedCoursesByCountStudents(Date afterDate) {
		Printer.show(mServiceCourses.getSortedListCoursesAfterDate(afterDate, new CountStudentsComparator()));
	}

	public void showSortedCoursesByLectorName(Date afterDate) {
		Printer.show(mServiceCourses.getSortedListCoursesAfterDate(afterDate, new LectorNameComparator()));
	}

	public void showSortedCoursesByAlphabet(Date afterDate) {
		Printer.show(mServiceCourses.getSortedListCoursesAfterDate(afterDate, new AlphabetCourseComparator()));
	}

	public void showSortedCurrentCoursesByStartDate(Date currentDate) {
		Printer.show(mServiceCourses.getSortedListCurrentCourses(currentDate, new DateCourseComparator()));
	}

	public void showSortedCurrentCoursesByCountStudents(Date currentDate) {
		Printer.show(mServiceCourses.getSortedListCurrentCourses(currentDate, new CountStudentsComparator()));
	}

	public void showSortedCurrentCoursesByLectorName(Date currentDate) {
		Printer.show(mServiceCourses.getSortedListCurrentCourses(currentDate, new LectorNameComparator()));
	}

	public void showSortedCurrentCoursesByAlphabet(Date currentDate) {
		Printer.show(mServiceCourses.getSortedListCurrentCourses(currentDate, new AlphabetCourseComparator()));
	}

	public void showTotalCountCourse() {
		int count = ArrayWorker.getLenghtArray(mServiceCourses.getListCourses());
		Printer.show("Total count courses: " + count);
	}

	public void showTotalCountStudents() {
		int count = ArrayWorker.getLenghtArray(mServiceStudent.getListStudent());
		Printer.show("Total count students: " + count);
	}

	public void showTotalCountLectors() {
		int count = ArrayWorker.getLenghtArray(mServiceLectors.getListLectors());
		Printer.show("Total count lectors: " + count);
	}

	public void showListLessonByDate(Date pDate) {
		Printer.show(mServiceTimeTable.getListLesson(pDate));
	}

	public void createCourse() {

	}
}
