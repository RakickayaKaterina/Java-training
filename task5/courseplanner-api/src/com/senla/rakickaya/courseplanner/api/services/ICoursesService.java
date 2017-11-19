package com.senla.rakickaya.courseplanner.api.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;

public interface ICoursesService {
	public void addCourse(ICourse pCourse);

	public void removeCourse(long pId) throws EntityNotFoundException;

	public void updateCourse(ICourse pCourse);

	public ICourse getCourse(long pId);

	public List<ICourse> getListCourses();

	public void addStudentToCourse(long pIdStudent, long pIdCourse);

	public void removeStudentFromCourse(long pIdStudent, long pIdCourse) throws EntityNotFoundException;

	public void addLectorToCourse(long pIdLector, long pIdCourse);

	public void removeLectorFromCourse(long pIdLector, long pIdCourse) throws EntityNotFoundException;

	public void addLectureToCourse(ILecture pLecture, long pIdCourse);

	public void removeLectureFromCourse(long pIdLecture, long pIdCourse) throws EntityNotFoundException;

	public List<ICourse> getSortedList(Comparator<ICourse> pComparator);

	public List<ICourse> getListCoursesAfterDate(Date pDate, Comparator<ICourse> pComparator);

	public List<ICourse> getListCurrentCourses(Date pCurrentDate, Comparator<ICourse> pComparator);

	public int getTotalCountCourses();

	public List<ICourse> getPastCourses(Date startDateSub, Date endDateSub);

	List<ILecture> getAllLectures();

	public void save();

}
