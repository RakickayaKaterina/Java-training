package com.senla.rakickaya.courseplanner.api.facade;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;

public interface IFacade {
	IResponse getSortedCoursesByStartDate();

	IResponse getSortedCoursesByCountStudents();

	IResponse getSortedCoursesByLectorName();

	IResponse getSortedCoursesByAlphabet();

	IResponse getSortedLectorsByAlphabet();

	IResponse getSortedLectorsByCountCourses();

	IResponse getDetailedDescription(IRequest pIdCourse);

	IResponse getSortedTimeTableByDate();

	IResponse getSortedTimeTableByAlphabet();

	IResponse getSortedCoursesByStartDate(IRequest afterDate);

	IResponse getSortedCoursesByCountStudents(IRequest afterDate);

	IResponse getSortedCoursesByLectorName(IRequest afterDate);

	IResponse getSortedCoursesByAlphabet(IRequest afterDate);

	IResponse getSortedCurrentCoursesByStartDate(IRequest currentDate);

	IResponse getSortedCurrentCoursesByCountStudents(IRequest currentDate);

	IResponse getSortedCurrentCoursesByLectorName(IRequest currentDate);

	IResponse getSortedCurrentCoursesByAlphabet(IRequest currentDate);

	IResponse getTotalCountCourse();

	IResponse getTotalCountStudents();

	IResponse getTotalCountLectors();

	IResponse getLessonsByDate(IRequest date);

	IResponse getPastCourses(IRequest interval);

	IResponse addCourse(IRequest course);

	IResponse removeCourse(IRequest idCourse);

	IResponse addLectureToCourse(IRequest request);

	IResponse removeLectureFromCourse(IRequest request);

	IResponse addStudent(IRequest student);

	IResponse addStudentToCourse(IRequest request);

	IResponse removeStudent(IRequest idStudent);

	IResponse removeStudentFromCourse(IRequest request);

	IResponse addLector(IRequest lector);

	IResponse addLectorToCourse(IRequest request);

	IResponse removeLector(IRequest idLector);

	IResponse removeLectorFromCourse(IRequest request);

	IResponse createTimeTableForLecture(IRequest request);

	IResponse removeTimeTableForLecture(IRequest idLecture);

	IResponse getAllStudents();

	IResponse getAllLectors();

	IResponse getAllCourses();

	IResponse getAllLectures();

	IResponse cloneCourse(IRequest idCourse);

	IResponse exportCourse(IRequest request);

	IResponse importCourse(IRequest request);

	IResponse exportTimeTable(IRequest request);

	IResponse exportStudent(IRequest request);

	IResponse exportLector(IRequest request);

	IResponse importTimeTable(IRequest request);

	IResponse importStudent(IRequest request);

	IResponse importLector(IRequest request);
}
