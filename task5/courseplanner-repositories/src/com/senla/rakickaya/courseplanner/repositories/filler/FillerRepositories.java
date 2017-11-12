package com.senla.rakickaya.courseplanner.repositories.filler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.rakickaya.courseplanner.api.beans.*;
import com.senla.rakickaya.courseplanner.beans.Course;
import com.senla.rakickaya.courseplanner.beans.Lector;
import com.senla.rakickaya.courseplanner.beans.Lecture;
import com.senla.rakickaya.courseplanner.beans.Lesson;
import com.senla.rakickaya.courseplanner.beans.RelationSC;
import com.senla.rakickaya.courseplanner.beans.Student;
import com.senla.rakickaya.utils.DateWorker;
import com.senla.rakickaya.utils.ListWorker;
import com.senla.rakickaya.utils.config.PathsToFiles;

public class FillerRepositories {

	private final String SEPARATOR_ATTRIBUTES = "@@";
	private final String SEPARATOR_ARRAYS = "$$";
	private final String SEPARATOR_OBJECT = "##";
	private final String SEPARATOR_ITEMS_ARRAY = ";;";

	private TextFileWorker studentsFile;
	private TextFileWorker lectorsFile;
	private TextFileWorker coursesFile;
	private TextFileWorker relationsFile;
	private TextFileWorker timetableFile;

	private List<IStudent> students;
	private List<ILector> lectors;
	private List<ICourse> courses;
	private List<IRelationSC> relations;
	private List<ILesson> timeTable;

	private static FillerRepositories fillerRepositories;

	public static FillerRepositories getInstance() {
		if (fillerRepositories == null) {
			fillerRepositories = new FillerRepositories(PathsToFiles.STUDENTS_FILE, PathsToFiles.LECTORS_FILE,
					PathsToFiles.COURSES_FILE, PathsToFiles.RELATIONS_FILE, PathsToFiles.TIME_TABLE_FILE);
		}
		return fillerRepositories;
	}

	private FillerRepositories(String pathStudentFile, String pathLectorsFile, String pathCoursesFile,
			String pathRelationsFile, String pathTimeTableFile) {
		init();
		studentsFile = new TextFileWorker(pathStudentFile);
		lectorsFile = new TextFileWorker(pathLectorsFile);
		coursesFile = new TextFileWorker(pathCoursesFile);
		relationsFile = new TextFileWorker(pathRelationsFile);
		timetableFile = new TextFileWorker(pathTimeTableFile);
		fillAll();
	}

	private void init() {
		students = new ArrayList<>();
		lectors = new ArrayList<>();
		courses = new ArrayList<>();
		relations = new ArrayList<>();
		timeTable = new ArrayList<>();
	}

	public void writeLectorToFile(List<ILector> pLector) {
		StringBuilder str = new StringBuilder();
		for (ILector lector : pLector) {
			if (lector == null) {
				continue;
			}
			str.append(lector.getId() + SEPARATOR_ATTRIBUTES);
			str.append(lector.getName() + SEPARATOR_OBJECT);

		}
		lectorsFile.writeToFile(str.toString().split(SEPARATOR_OBJECT));
	}

	private void readLectorsFromFile() {
		String[] listObjects = lectorsFile.readFromFile();
		for (String object : listObjects) {
			if (object.equals("")) {
				continue;
			}
			String[] attributes = object.split(SEPARATOR_ATTRIBUTES);
			long id = Long.parseLong(attributes[0]);
			String name = attributes[1];
			lectors.add(new Lector(id, name));
		}
	}

	public void writeCourseToFile(List<ICourse> pCourse) {
		StringBuilder str = new StringBuilder();
		for (ICourse course : pCourse) {
			if (course == null) {
				continue;
			}
			str.append(course.getId() + SEPARATOR_ATTRIBUTES);
			str.append(course.getName() + SEPARATOR_ATTRIBUTES);
			str.append(course.getDescription() + SEPARATOR_ATTRIBUTES);
			str.append(DateWorker.getStringFromDate(course.getStartDate()) + SEPARATOR_ATTRIBUTES);
			str.append(DateWorker.getStringFromDate(course.getEndDate()) + SEPARATOR_ATTRIBUTES);
			if (course.getLector() != null) {
				str.append(course.getLector().getId() + SEPARATOR_ATTRIBUTES);
			} else
				str.append("-1" + SEPARATOR_ATTRIBUTES);
			for (ILecture lecture : course.getLectures()) {
				if (lecture == null) {
					continue;
				}
				str.append(lecture.getId() + SEPARATOR_ITEMS_ARRAY);
				str.append(lecture.getName() + SEPARATOR_ARRAYS);

			}
			str.append(SEPARATOR_ARRAYS);
			str.append(SEPARATOR_OBJECT);

		}
		coursesFile.writeToFile(str.toString().split(SEPARATOR_OBJECT));
	}

