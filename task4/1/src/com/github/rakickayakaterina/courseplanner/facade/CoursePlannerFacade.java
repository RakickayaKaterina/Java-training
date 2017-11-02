package com.github.rakickayakaterina.courseplanner.facade;

import java.util.Date;

import com.github.rakickayakaterina.courseplanner.api.services.*;
import com.github.rakickayakaterina.courseplanner.beans.Course;
import com.github.rakickayakaterina.courseplanner.beans.Lector;
import com.github.rakickayakaterina.courseplanner.beans.Lecture;
import com.github.rakickayakaterina.courseplanner.beans.Student;
import com.github.rakickayakaterina.courseplanner.comparators.course.*;
import com.github.rakickayakaterina.courseplanner.comparators.lector.AlphabetLectorComparator;
import com.github.rakickayakaterina.courseplanner.comparators.lesson.*;
import com.github.rakickayakaterina.courseplanner.utils.Printer;

public class CoursePlannerFacade {
	private IServiceLectors mServiceLectors;
	private IServiceCourses mServiceCourses;
	private IServiceTimeTable mServiceTimeTable;
	private IServiceStudents mServiceStudent;

	public CoursePlannerFacade(IServiceLectors mServiceLectors, IServiceCourses mServiceCourses,
			IServiceTimeTable mServiceTimeTable, IServiceStudents mServiceStudent) {
		super();
		this.mServiceLectors = mServiceLectors;
		this.mServiceCourses = mServiceCourses;
		this.mServiceTimeTable = mServiceTimeTable;
		this.mServiceStudent = mServiceStudent;
	}

	// 1
	public void showSortedCoursesByStartDate() {
		Printer.show(mServiceCourses.getSortedList(new DateCourseComparator()));
	}

	public void showSortedCoursesByCountStudents() {
		Printer.show(mServiceCourses.getSortedList(new CountStudentsComparator()));
	}

	public void showSortedCoursesByLectorName() {
		Printer.show(mServiceCourses.getSortedList(new LectorNameComparator()));
	}

	public void showSortedCoursesByAlphabet() {
		Printer.show(mServiceCourses.getSortedList(new AlphabetCourseComparator()));
	}

	// 2
	public void showSortedLectorsByAlphabet() {
		Printer.show(mServiceLectors.getSortedList(new AlphabetLectorComparator()));
	}

	public void showSortedLectorsByCountCourses() {
		Printer.show(mServiceLectors.getLectorsInformation());
	}

	// 3
	public void showDetailedDescription(long pIdCourse) {
		Printer.show(mServiceCourses.getDetailDescription(pIdCourse));
	}

	// 4
	public void showSortedTimeTableByDate() {
		Printer.show(mServiceTimeTable.getSortedList(new DateLessonComparator()));
	}

	public void showSortedTimeTableByAlphabet() {
		Printer.show(mServiceTimeTable.getSortedList(new AlphabetLessonComparator()));

	}

	// 5
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

	// 6
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

	// 7
	public void showTotalCountCourse() {
		Printer.show("Total count courses: " + mServiceCourses.getTotalCountCourses());
	}

	public void showTotalCountStudents() {
		Printer.show("Total count students: " + mServiceStudent.getTotalCountStudents());
	}

	public void showTotalCountLectors() {
		Printer.show("Total count lectors: " + mServiceLectors.getTotalCountLectors());
	}
	//8
	public void showListLessonByDate(Date pDate) {
		Printer.show(mServiceTimeTable.getListLesson(pDate));
	}
	// 9
		public void showPastCourses(Date startDate, Date endDate) {
			Printer.show(mServiceCourses.getPastCourses(startDate, endDate));
		}
	// 10
	public void addCourse(Course course) {
		mServiceCourses.addCourse(course);
	}

	// 11
	public void removeCourse(long idCourse) {
		mServiceCourses.removeCourse(idCourse);
	}

	// 12
	public void addLectureToCourse(Lecture lecture, long pIdCourse) {
		mServiceCourses.addLectureToCourse(lecture, pIdCourse);
	}

	public void removeLectureFromCourse(long idLecture, long idCourse) {
		mServiceCourses.removeLectureFromCourse(idLecture, idCourse);
	}

	// 13
	public void addStudent(Student student) {
		mServiceStudent.addStudent(student);
	}

	public void addStudentToCourse(long idStudent, long idCourse) {
		mServiceCourses.addStudentToCourse(idStudent, idCourse);
	}

	public void removeStudent(long idStudent) {
		mServiceStudent.removeStudent(idStudent);
	}

	public void removeStudentFromCourse(long idStudent, long idCourse) {
		mServiceCourses.removeStudentFromCourse(idStudent, idCourse);
	}

	// 14
	public void addLector(Lector lector) {
		mServiceLectors.addLector(lector);
	}

	public void addLectorToCourse(long idLector, long idCourse) {
		mServiceCourses.addLectorToCourse(idLector, idCourse);
	}

	public void removeLector(long idLector) {
		mServiceLectors.removeLector(idLector);
	}

	public void removeLectorFromCourse(long idLector, long idCourse) {
		mServiceCourses.removeLectorFromCourse(idLector, idCourse);
	}

	// 15
	public void createTimeTableForLecture(long idLecture, Date dateForLecture) {
		mServiceTimeTable.createLesson(idLecture, dateForLecture);
	}

	public void removeTimeTableForLecture(long idLecture) {
		mServiceTimeTable.removeLessonByLecture(idLecture);
	}
}
