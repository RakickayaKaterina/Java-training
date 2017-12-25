package com.senla.rakickaya.courseplanner.server;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.api.facade.IFacade;
import com.senla.rakickaya.courseplanner.dependency.ServiceDI;
import com.senla.rakickaya.courseplanner.server.api.IFacadeWrapper;
import com.senla.rakickaya.courseplanner.utils.Decoder;

public class FacadeWrapper implements IFacadeWrapper {
	private IFacade facade;
	private Decoder decoder;

	public FacadeWrapper() {
		super();
		decoder = new Decoder();
		facade = (IFacade) ServiceDI.getInstance().getObject(IFacade.class);
	}

	@Override
	public String getResponse(String request) {
		IRequest req = decoder.decode(request);
		IResponse response = null;
		switch (req.getIntent()) {
		case GET_SORTED_COURSES_BY_START_DATE:
			response = facade.getSortedCoursesByStartDate();
			break;
		case GET_SORTED_COURSES_BY_COUNT_STUDENT:
			response = facade.getSortedCoursesByCountStudents();
			break;
		case GET_SORTED_COURSES_BY_LECTOR_NAME:
			response = facade.getSortedCoursesByLectorName();
			break;
		case GET_SORTED_COURSES_BY_ALPH:
			response = facade.getSortedCoursesByAlphabet();
			break;
		case GET_SORTED_LECTORS_BY_COUNT_STUDENT:
			response = facade.getSortedLectorsByCountCourses();
			break;
		case GET_SORTED_LECTORS_BY_ALPH:
			response = facade.getSortedLectorsByAlphabet();
			break;
		case GET_DETAILED_DESCRIPTION:
			response = facade.getDetailedDescription(req);
			break;
		case GET_SORTED_TIME_TABLE_BY_DATE:
			response = facade.getSortedTimeTableByDate();
			break;
		case GET_SORTED_TIME_TABLE_BY_ALPH:
			response = facade.getSortedTimeTableByAlphabet();
			break;
		case GET_SORTED_COURSES_BY_START_DATE_PARAM:
			response = facade.getSortedCoursesByStartDate(req);
			break;
		case GET_SORTED_COURSES_BY_COUNT_STUDENT_PARAM:
			response = facade.getSortedCoursesByCountStudents(req);
			break;
		case GET_SORTED_COURSES_BY_LECTOR_NAME_PARAM:
			response = facade.getSortedCoursesByLectorName(req);
			break;
		case GET_SORTED_COURSES_BY_ALPH_PARAM:
			response = facade.getSortedCoursesByAlphabet(req);
			break;
		case GET_SORTED_CURRENT_COURSES_BY_START_DATE:
			response = facade.getSortedCurrentCoursesByStartDate(req);
			break;
		case GET_SORTED_CURRENT_COURSES_BY_COUNT_STUDENT:
			response = facade.getSortedCurrentCoursesByCountStudents(req);
			break;
		case GET_SORTED_CURRENT_COURSES_BY_LECTOR_NAME:
			response = facade.getSortedCurrentCoursesByLectorName(req);
			break;
		case GET_SORTED_CURRENT_COURSES_BY_ALPH:
			response = facade.getSortedCurrentCoursesByAlphabet(req);
			break;
		case GET_TOTAL_COUNT_COURSES:
			response = facade.getTotalCountCourse();
			break;
		case GET_TOTAL_COUNT_STUDENTS:
			response = facade.getTotalCountStudents();
			break;
		case GET_TOTAL_COUNT_LECTORS:
			response = facade.getTotalCountLectors();
			break;
		case GET_LESSONS_BY_DATE:
			response = facade.getLessonsByDate(req);
			break;
		case GET_PAST_COURSES:
			response = facade.getPastCourses(req);
			break;
		case ADD_COURSE:
			response = facade.addCourse(req);
			break;
		case REMOVE_COURSE:
			response = facade.removeCourse(req);
			break;
		case ADD_LECTURE_TO_COURSE:
			response = facade.addLectureToCourse(req);
			break;
		case REMOVE_LECTURE_FROM_COURSE:
			response = facade.removeLectureFromCourse(req);
			break;
		case ADD_STUDENT:
			response = facade.addStudent(req);
			break;
		case REMOVE_STUDENT:
			response = facade.removeStudent(req);
			break;
		case ADD_STUDENT_TO_COURSE:
			response = facade.addStudentToCourse(req);
			break;
		case REMOVE_STUDENT_FROM_COURSE:
			response = facade.removeStudentFromCourse(req);
			break;
		case ADD_LECTOR:
			response = facade.addLector(req);
			break;
		case REMOVE_LECTOR:
			response = facade.removeLector(req);
			break;
		case ADD_LECTOR_TO_COURSE:
			response = facade.addLectorToCourse(req);
			break;
		case REMOVE_LECTOR_FROM_COURSE:
			response = facade.removeLectorFromCourse(req);
			break;
		case CREATE_TIME_TABLE_FOR_LECTURE:
			response = facade.createTimeTableForLecture(req);
			break;
		case REMOVE_TIME_TABLE_FOR_LECTURE:
			response = facade.removeTimeTableForLecture(req);
			break;
		case GET_ALL_COURSES:
			response = facade.getAllCourses();
			break;
		case GET_ALL_STUDENTS:
			response = facade.getAllStudents();
			break;
		case GET_ALL_LECTURES:
			response = facade.getAllLectures();
			break;
		case GET_ALL_LECTORS:
			response = facade.getAllLectors();
			break;
		case CLONE_COURSES:
			response = facade.cloneCourse(req);
			break;
		case EXPORT_COURSES:
			response = facade.exportCourse(req);
			break;
		case EXPORT_STUDENTS:
			response = facade.exportStudent(req);
			break;
		case EXPORT_LECTORS:
			response = facade.exportLector(req);
			break;
		case EXPORT_TIME_TABLE:
			response = facade.exportTimeTable(req);
			break;
		case IMPORT_COURSES:
			response = facade.importCourse(req);
			break;
		case IMPORT_STUDENTS:
			response = facade.importStudent(req);
			break;
		case IMPORT_LECTORS:
			response = facade.importLector(req);
			break;
		case IMPORT_TIME_TABLE:
			response = facade.importTimeTable(req);
			break;
		
			

		default:
			break;
		}
		String decodeResponse = decoder.encode(response);
		return decodeResponse;
	}

}
