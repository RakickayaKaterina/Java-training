package com.senla.rakickaya.courseplanner.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.senla.rakickaya.courseplanner.configuration.Config;


public class GeneratorId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4219431293233240562L;
	private long idCourse;
	private long idLector;
	private long idLecture;
	private long idLesson;
	private long idStudent;

	private String pathSave;
	private static GeneratorId generatorId;

	public static GeneratorId getInstance() {
		if (generatorId == null) {
			Config conf = Config.getInstance();
			generatorId = restore(conf.getPathId());
		}
		return generatorId;
	}

	private GeneratorId() {
		this(0L, 0L, 0L, 0L, 0L);
	}

	private GeneratorId(long idCourse, long idLector, long idLecture, long idLesson, long idStudent) {
		super();
		this.idCourse = idCourse;
		this.idLector = idLector;
		this.idLecture = idLecture;
		this.idLesson = idLesson;
		this.idStudent = idStudent;
	}

	public void saveState() {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(pathSave)))) {
			objectOutputStream.writeObject(this);
		} catch (IOException e) {
			// LOGGER File not found
		}

	}

	private static GeneratorId restore(String path) {
		GeneratorId generatorId = null;
		try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(path)))) {
			generatorId = (GeneratorId) objectInputStream.readObject();
		} catch (ClassNotFoundException | IOException e) {
			
			generatorId = new GeneratorId();
		}
		generatorId.pathSave = path;
		return generatorId;

	}

	public long nextIdCourse() {
		long id =++idCourse;
		saveState();
		return id;
	}

	public long nextIdLector() {
		long id = ++idLector;
		saveState();
		return id;
	}

	public long nextIdLecture() {
		long id = ++idLecture;
		saveState();
		return id;
	}

	public long nextIdLesson() {
		long id = ++idLesson;
		saveState();
		return id;
	}

	public long nextIdStudent() {
		long id =++idStudent;
		saveState();
		return id;
	}

	public long getIdCourse() {
		return idCourse;
	}

	public void setIdCourse(long idCourse) {
		this.idCourse = idCourse;
		saveState();
	}

	public long getIdLector() {
		return idLector;
	}

	public void setIdLector(long idLector) {
		this.idLector = idLector;
		saveState();
	}

	public long getIdLecture() {
		return idLecture;
	}

	public void setIdLecture(long idLecture) {
		this.idLecture = idLecture;
		saveState();
	}

	public long getIdLesson() {
		return idLesson;
	}

	public void setIdLesson(long idLesson) {
		this.idLesson = idLesson;
		saveState();
	}

	public long getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(long idStudent) {
		this.idStudent = idStudent;
		saveState();
	}

}
