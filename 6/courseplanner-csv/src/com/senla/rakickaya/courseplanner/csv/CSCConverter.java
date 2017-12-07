package com.senla.rakickaya.courseplanner.csv;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.beans.Course;
import com.senla.rakickaya.courseplanner.beans.Lecture;
import com.senla.rakickaya.courseplanner.utils.DateWorker;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class CSCConverter {
	public static ICourse parseCourse(String csvObject, List<IStudent> students, List<ILector> lectors) {
		try {
			String[] fields = csvObject.split(Separators.SEPARATOR_FIELDS);
			long idCourse = Integer.valueOf(fields[0]);
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
			ICourse course = new Course(idCourse,name,description,startDate,endDate);
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
}
