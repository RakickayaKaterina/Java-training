package com.senla.rakickaya.utils;

import com.danco.training.TextFileWorker;
import com.senla.rakickaya.utils.config.PathsToFiles;

public class GeneratorId {
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
			generatorId = restore(PathsToFiles.GENERATOR_FILE);
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

	public void saveState() {
		if (pathSave != null) {
			TextFileWorker textFileWorker = new TextFileWorker(pathSave);
			textFileWorker.writeToFile(
					new String[] { String.valueOf(idCourse), String.valueOf(idLector), String.valueOf(idLecture),
							String.valueOf(idLesson), String.valueOf(idStudent), String.valueOf(idRelation) });
		}

	}

	private static GeneratorId restore(String path) {
		try {

			TextFileWorker textFileWorker = new TextFileWorker(path);
			String[] objects = textFileWorker.readFromFile();
			long idCourse = Long.parseLong(objects[0]);
			long idLector = Long.parseLong(objects[1]);
			long idLecture = Long.parseLong(objects[2]);
			long idLesson = Long.parseLong(objects[3]);
			long idStudent = Long.parseLong(objects[4]);
			long idRelation = Long.parseLong(objects[5]);
			GeneratorId generatorId = new GeneratorId(idCourse, idLector, idLecture, idLesson, idStudent, idRelation);
			generatorId.pathSave = path;
			return generatorId;

		} catch (Exception e) {
			return new GeneratorId();
		}
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
