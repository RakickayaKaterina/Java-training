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
	private long idRelation;

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
		this(0L, 0L, 0L, 0L, 0L, 0L);
	}

	private GeneratorId(long idCourse, long idLector, long idLecture, long idLesson, long idStudent, long idRelation) {
		super();
		this.idCourse = idCourse;
		this.idLector = idLector;
		this.idLecture = idLecture;
		this.idLesson = idLesson;
		this.idStudent = idStudent;
		this.idRelation = idRelation;
	}

	public void saveState() throws IOException {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(pathSave)))) {
			objectOutputStream.writeObject(this);
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

	public long getIdCourse() {
		return ++idCourse;
	}

	public long getIdLector() {
		return ++idLector;
	}

	public long getIdLecture() {
		return ++idLecture;
	}

	public long getIdLesson() {
		return ++idLesson;
	}

	public long getIdStudent() {
		return ++idStudent;
	}

	public long getIdRelation() {
		return ++idRelation;
	}

}
