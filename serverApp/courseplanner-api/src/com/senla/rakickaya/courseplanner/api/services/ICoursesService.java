package com.senla.rakickaya.courseplanner.api.services;

import java.util.Date;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.dao.enums.SortColumnCourse;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;

public interface ICoursesService {
	void addCourse(ICourse pCourse);

	void removeCourse(long pId) throws EntityNotFoundException;

	void updateCourse(ICourse pCourse);

	ICourse getCourse(long pId);

	List<ICourse> getCourses();

	void addStudentToCourse(long pIdStudent, long pIdCourse);

	void removeStudentFromCourse(long pIdStudent, long pIdCourse) throws EntityNotFoundException;

	void addLectorToCourse(long pIdLector, long pIdCourse);

	void removeLectorFromCourse(long pIdLector, long pIdCourse) throws EntityNotFoundException;

	void addLectureToCourse(ILecture pLecture, long pIdCourse);

	int getTotalCountCourses();

	List<ICourse> getPastCourses(Date startDateSub, Date endDateSub);

	List<ILecture> getAllLectures();

	void cloneCourseById(long pId) throws CloneNotSupportedException, EntityNotFoundException;

	void exportCSV(String path);

	void importCSV(String path) throws Exception;

	List<ICourse> getCoursesWithStudents();

	List<ICourse> getCoursesWithLectures();

	List<ICourse> getCoursesWithLector();

	List<ICourse> getSortedList(SortColumnCourse column);

	List<ICourse> getCoursesWithLectorAfterDate(Date pDate, SortColumnCourse column);

	List<ICourse> getCurrentCoursesWithLector(Date pCurrentDate, SortColumnCourse column);

	List<ICourse> getCoursesSortedByCountStudent();

	List<ICourse> getCoursesAfterDateWithStudents(Date pDate);

	void removeLectureFromCourse(long pIdLecture) throws EntityNotFoundException;

}
