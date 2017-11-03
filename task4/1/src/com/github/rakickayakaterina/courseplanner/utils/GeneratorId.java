package com.github.rakickayakaterina.courseplanner.utils;

import com.danco.training.TextFileWorker;

public class GeneratorId {
	
	private static final String SEPARATOR="::";
	
	private static long idCourse;
	private static long idLector;
	private static long idLecture;
	private static long idLesson;
	private static long idStudent;
	private static long idRelation;

	
	public static void saveState(String path) {
		TextFileWorker textFileWorker = new TextFileWorker(path);
		StringBuilder str = new StringBuilder();
		str.append(idCourse+SEPARATOR);
		str.append(idLector+SEPARATOR);
		str.append(idLecture+SEPARATOR);
		str.append(idLesson+SEPARATOR);
		str.append(idStudent+SEPARATOR);
		str.append(idRelation+SEPARATOR);
		textFileWorker.writeToFile(str.toString().split(SEPARATOR));
		
	}
	public static void restore(String path){
		TextFileWorker textFileWorker = new TextFileWorker(path);
		String[] objects = textFileWorker.readFromFile();
		idCourse = Long.parseLong(objects[0]);
		idLector = Long.parseLong(objects[1]);
		idLecture = Long.parseLong(objects[2]);
		idLesson = Long.parseLong(objects[3]);
		idStudent = Long.parseLong(objects[4]);
		idRelation = Long.parseLong(objects[5]);
	}

	public static long getIdCourse() {
		return ++idCourse;
	}

	public static long getIdLector() {
		return ++idLector;
	}

	public static long getIdLecture() {
		return ++idLecture;
	}

	public static long getIdLesson() {
		return ++idLesson;
	}

	public static long getIdStudent() {
		return ++idStudent;
	}

	public static long getIdRelation() {
		return ++idRelation;
	}

}
