package com.senla.rakickaya.courseplanner.dataExchange;

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
import com.senla.rakickaya.courseplanner.utils.DateWorker;

public class RequestExtractor {
	private static final long DEFAULT = 0L;
	
	private IRequest mRequest;

	public RequestExtractor(IRequest mRequest) {
		super();
		this.mRequest = mRequest;
	}

	public IStudent extractStudent() {
		String studentName = mRequest.getObject(TagsRequest.STUDENT_NAME);
		long idStudent = DEFAULT;
		return new Student(idStudent, studentName);
	}

	public ILector extractLector() {
		String lectorName = mRequest.getObject(TagsRequest.LECTOR_NAME);
		long idLector = DEFAULT;
		return new Lector(idLector, lectorName);
	}

	public ILecture extractLecture() {
		String lectureName = mRequest.getObject(TagsRequest.LECTURE_NAME);
		long idLecture = DEFAULT;
		return new Lecture(idLecture, lectureName);
	}

	public ICourse extractCourse() throws ParseException {
		String courseName = mRequest.getObject(TagsRequest.COURSE_NAME);
		String descriptionCourse = mRequest.getObject(TagsRequest.DESCRIPTION_COURSE);
		Date startDate = DateWorker.createDate(mRequest.getObject(TagsRequest.START_DATE_COURSE));
		Date endDate = DateWorker.createDate(mRequest.getObject(TagsRequest.END_DATE_COURSE));
		long idCourse = DEFAULT;
		return new Course(idCourse, courseName, descriptionCourse, startDate, endDate);
	}

	private long extractId(TagsRequest el) {
		long id = Long.valueOf(mRequest.getObject(el));
		return id;
	}

	public int extractCountStudent() {
		String str = mRequest.getObject(TagsRequest.COUNT_STUDENT);
		int i = Integer.valueOf(str);
		return i;
	}

	public String extractPath() {
		return mRequest.getObject(TagsRequest.PATH);
	}

	public long extractIdCourse() {
		return extractId(TagsRequest.ID_COURSE);
	}

	public long extractIdStudent() {
		return extractId(TagsRequest.ID_STUDENT);
	}

	public long extractIdLector() {
		return extractId(TagsRequest.ID_LECTOR);
	}

	public long extractIdLecture() {
		return extractId(TagsRequest.ID_LECTURE);
	}

	private Date extractDate(TagsRequest el) throws ParseException {
		Date date = DateWorker.createDate(mRequest.getObject(el));
		return date;
	}

	public Date extractDate() throws ParseException {
		return extractDate(TagsRequest.DATE);
	}

	public Date extractStartDate() throws ParseException {
		return extractDate(TagsRequest.START_DATE);
	}

	public Date extractEndDate() throws ParseException {
		return extractDate(TagsRequest.END_DATE);
	}

}
