package com.senla.rakickaya.courseplanner.repositories.filler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.configuration.Config;

public class FillerRepositories {

	private String pathStudentFile;
	private String pathLectorsFile;
	private String pathCoursesFile;
	private String pathTimeTableFile;

	private List<IStudent> students;
	private List<ILector> lectors;
	private List<ICourse> courses;
	private List<ILesson> timeTable;

	private static FillerRepositories fillerRepositories;

	private static final Logger logger = Logger.getLogger(FillerRepositories.class.getName());

	public static FillerRepositories getInstance() {
		if (fillerRepositories == null) {
			Config conf = Config.getInstance();
			fillerRepositories = new FillerRepositories(conf.getPathStudent(), conf.getPathLector(),
					conf.getPathCourse(), conf.getPathTimeTable());
		}
		return fillerRepositories;
	}

	private FillerRepositories(String pathStudentFile, String pathLectorsFile, String pathCoursesFile,
			String pathTimeTableFile) {
		init();
		this.pathStudentFile = pathStudentFile;
		this.pathLectorsFile = pathLectorsFile;
		this.pathCoursesFile = pathCoursesFile;
		this.pathTimeTableFile = pathTimeTableFile;
		fillAll();
	}

	private void init() {
		students = new ArrayList<>();
		lectors = new ArrayList<>();
		courses = new ArrayList<>();
		timeTable = new ArrayList<>();
	}

	public void writeLectorToFile(List<ILector> pLector) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(new File(pathLectorsFile)))) {
			objectOutputStream.writeObject(pLector);
		} catch (IOException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private void readLectorsFromFile() {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(new File(pathLectorsFile)))) {
			lectors = (List<ILector>) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		} catch (IOException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
	}

	public void writeCourseToFile(List<ICourse> pCourse) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(new File(pathCoursesFile)))) {
			objectOutputStream.writeObject(pCourse);
		} catch (IOException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private void readCoursesFromFile() {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(new File(pathCoursesFile)))) {
			courses = (List<ICourse>) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		} catch (IOException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
	}

	public void writeLessonToFile(List<ILesson> pLesson) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(new File(pathTimeTableFile)))) {
			objectOutputStream.writeObject(pLesson);
		} catch (IOException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private void readLessonsFromFile() {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(new File(pathTimeTableFile)))) {
			timeTable = (List<ILesson>) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		} catch (IOException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
	}

	public void writeStudentToFile(List<IStudent> pStudent) {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				new FileOutputStream(new File(pathStudentFile)))) {
			objectOutputStream.writeObject(pStudent);
		} catch (IOException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private void readStudents() {
		try (ObjectInputStream objectInputStream = new ObjectInputStream(
				new FileInputStream(new File(pathStudentFile)))) {
			students = (List<IStudent>) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			logger.error(new Date() + " " + e.getMessage());
		} catch (IOException e) {
			logger.error(new Date() + " " + e.getMessage());
		}
	}

	public void fillAll() {
		readStudents();
		readLectorsFromFile();
		readCoursesFromFile();
		readLessonsFromFile();
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

	public List<ILesson> getTimeTable() {
		return timeTable;
	}

}