	private void readCoursesFromFile() throws ParseException {
		String[] listObjects = coursesFile.readFromFile();
		for (String object : listObjects) {
			if (object.equals("")) {
				continue;
			}
			String[] attributes = object.split(SEPARATOR_ATTRIBUTES);
			long id = Long.parseLong(attributes[0]);
			String name = attributes[1];
			String description = attributes[2];
			Date startDate = DateWorker.createDate(attributes[3]);
			Date endDate = DateWorker.createDate(attributes[4]);
			long idLector = Long.parseLong(attributes[5]);
			int position = ListWorker.getIndexById(lectors, idLector);
			ILector lector = idLector < 0 ? null : lectors.get(position);

			List<ILecture> lectures = new ArrayList<>();
			String[] str = attributes[6].split("[$]+");
			for (String lecture : str) {
				if (lecture.equals("") || lecture.contains(SEPARATOR_ARRAYS)) {
					continue;
				}
				String[] items = lecture.split(SEPARATOR_ITEMS_ARRAY);
				long idLecture = Long.parseLong(items[0]);
				String nameLecture = items[1];
				lectures.add(new Lecture(idLecture, nameLecture));
			}
			ICourse course = new Course(id, name, description, startDate, endDate);
			course.setLectures(lectures);
			course.setLector(lector);
			courses.add(course);

		}
	}

	public void writeLessonToFile(List<ILesson> pLesson) {
		StringBuilder str = new StringBuilder();
		for (ILesson lesson : pLesson) {
			if (lesson == null) {
				continue;
			}
			str.append(lesson.getId() + SEPARATOR_ATTRIBUTES);
			str.append(lesson.getLecture().getId() + SEPARATOR_ATTRIBUTES);
			str.append(DateWorker.getStringFromDate(lesson.getDate()) + SEPARATOR_OBJECT);

		}
		timetableFile.writeToFile(str.toString().split(SEPARATOR_OBJECT));
	}

	private ILecture getLectureById(long id) {
		for (ICourse course : courses) {
			if (course == null) {
				continue;
			}
			int position = ListWorker.getIndexById(courses, id);
			if (position >= 0) {
				return course.getLectures().get(position);
			}
		}
		return null;
	}

	private void readLessonsFromFile() throws ParseException {
		String[] listObjects = timetableFile.readFromFile();
		for (String object : listObjects) {
			if (object.equals("")) {
				continue;
			}
			String[] attributes = object.split(SEPARATOR_ATTRIBUTES);
			long id = Long.parseLong(attributes[0]);
			long idLecture = Long.parseLong(attributes[1]);
			ILecture lecture = getLectureById(idLecture);
			Date date = DateWorker.createDate(attributes[2]);
			timeTable.add(new Lesson(id, lecture, date));
		}
	}

	public void writeRelationToFile(List<IRelationSC> pRelation) {
		StringBuilder str = new StringBuilder();
		for (IRelationSC relation : pRelation) {
			if (relation == null) {
				continue;
			}
			str.append(relation.getId() + SEPARATOR_ATTRIBUTES);
			str.append(relation.getStudent().getId() + SEPARATOR_ATTRIBUTES);
			str.append(relation.getCourse().getId() + SEPARATOR_OBJECT);

		}
		relationsFile.writeToFile(str.toString().split(SEPARATOR_OBJECT));
	}

	private void readRelationFromFile() {
		String[] listObjects = relationsFile.readFromFile();
		for (String object : listObjects) {
			if (object.equals("")) {
				continue;
			}
			String[] attributes = object.split(SEPARATOR_ATTRIBUTES);
			long id = Long.parseLong(attributes[0]);
			long idStudent = Long.parseLong(attributes[1]);
			long idCourse = Long.parseLong(attributes[2]);
			int positionStudent = ListWorker.getIndexById(students, idStudent);
			int positionCourse = ListWorker.getIndexById(courses, idCourse);
			IStudent student = positionStudent < 0 ? null : students.get(positionStudent);
			ICourse course = positionCourse < 0 ? null : courses.get(positionCourse);
			relations.add(new RelationSC(id, student, course));
			student.getCourses().add(course);
			course.getStudents().add(student);
		}
	}

	public void fillAll() {
		readStudents();
		readLectorsFromFile();
		try {
			readCoursesFromFile();
			readLessonsFromFile();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		readRelationFromFile();
	}

	public void writeStudentToFile(List<IStudent> pStudent) {
		StringBuilder str = new StringBuilder();
		for (IStudent student : pStudent) {
			if (student == null) {
				continue;
			}
			str.append(student.getId() + SEPARATOR_ATTRIBUTES);
			str.append(student.getNameStudent() + SEPARATOR_OBJECT);

		}
		studentsFile.writeToFile(str.toString().split(SEPARATOR_OBJECT));
	}

	private void readStudents() {
		String[] listObjects = studentsFile.readFromFile();
		for (String object : listObjects) {
			if (object.equals("")) {
				continue;
			}
			String[] attributes = object.split(SEPARATOR_ATTRIBUTES);
			long id = Long.parseLong(attributes[0]);
			String name = attributes[1];
			students.add(new Student(id, name));
		}
	}

	public List<IStudent> getStudents() {
		return students;
	}

	public List<ILector> getLectors() {
		return lectors;
	}

	public List<ICourse> getCourses() {
		return courses;
	}

	public List<IRelationSC> getRelations() {
		return relations;
	}

	public List<ILesson> getTimeTable() {
		return timeTable;
	}

}
