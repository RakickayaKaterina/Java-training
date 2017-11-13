package com.senla.rakickaya.view.dataExchange;

import java.text.ParseException;
import java.util.Date;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.enums.TagsRequest;
import com.senla.rakickaya.courseplanner.beans.Course;
import com.senla.rakickaya.courseplanner.beans.Lector;
import com.senla.rakickaya.courseplanner.beans.Lecture;
import com.senla.rakickaya.courseplanner.beans.Student;
import com.senla.rakickaya.utils.DateWorker;
import com.senla.rakickaya.utils.GeneratorId;

public class RequestExtractor {
	private IRequest mRequest;

	public RequestExtractor(IRequest mRequest) {
		super();
		this.mRequest = mRequest;
	}
	public IStudent extractStudent(){
		String studentName = mRequest.getObject(TagsRequest.studentName);
		long idStudent = GeneratorId.getInstance().getIdStudent();
		return new Student(idStudent, studentName);
	}
	public ILector extractLector(){
		String lectorName = mRequest.getObject(TagsRequest.lectorName);
		long idLector = GeneratorId.getInstance().getIdLector();
		return new Lector(idLector, lectorName);
	}
	public ILecture extractLecture(){
		String lectureName = mRequest.getObject(TagsRequest.lectureName);
		long idLecture = GeneratorId.getInstance().getIdLecture();
		return new Lecture(idLecture, lectureName);
	}
	public ICourse extractCourse() throws ParseException{
		String courseName = mRequest.getObject(TagsRequest.courseName);
		String descriptionCourse = mRequest.getObject(TagsRequest.descriptionCourse);
		Date startDate = DateWorker.createDate(mRequest.getObject(TagsRequest.startDateCourse));
		Date endDate = DateWorker.createDate(mRequest.getObject(TagsRequest.endDateCourse));
		long idCourse = GeneratorId.getInstance().getIdCourse();
		return new Course(idCourse, courseName, descriptionCourse, startDate, endDate);
	}
	private long extractId(TagsRequest el){
		long id = Long.valueOf(mRequest.getObject(el));
		return id;
	}
	public long extractIdCourse(){
		return extractId(TagsRequest.idCourse);
	}
	public long extractIdStudent(){
		return extractId(TagsRequest.idStudent);
	}
	public long extractIdLector(){
		return extractId(TagsRequest.idLector);
	}
	public long extractIdLecture(){
		return extractId(TagsRequest.idLecture);
	}
	private Date extractDate(TagsRequest el) throws ParseException{
		Date date = DateWorker.createDate(mRequest.getObject(el));
		return date;
	}
	public Date extractDate() throws ParseException{
		return extractDate(TagsRequest.date);
	}
	public Date extractStartDate() throws ParseException{
		return extractDate(TagsRequest.startDate);
	}
	public Date extractEndDate() throws ParseException{
		return extractDate(TagsRequest.endDate);
	}
	
}
