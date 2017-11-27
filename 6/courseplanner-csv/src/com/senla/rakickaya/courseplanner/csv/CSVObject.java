package com.senla.rakickaya.courseplanner.csv;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.utils.DateWorker;

public class CSVObject {
	private String csvObject;

	CSVObject(String csvObject) {
		this.csvObject = csvObject;
	}

	public static CSVObject valueOf(ICourse course) {
		StringBuilder str = new StringBuilder();
		ILector lector = course.getLector();
		List<ILecture> lectures = course.getLectures();
		List<IStudent> students = course.getStudents();
		str.append(course.getId()).append(Separators.SEPARATOR_FIELDS).append(course.getName())
				.append(Separators.SEPARATOR_FIELDS).append(course.getDescription()).append(Separators.SEPARATOR_FIELDS)
				.append(DateWorker.getStringFromDate(course.getStartDate())).append(Separators.SEPARATOR_FIELDS)
				.append(DateWorker.getStringFromDate(course.getEndDate())).append(Separators.SEPARATOR_FIELDS)
				.append(lector != null ? lector.getId() : "Not lector").append(Separators.SEPARATOR_FIELDS);
		for (IStudent student : students) {
			str.append(student.getId()).append(Separators.SEPARATOR_ARRAYS);
		}
		str.append(Separators.SEPARATOR_FIELDS);
		for (ILecture lecture : lectures) {
			str.append(lecture.getId()).append(Separators.SEPARATOR_OBJECT).append(lecture.getName())
					.append(Separators.SEPARATOR_ARRAYS);
		}
		return new CSVObject(str.toString());
	}

	public static CSVObject valueOf(ILector lector) {
		StringBuilder str = new StringBuilder();
		str.append(lector.getId()).append(Separators.SEPARATOR_FIELDS).append(lector.getName());
		return new CSVObject(str.toString());
	}

	public static CSVObject valueOf(IStudent student) {
		StringBuilder str = new StringBuilder();
		List<ICourse> courses = student.getCourses();
		str.append(student.getId()).append(Separators.SEPARATOR_FIELDS).append(student.getNameStudent())
				.append(Separators.SEPARATOR_FIELDS);

		if (courses == null || courses.isEmpty()) {
			str.append("No courses");
		} else {
			for (ICourse course : courses) {
				str.append(course.getId()).append(Separators.SEPARATOR_ARRAYS);
			}
		}
		str.append(Separators.SEPARATOR_FIELDS);
		return new CSVObject(str.toString());
	}

	public static CSVObject valueOf(ILesson lesson) {
		StringBuilder str = new StringBuilder();
		str.append(lesson.getId()).append(Separators.SEPARATOR_FIELDS).append(lesson.getLecture().getId())
				.append(Separators.SEPARATOR_FIELDS).append(DateWorker.getStringFromDate(lesson.getDate()))
				.append(Separators.SEPARATOR_FIELDS).append(lesson.getCountStudent());
		return new CSVObject(str.toString());
	}

	@Override
	public String toString() {
		return csvObject;
	}

}
