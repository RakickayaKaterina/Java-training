import java.text.ParseException;

import facade.CoursePlanningFacade;
import launcher.Launcher;
import models.Course;
import models.Lector;
import models.Lecture;
import models.Student;
import utils.DateWorker;
import utils.GeneratorId;

public class Test {

	public static void main(String[] args) throws ParseException {
		Launcher launcher = new Launcher();
		CoursePlanningFacade facade = new CoursePlanningFacade(launcher.getServiceLect(), launcher.getServiceCource(), launcher.getServiceTime(), launcher.getServiceStud());
		
		Student studentRakickaya = new Student(GeneratorId.getIdStudent(), "Rakickaya Kate");
		Student studentLapanya = new Student(GeneratorId.getIdStudent(), "Cat Lapanya");
		
		Course courseDiscreteMath = new Course(GeneratorId.getIdCourse(), "Discrete Math", "This course is very interesting",DateWorker.createDate("01:09:2017"), DateWorker.createDate("25:12:2017"), 34);
		Course courseMachineLearning = new Course(GeneratorId.getIdCourse(), "Machine Learning", "This course is very difficult", DateWorker.createDate("01:09:2017"), DateWorker.createDate("25:12:2017"),42);		
		
		Lector lectorProsvirnina = new Lector(GeneratorId.getIdLector(), "Prosvirnina Irina Borisovna");
		Lector lectorIvanov = new Lector(GeneratorId.getIdLector(), "Ivanov Ivan Ivanovich");
				
		Lecture lectureCombinatoric = new Lecture(GeneratorId.getIdLecture(), "Combinatoric");
		Lecture lectureBooleanAlgebra = new Lecture(GeneratorId.getIdLecture(), "Boolean algebra");
		
		
		facade.addStudent(studentRakickaya);
		facade.addStudent(studentLapanya);
		
		facade.addCourse(courseDiscreteMath);
		facade.addCourse(courseMachineLearning);
	
		facade.addLector(lectorProsvirnina);
		facade.addLector(lectorIvanov);
	
		facade.addLectorToCourse(lectorProsvirnina.getId(), courseDiscreteMath.getId());
		facade.addLectorToCourse(lectorIvanov.getId(), courseMachineLearning.getId());
		
		facade.addStudentToCourse(studentRakickaya.getId(), courseDiscreteMath.getId());
		facade.addStudentToCourse(studentRakickaya.getId(), courseMachineLearning.getId());
		
		facade.addStudentToCourse(studentLapanya.getId(), courseDiscreteMath.getId());
		
		facade.addLectureToCourse(lectureCombinatoric, courseDiscreteMath.getId());
		facade.addLectureToCourse(lectureBooleanAlgebra, courseDiscreteMath.getId());
	
		facade.showDetailedDescription(courseDiscreteMath.getId());
		facade.showListLessonByDate(DateWorker.createDate("12:10:2017"));
		facade.showSortedCoursesByCountStudents();
		facade.showSortedCoursesByLectorName(DateWorker.createDate("12:10:2017"));
		facade.showTotalCountStudents();
		facade.showSortedCoursesByLectorName();
	}

}
