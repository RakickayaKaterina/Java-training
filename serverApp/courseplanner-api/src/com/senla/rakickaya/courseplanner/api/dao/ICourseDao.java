package com.senla.rakickaya.courseplanner.api.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.dao.enums.SortColumnCourse;

public interface ICourseDao extends GenericDao<ICourse, Long> {

	List<ICourse> getCoursesWithStudents(Connection connection) throws Exception;

	void addStudentToCourse(Long pIdCourse, Long pIdStudent, Connection connection) throws Exception;

	void removeStudentFromCourse(Long pIdCourse, Long pIdStudent, Connection connection) throws Exception;

	void removeLectorFromCourse(Long pIdCourse, Long pIdLector, Connection connection) throws Exception;

	void addLectorToCourse(Long pIdCourse, Long pIdLector, Connection connection) throws Exception;

	List<ICourse> getCoursesWithLectures(Connection connection) throws Exception;

	List<ICourse> getCoursesWithLectorSortByLectorName(Connection connection) throws Exception;

	void addLectureToCourse(Long pIdCourse, ILecture lecture, Connection connection) throws Exception;

	List<ICourse> getSortedCourses(SortColumnCourse column, Connection connection) throws Exception;

	List<ICourse> getSortedCoursesByAfterDate(Date pDate, SortColumnCourse column, Connection connection) throws Exception;

	List<ICourse> getSortedCoursesByCurrentDate(Date pCurrentDate, SortColumnCourse column, Connection connection) throws Exception;

	int getCount(Connection connection) throws Exception;

	List<ICourse> getPastCourses(Date startDateSub, Date endDateSub, Connection connection) throws Exception;

	List<ICourse> getSortedCoursesWithStudentsAfterDate(Date pDate, Connection connection) throws Exception;

	List<ILecture> getAllLectures(Connection connection) throws Exception;

	void removeLectureFromCourse(Long pIdLecture, Connection connection) throws Exception;

	ILecture findLectureById(Long idLecture, Connection connection) throws Exception;

}
