package facade;

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
import models.Lector;
import models.Lecture;
import models.Student;
import services.IServiceCourses;
import services.IServiceLectors;
import services.IServiceStudent;
import services.IServiceTimeTable;
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

	// 2
	public void showSortedLectorsByAlphabet() {
		Printer.show(mServiceLectors.sort(new AlphabetLectorComparator()));
	}

	public void showSortedLectorsByCountCourses() {
		Printer.show(mServiceLectors.sort(new CountCoursesComparator()));
	}

	// 1
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

	// 3
	public void showDetailedDescription(long pIdCourse) {
		Course course = mServiceCourses.getCourse(pIdCourse);
		if(course==null) return;
		
		String detailedDescription = String.format("Detailed description:\ndescription=%s\nlector=%s\n",
				course.getDescription(), course.getLector().getName());
		
		StringBuilder builder = new StringBuilder(detailedDescription);
		builder.append("Students:\n");
		for(Student student : course.getStudents()){
			if(student!=null){
				builder.append(student.getNameStudent()+"\n");
			}
		}
		
		Printer.show(builder.toString());
	}

	// 4
	public void showSortedTimeTableByDate() {
		Printer.show(mServiceTimeTable.sort(new DateLessonComparator()));
	}

	public void showSortedTimeTableByAlphabet() {
		Printer.show(mServiceTimeTable.sort(new AlphabetLessonComparator()));
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

	// 8
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

	// 14
	public void addStudent(Student student) {
		mServiceStudent.addStudent(student);
	}

	public void addStudentToCourse(long idStudent, long idCourse) {
		mServiceCourses.addStudentToCourse(idStudent, idCourse);
	}

	public void removeStudent(long idStudent) {
		mServiceStudent.removeStudent(idStudent);
	}

	// 15
	public void addLector(Lector lector) {
		mServiceLectors.addLector(lector);
	}

	public void addLectorToCourse(long idLector, long idCourse) {
		mServiceCourses.addLectorToCourse(idLector, idCourse);
	}

	public void removeLector(long idLector) {
		mServiceLectors.removeLector(idLector);
	}

	// 13
	public void addLectureToCourse(Lecture lecture, long pIdCourse) {
		mServiceCourses.addLectureToCourse(lecture, pIdCourse);
	}

	public void removeLecture(long idLecture) {
		mServiceCourses.removeLectureFromCourse(idLecture);
	}

	// 16
	public void createTimeTableForLecture(long idLecture, Date dateForLecture) {
		mServiceTimeTable.createLesson(idLecture, dateForLecture);
	}

	public void removeTimeTableForLecture(long idLecture) {
		mServiceTimeTable.removeLessonByLectureId(idLecture);
	}

}
