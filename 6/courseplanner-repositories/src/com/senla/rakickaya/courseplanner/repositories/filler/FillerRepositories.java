package com.senla.rakickaya.courseplanner.repositories.filler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.api.beans.IRelationSC;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.utils.ListWorker;
import com.senla.rakickaya.courseplanner.utils.config.PathsToFiles;

public class FillerRepositories {

	private String pathStudentFile;
	private String pathLectorsFile;
	private String pathCoursesFile;
	private String pathRelationsFile;
	private String pathTimeTableFile;

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
		this.pathStudentFile = pathStudentFile;
		this.pathLectorsFile = pathLectorsFile;
		this.pathCoursesFile = pathCoursesFile;
		this.pathRelationsFile = pathRelationsFile;
		this.pathTimeTableFile = pathTimeTableFile;
		fillAll();
	}

	private void init() {
		students = new ArrayList<>();
		lectors = new ArrayList<>();
		courses = new ArrayList<>();
		relations = new ArrayList<>();
		timeTable = new ArrayList<>();
	}

	public void writeLectorToFile(List<ILector> pLector) throws IOException {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(new File(pathLectorsFile)))) {
			objectOutputStream.writeObject(pLector);
		}
	}

	private void readLectorsFromFile() throws IOException, ClassNotFoundException {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(new File(pathLectorsFile)))) {
			lectors = (List<ILector>) objectInputStream.readObject();
		}
	}

	public void writeCourseToFile(List<ICourse> pCourse) throws IOException {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(new File(pathCoursesFile)))) {
			objectOutputStream.writeObject(pCourse);
		}
	}

	private void readCoursesFromFile() throws ClassNotFoundException, IOException {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(new File(pathCoursesFile)))) {
			courses = (List<ICourse>) objectInputStream.readObject();
		}
	}

	public void writeLessonToFile(List<ILesson> pLesson) throws IOException {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(new File(pathTimeTableFile)))) {
			objectOutputStream.writeObject(pLesson);
		}
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

	private void readLessonsFromFile() throws ClassNotFoundException, IOException {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(new File(pathTimeTableFile)))) {
			timeTable = (List<ILesson>) objectInputStream.readObject();
		}
	}

	public void writeRelationToFile(List<IRelationSC> pRelation) throws IOException {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(new File(pathRelationsFile)))) {
			objectOutputStream.writeObject(pRelation);
		}
	}

	private void readRelationFromFile() throws ClassNotFoundException, IOException {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(new File(pathRelationsFile)))) {
			relations = (List<IRelationSC>) objectInputStream.readObject();
		}
	}

	public void fillAll() {
		try {
			readStudents();
			readLectorsFromFile();
			readCoursesFromFile();
			readLessonsFromFile();
			readRelationFromFile();
		} catch (ClassNotFoundException | IOException e) {
			// TODO logger
			e.printStackTrace();
		}

	}

	public void writeStudentToFile(List<IStudent> pStudent) throws IOException {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(new File(pathStudentFile)))) {
			objectOutputStream.writeObject(pStudent);
		}
	}

	private void readStudents() throws ClassNotFoundException, IOException {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(new File(pathStudentFile)))) {
			students = (List<IStudent>) objectInputStream.readObject();
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
