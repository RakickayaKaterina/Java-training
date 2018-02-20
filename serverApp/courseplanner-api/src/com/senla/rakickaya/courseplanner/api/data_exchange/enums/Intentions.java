package com.senla.rakickaya.courseplanner.api.data_exchange.enums;

public enum Intentions {
	GET_SORTED_COURSES_BY_START_DATE("getSortedCoursesByStartDate", false), GET_SORTED_COURSES_BY_COUNT_STUDENT("getSortedCoursesByCountStudents", false), GET_SORTED_COURSES_BY_LECTOR_NAME("getSortedCoursesByLectorName", false), GET_SORTED_COURSES_BY_ALPH("getSortedCoursesByAlphabet", false),
	GET_SORTED_LECTORS_BY_COUNT_STUDENT("getSortedLectorsByCountCourses", false), GET_SORTED_LECTORS_BY_ALPH("getSortedLectorsByAlphabet", false),
	GET_DETAILED_DESCRIPTION("getDetailedDescription", true),
	GET_SORTED_TIME_TABLE_BY_DATE("getSortedTimeTableByDate", false), GET_SORTED_TIME_TABLE_BY_ALPH("getSortedTimeTableByAlphabet", false),
	GET_SORTED_COURSES_BY_START_DATE_PARAM("getSortedCoursesByStartDate", true), GET_SORTED_COURSES_BY_COUNT_STUDENT_PARAM("getSortedCoursesByCountStudents", true), GET_SORTED_COURSES_BY_LECTOR_NAME_PARAM("getSortedCoursesByLectorName", true), GET_SORTED_COURSES_BY_ALPH_PARAM("getSortedCoursesByAlphabet", true),
	GET_SORTED_CURRENT_COURSES_BY_START_DATE("getSortedCurrentCoursesByStartDate", true), GET_SORTED_CURRENT_COURSES_BY_COUNT_STUDENT("getSortedCurrentCoursesByCountStudents", true), GET_SORTED_CURRENT_COURSES_BY_LECTOR_NAME("getSortedCurrentCoursesByLectorName", true), GET_SORTED_CURRENT_COURSES_BY_ALPH("getSortedCurrentCoursesByAlphabet", true), 	
	GET_TOTAL_COUNT_COURSES("getTotalCountCourse", false), GET_TOTAL_COUNT_STUDENTS("getTotalCountStudents", false), GET_TOTAL_COUNT_LECTORS("getTotalCountLectors", false),
	GET_LESSONS_BY_DATE("getLessonsByDate", true),
	GET_PAST_COURSES("getPastCourses", true), 
	ADD_COURSE("addCourse", true), REMOVE_COURSE("removeCourse", true), ADD_LECTURE_TO_COURSE("addLectureToCourse", true),REMOVE_LECTURE_FROM_COURSE("removeLectureFromCourse", true),
	ADD_STUDENT("addStudent", true), REMOVE_STUDENT("removeStudent", true), ADD_STUDENT_TO_COURSE("addStudentToCourse", true),REMOVE_STUDENT_FROM_COURSE("removeStudentFromCourse", true), 
	ADD_LECTOR("addLector", true), REMOVE_LECTOR("removeLector", true), ADD_LECTOR_TO_COURSE("addLectorToCourse", true),REMOVE_LECTOR_FROM_COURSE("removeLectorFromCourse", true),
	CREATE_TIME_TABLE_FOR_LECTURE("createTimeTableForLecture", true), REMOVE_TIME_TABLE_FOR_LECTURE("removeTimeTableForLecture", true),
	GET_ALL_STUDENTS("getAllStudents", false), GET_ALL_LECTORS("getAllLectors", false), GET_ALL_COURSES("getAllCourses", false), GET_ALL_LECTURES("getAllLectures", false),
	CLONE_COURSES("cloneCourse", true), 
	EXPORT_COURSES("exportCourse", true), IMPORT_COURSES("importCourse", true),
	EXPORT_STUDENTS("exportStudent", true), IMPORT_STUDENTS("importStudent", true),
	EXPORT_LECTORS("exportLector", true), IMPORT_LECTORS("importLector", true),
	EXPORT_TIME_TABLE("exportTimeTable", true), IMPORT_TIME_TABLE("importTimeTable", true), GET_ALL_COURSES_WITH_LECTURE("getCoursesWithLectures", false);
	
	private String methodName;
	private boolean hasParam;
	
	private Intentions(String intention, boolean hasParam) {
		methodName = intention;
		this.hasParam = hasParam;
	}
	
	public String getIntention(){
		return methodName;
	}

	public boolean isHasParam() {
		return hasParam;
	}
	
}
