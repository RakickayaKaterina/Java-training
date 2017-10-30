package utils;

public class GeneratorId {
	private static long idCourse;
	private static long idLector;
	private static long idLecture;
	private static long idLesson;
	private static long idStudent;
	
	static{
		
		//TODO read from file
		
		idCourse = 0;
		idLector = 0;
		idLecture = 0;
		idLesson = 0;
		idStudent = 0;
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
	
	
}
