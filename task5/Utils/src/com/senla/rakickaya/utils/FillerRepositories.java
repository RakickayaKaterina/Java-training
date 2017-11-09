package com.senla.rakickaya.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.rakickaya.model.beans.Course;
import com.senla.rakickaya.model.beans.Lector;
import com.senla.rakickaya.model.beans.Lecture;
import com.senla.rakickaya.model.beans.Lesson;
import com.senla.rakickaya.model.beans.RelationSC;
import com.senla.rakickaya.model.beans.Student;

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

	private List<Student> students;
	private List<Lector> lectors;
	private List<Course> courses;
	private List<RelationSC> relations;
	private List<Lesson> timeTable;

	public FillerRepositories(String pathStudentFile, String pathLectorsFile, String pathCoursesFile, String pathRelationsFile,
			String pathTimeTableFile) {
		init();
		studentsFile = new TextFileWorker(pathStudentFile);
		lectorsFile = new TextFileWorker(pathLectorsFile);
		coursesFile = new TextFileWorker(pathCoursesFile);
		relationsFile = new TextFileWorker(pathRelationsFile);
		timetableFile = new TextFileWorker(pathTimeTableFile);
	}

	private void init() {
		students = new ArrayList<>();
		lectors = new ArrayList<>();
		courses = new ArrayList<>();
		relations = new ArrayList<>();
		timeTable = new ArrayList<>();
	}

	public void writeLectorToFile(List<Lector> pLector) {
		StringBuilder str = new StringBuilder();
		for (Lector lector : pLector) {
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
			lectors.add(new Lector(id,name));
		}
	}

	public void writeCourseToFile(List<Course> pCourse) {
		StringBuilder str = new StringBuilder();
		for (Course course : pCourse) {
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
			for (Lecture lecture : course.getLectures()) {
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
			Lector lector = idLector < 0 ? null : lectors.get(position);

			List<Lecture> lectures = new ArrayList<>();
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
			Course course = new Course(id, name, description, startDate, endDate);
			course.setLectures(lectures);
			course.setLector(lector);
			courses.add(course);

		}
	}

	public void writeLessonToFile(List<Lesson> pLesson) {
		StringBuilder str = new StringBuilder();
		for (Lesson lesson : pLesson) {
			if (lesson == null) {
				continue;
			}
			str.append(lesson.getId() + SEPARATOR_ATTRIBUTES);
			str.append(lesson.getLecture().getId() + SEPARATOR_ATTRIBUTES);
			str.append(DateWorker.getStringFromDate(lesson.getDate()) + SEPARATOR_OBJECT);

		}
		timetableFile.writeToFile(str.toString().split(SEPARATOR_OBJECT));
	}

	private Lecture getLectureById(long id) {
		for (Course course : courses) {
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
			Lecture lecture = getLectureById(idLecture);
			Date date = DateWorker.createDate(attributes[2]);
			timeTable.add(new Lesson(id, lecture, date));
		}
	}

	public void writeRelationToFile(List<RelationSC> pRelation) {
		StringBuilder str = new StringBuilder();
		for (RelationSC relation : pRelation) {
			if (relation == null) {
				continue;
			}
			str.append(relation.getId() + SEPARATOR_ATTRIBUTES);
			str.append(relation.getStudent().getId() + SEPARATOR_ATTRIBUTES);
			str.append(relation.getCourse().getId() + SEPARATOR_OBJECT);

		}
		relationsFile.writeToFile(str.toString().split(SEPARATOR_OBJECT));
	}

	private void readRelationFromFile() throws ParseException {
		String[] listObjects = relationsFile.readFromFile();
		for (String object : listObjects) {
			if (object.equals("")) {
				continue;
			}
			String[] attributes = object.split(SEPARATOR_ATTRIBUTES);
			long id = Long.parseLong(attributes[0]);
			long idStudent = Long.parseLong(attributes[1]);
			long idCourse = Long.parseLong(attributes[2]);
			int positionStudent =ListWorker.getIndexById(students, idStudent);
			int positionCourse = ListWorker.getIndexById(courses, idCourse);
			Student student = positionStudent < 0 ? null : students.get(positionStudent);
			Course course = positionCourse < 0 ? null : courses.get(positionCourse);
			relations.add(new RelationSC(id, student, course));
			student.addCourse(course);
			course.addStudent(student);
		}
	}

	public void fillAll() throws ParseException {
		readStudents();
		readLectorsFromFile();
		readCoursesFromFile();
		readLessonsFromFile();
		readRelationFromFile();
	}

	public void writeStudentToFile(List<Student> pStudent) {
		StringBuilder str = new StringBuilder();
		for (Student student : pStudent) {
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

	public List<Student> getStudents() {
		return students;
	}

	public List<Lector> getLectors() {
		return lectors;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public List<RelationSC> getRelations() {
		return relations;
	}

	public List<Lesson> getTimeTable() {
		return timeTable;
	}

}
