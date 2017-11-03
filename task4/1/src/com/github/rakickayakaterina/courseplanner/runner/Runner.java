package com.github.rakickayakaterina.courseplanner.runner;

import java.text.ParseException;

import com.github.rakickayakaterina.courseplanner.beans.Course;
import com.github.rakickayakaterina.courseplanner.beans.Lector;
import com.github.rakickayakaterina.courseplanner.beans.Lecture;
import com.github.rakickayakaterina.courseplanner.beans.Student;
import com.github.rakickayakaterina.courseplanner.facade.CoursePlannerFacade;
import com.github.rakickayakaterina.courseplanner.launcher.Launcher;
import com.github.rakickayakaterina.courseplanner.launcher.PathsToFiles;
import com.github.rakickayakaterina.courseplanner.utils.DateWorker;
import com.github.rakickayakaterina.courseplanner.utils.FillerRepositories;
import com.github.rakickayakaterina.courseplanner.utils.GeneratorId;

public class Runner {
	public static void main(String[] args) throws ParseException {

		FillerRepositories filler = new FillerRepositories(PathsToFiles.STUDENTS_FILE, PathsToFiles.LECTORS_FILE,PathsToFiles.COURSES_FILE, PathsToFiles.RELATIONS_FILE , PathsToFiles.TIME_TABLE_FILE);
		GeneratorId.restore(PathsToFiles.GENERATOR_FILE);
		filler.fillAll();

		Launcher launcher = new Launcher(filler);

		CoursePlannerFacade facade = new CoursePlannerFacade(launcher.getServiceLect(), launcher.getServiceCource(),
				launcher.getServiceTime(), launcher.getServiceStud());

		Student studentRakickaya = new Student(GeneratorId.getIdStudent(), "Rakickaya Kate");
		Student studentLapanya = new Student(GeneratorId.getIdStudent(), "Cat Lapanya");
		Student studentLapanya1 = new Student(GeneratorId.getIdStudent(), "Cat Lapanya1");
		Student studentLapanya2 = new Student(GeneratorId.getIdStudent(), "Cat Lapanya2");
		Student studentLapanya3 = new Student(GeneratorId.getIdStudent(), "Cat Lapanya3");

		Course courseDiscreteMath = new Course(GeneratorId.getIdCourse(), "Discrete Math",
				"This course is very interesting", DateWorker.createDate("01:09:2017"),
				DateWorker.createDate("25:12:2017"), 34);
		Course courseMachineLearning = new Course(GeneratorId.getIdCourse(), "Machine Learning",
				"This course is very difficult", DateWorker.createDate("01:09:2017"),
				DateWorker.createDate("25:12:2017"), 42);

		Lector lectorProsvirnina = new Lector(GeneratorId.getIdLector(), "Prosvirnina Irina Borisovna");
		Lector lectorIvanov = new Lector(GeneratorId.getIdLector(), "Ivanov Ivan Ivanovich");

		Lecture lectureCombinatoric = new Lecture(GeneratorId.getIdLecture(), "Combinatoric");
		Lecture lectureBooleanAlgebra = new Lecture(GeneratorId.getIdLecture(), "Boolean algebra");

		facade.addStudent(studentRakickaya);
		facade.addStudent(studentLapanya);
		facade.addStudent(studentLapanya1);
		facade.addStudent(studentLapanya2);
		facade.addStudent(studentLapanya3);

		facade.addCourse(courseDiscreteMath);
		facade.addCourse(courseMachineLearning);

		facade.addLector(lectorProsvirnina);
		facade.addLector(lectorIvanov);

		facade.addLectorToCourse(lectorProsvirnina.getId(), courseDiscreteMath.getId());
		facade.addLectorToCourse(lectorIvanov.getId(), courseMachineLearning.getId());

		facade.addStudentToCourse(studentRakickaya.getId(), courseDiscreteMath.getId());
		facade.addStudentToCourse(studentRakickaya.getId(), courseMachineLearning.getId());
		facade.addStudentToCourse(studentLapanya1.getId(), courseMachineLearning.getId());
		facade.addStudentToCourse(studentLapanya2.getId(), courseMachineLearning.getId());
		facade.addStudentToCourse(studentLapanya3.getId(), courseMachineLearning.getId());
		facade.addStudentToCourse(studentLapanya.getId(), courseDiscreteMath.getId());

		facade.removeStudentFromCourse(studentLapanya.getId(), courseMachineLearning.getId());

		facade.addLectureToCourse(lectureCombinatoric, courseDiscreteMath.getId());
		facade.addLectureToCourse(lectureBooleanAlgebra, courseDiscreteMath.getId());

		facade.createTimeTableForLecture(lectureCombinatoric.getId(), DateWorker.createDate("31:10:2017"));
		facade.createTimeTableForLecture(lectureBooleanAlgebra.getId(), DateWorker.createDate("31:10:2017"));
		facade.createTimeTableForLecture(lectureBooleanAlgebra.getId(), DateWorker.createDate("01:11:2017"));

		facade.showSortedCoursesByCountStudents();

		facade.showDetailedDescription(courseDiscreteMath.getId());
		facade.showListLessonByDate(DateWorker.createDate("01:11:2017"));
		facade.showSortedCoursesByCountStudents();
		facade.showSortedCoursesByLectorName(DateWorker.createDate("31:08:2017"));
		facade.showTotalCountStudents();
		facade.showSortedCoursesByLectorName();

		facade.showSortedTimeTableByAlphabet();

		facade.showSortedLectorsByCountCourses();
		
		launcher.save();
		GeneratorId.saveState(PathsToFiles.GENERATOR_FILE);
	}
}
