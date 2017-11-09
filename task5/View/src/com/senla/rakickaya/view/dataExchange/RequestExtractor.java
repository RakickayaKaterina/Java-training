package com.senla.rakickaya.view.dataExchange;

import java.text.ParseException;
import java.util.Date;

import com.senla.rakickaya.model.beans.Course;
import com.senla.rakickaya.model.beans.Lector;
import com.senla.rakickaya.model.beans.Lecture;
import com.senla.rakickaya.model.beans.Student;
import com.senla.rakickaya.utils.DateWorker;
import com.senla.rakickaya.utils.launcher.Launcher;

public class RequestExtractor {
	private Request mRequest;

	public RequestExtractor(Request mRequest) {
		super();
		this.mRequest = mRequest;
	}
	public Student extractStudent(){
		String studentName = mRequest.getObject("studentName");
		long idStudent = Launcher.getInstance().getGeneratorId().getIdStudent();
		return new Student(idStudent, studentName);
	}
	public Lector extractLector(){
		String lectorName = mRequest.getObject("lectorName");
		long idLector = Launcher.getInstance().getGeneratorId().getIdLector();
		return new Lector(idLector, lectorName);
	}
	public Lecture extractLecture(){
		String lectureName = mRequest.getObject("lectureName");
		long idLecture = Launcher.getInstance().getGeneratorId().getIdLecture();
		return new Lecture(idLecture, lectureName);
	}
	public Course extractCourse() throws ParseException{
		String courseName = mRequest.getObject("courseName");
		String descriptionCourse = mRequest.getObject("descriptionCourse");
		Date startDate = DateWorker.createDate(mRequest.getObject("startDate"));
		Date endDate = DateWorker.createDate(mRequest.getObject("endDate"));
		long idCourse = Launcher.getInstance().getGeneratorId().getIdCourse();
		return new Course(idCourse, courseName, descriptionCourse, startDate, endDate);
	}
	private long extractId(String el){
		long id = Long.valueOf(mRequest.getObject(el));
		return id;
	}
	public long extractIdCourse(){
		return extractId("idCourse");
	}
	public long extractIdStudent(){
		return extractId("idStudent");
	}
	public long extractIdLector(){
		return extractId("idLector");
	}
	public long extractIdLecture(){
		return extractId("idLecture");
	}
	private Date extractDate(String el) throws ParseException{
		Date date = DateWorker.createDate(mRequest.getObject(el));
		return date;
	}
	public Date extractDate() throws ParseException{
		return extractDate("date");
	}
	public Date extractStartDate() throws ParseException{
		return extractDate("startDate");
	}
	public Date extractEndDate() throws ParseException{
		return extractDate("endDate");
	}
	
}
