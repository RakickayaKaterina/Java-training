package com.github.rakickayakaterina.courseplanner.utils;

import java.text.ParseException;
import java.util.Date;

import com.danco.training.TextFileWorker;
import com.github.rakickayakaterina.courseplanner.beans.*;

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

	private Student[] students;
	private Lector[] lectors;
	private Course[] courses;
	private RelationSC[] relations;
	private Lesson[] timeTable;

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
		students = new Student[10];
		lectors = new Lector[10];
		courses = new Course[10];
		relations = new RelationSC[10];
		timeTable = new Lesson[10];
	}

	public void writeLectorToFile(Lector[] pLector) {
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
			lectors = (Lector[]) ArrayWorker.addToArray(new Lector(id, name), lectors);
		}
	}

	public void writeCourseToFile(Course[] pCourse) {
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
			int position = ArrayWorker.getPositionById(idLector, lectors);
			Lector lector = idLector < 0 ? null : lectors[position];

			Lecture[] lectures = new Lecture[10];
			String[] str = attributes[6].split("[$]+");
			for (String lecture : str) {
				if (lecture.equals("") || lecture.contains(SEPARATOR_ARRAYS)) {
					continue;
				}
				String[] items = lecture.split(SEPARATOR_ITEMS_ARRAY);
				long idLecture = Long.parseLong(items[0]);
				String nameLecture = items[1];
				lectures = (Lecture[]) ArrayWorker.addToArray(new Lecture(idLecture, nameLecture), lectures);
			}
			Course course = new Course(id, name, description, startDate, endDate, 10);
			course.setLectures(lectures);
			course.setLector(lector);
			courses = (Course[]) ArrayWorker.addToArray(course, courses);

		}
	}

	public void writeLessonToFile(Lesson[] pLesson) {
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
			int position = ArrayWorker.getPositionById(id, course.getLectures());
			if (position >= 0) {
				return course.getLectures()[position];
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
			timeTable = (Lesson[]) ArrayWorker.addToArray(new Lesson(id, lecture, date), timeTable);
		}
	}

	public void writeRelationToFile(RelationSC[] pRelation) {
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
			int positionStudent = ArrayWorker.getPositionById(idStudent, students);
			int positionCourse = ArrayWorker.getPositionById(idCourse, courses);
			Student student = positionStudent < 0 ? null : students[positionStudent];
			Course course = positionCourse < 0 ? null : courses[positionCourse];
			relations = (RelationSC[]) ArrayWorker.addToArray(new RelationSC(id, student, course), relations);
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

	public void writeStudentToFile(Student[] pStudent) {
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
			students = (Student[]) ArrayWorker.addToArray(new Student(id, name), students);
		}
	}

	public Student[] getStudents() {
		return students;
	}

	public Lector[] getLectors() {
		return lectors;
	}

	public Course[] getCourses() {
		return courses;
	}

	public RelationSC[] getRelations() {
		return relations;
	}

	public Lesson[] getTimeTable() {
		return timeTable;
	}

}
