package com.senla.rakickaya.courseplanner.csv;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.beans.Course;
import com.senla.rakickaya.courseplanner.beans.Lector;
import com.senla.rakickaya.courseplanner.beans.Lecture;
import com.senla.rakickaya.courseplanner.beans.Lesson;
import com.senla.rakickaya.courseplanner.beans.Student;
import com.senla.rakickaya.courseplanner.utils.DateWorker;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class CSVConverter {
	public static ICourse parseCourse(CSVObject csvObject, List<IStudent> students, List<ILector> lectors) {
		try {
			String[] fields = csvObject.toString().split(Separators.SEPARATOR_FIELDS);
			long idCourse = Long.valueOf(fields[0]);
			String name = fields[1];
			String description = fields[2];
			Date startDate = DateWorker.createDate(fields[3]);
			Date endDate = DateWorker.createDate(fields[4]);
			Long idLector = fields[5].equals("Not lector") ? null : Long.valueOf(fields[5]);
			ILector lector = idLector != null ? ListWorker.getItemById(lectors, idLector) : null;

			List<IStudent> studentsCourse = new ArrayList<>();
			for (String idObj : fields[6].split(Separators.SEPARATOR_ARRAYS)) {
				if (!idObj.equals("")) {
					Long idStudent = Long.valueOf(idObj);
					IStudent student = ListWorker.getItemById(students, idStudent);
					studentsCourse.add(student);
				}
			}
			List<ILecture> lectures = new ArrayList<>();
			for (String s : fields[7].split(Separators.SEPARATOR_ARRAYS)) {
				if (!s.equals("")) {
					String[] objs = s.split(Separators.SEPARATOR_OBJECT);
					Long idLecture = Long.valueOf(objs[0]);
					String nameLecture = objs[1];
					lectures.add(new Lecture(idLecture, nameLecture));
				}
			}
			ICourse course = new Course(idCourse, name, description, startDate, endDate);
			course.setLector(lector);
			course.setStudents(studentsCourse);
			course.setLectures(lectures);
			return course;

		} catch (ParseException e) {
			e.getMessage();
			e.printStackTrace();
			return null;
		}

	}

	public static IStudent parseStudent(CSVObject csvObject, List<ICourse> courses) {

		String[] fields = csvObject.toString().split(Separators.SEPARATOR_FIELDS);
		long idStudent = Long.valueOf(fields[0]);
		String name = fields[1];

		List<ICourse> courseStudent = new ArrayList<>();
		if (!fields[2].equals("No courses")) {
			for (String idObj : fields[2].split(Separators.SEPARATOR_ARRAYS)) {
				if (!idObj.equals("")) {
					Long idCourse = Long.valueOf(idObj);
					ICourse course = ListWorker.getItemById(courses, idCourse);
					courseStudent.add(course);
				}
			}
		}
		IStudent student = new Student(idStudent, name);
		student.setCourses(courseStudent);
		return student;

	}

	public static ILector parseLector(CSVObject csvObject) {

		String[] fields = csvObject.toString().split(Separators.SEPARATOR_FIELDS);
		long idLector = Long.valueOf(fields[0]);
		String name = fields[1];

		ILector lector = new Lector(idLector, name);
		return lector;

	}

	public static ILesson parseLesson(CSVObject csvObject, List<ILecture> lectures) {

		try {
			String[] fields = csvObject.toString().split(Separators.SEPARATOR_FIELDS);
			long idLesson = Long.valueOf(fields[0]);

			long idLecture = Long.valueOf(fields[1]);
			ILecture lecture = ListWorker.getItemById(lectures, idLecture);
			Date date = DateWorker.createDate(fields[2]);
			int countStudent = Integer.valueOf(fields[3]);
			ILesson lesson = new Lesson(idLesson, lecture, date, countStudent);

			return lesson;

		} catch (ParseException e) {
			e.getMessage();
			e.printStackTrace();
			return null;
		}

	}

}
