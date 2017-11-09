package com.senla.rakickaya.view.facade;

import com.senla.rakickaya.view.api.facade.IFacade;
import com.senla.rakickaya.view.dataExchange.Request;
import com.senla.rakickaya.view.dataExchange.Response;

public class Facade implements IFacade {

	@Override
	public Response getSortedCoursesByStartDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedCoursesByCountStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedCoursesByLectorName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedCoursesByAlphabet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedLectorsByAlphabet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedLectorsByCountCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getDetailedDescription(Request pIdCourse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedTimeTableByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedTimeTableByAlphabet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedCoursesByStartDate(Request afterDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedCoursesByCountStudents(Request afterDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedCoursesByLectorName(Request afterDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedCoursesByAlphabet(Request afterDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedCurrentCoursesByStartDate(Request currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedCurrentCoursesByCountStudents(Request currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedCurrentCoursesByLectorName(Request currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getSortedCurrentCoursesByAlphabet(Request currentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getTotalCountCourse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getTotalCountStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getTotalCountLectors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getListLessonByDate(Request date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getPastCourses(Request interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addCourse(Request course) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response removeCourse(Request idCourse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addLectureToCourse(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response removeLectureFromCourse(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addStudent(Request student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addStudentToCourse(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response removeStudent(Request idStudent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response removeStudentFromCourse(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addLector(Request lector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response addLectorToCourse(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response removeLector(Request idLector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response removeLectorFromCourse(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response createTimeTableForLecture(Request request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response removeTimeTableForLecture(Request idLecture) {
		// TODO Auto-generated method stub
		return null;
	}

}
