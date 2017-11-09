package com.senla.rakickaya.utils.launcher;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.controller.api.repositories.*;
import com.senla.rakickaya.controller.api.services.*;
import com.senla.rakickaya.controller.repositories.CoursesRepository;
import com.senla.rakickaya.controller.repositories.LectorsRepository;
import com.senla.rakickaya.controller.repositories.RelationsRepository;
import com.senla.rakickaya.controller.repositories.StudentsRepository;
import com.senla.rakickaya.controller.repositories.TimeTable;
import com.senla.rakickaya.controller.services.CoursesService;
import com.senla.rakickaya.controller.services.LectorsService;
import com.senla.rakickaya.controller.services.StudentsService;
import com.senla.rakickaya.controller.services.TimeTableService;
import com.senla.rakickaya.model.beans.Course;
import com.senla.rakickaya.model.beans.Lector;
import com.senla.rakickaya.model.beans.Lesson;
import com.senla.rakickaya.model.beans.RelationSC;
import com.senla.rakickaya.model.beans.Student;
import com.senla.rakickaya.utils.FillerRepositories;
import com.senla.rakickaya.utils.GeneratorId;

public class Launcher {
	//TODO 
	private static Launcher launcher;
	public static Launcher getInstance(){
		if(launcher == null){
			launcher = new Launcher(new FillerRepositories(PathsToFiles.STUDENTS_FILE, PathsToFiles.LECTORS_FILE, PathsToFiles.COURSES_FILE, PathsToFiles.RELATIONS_FILE, PathsToFiles.TIME_TABLE_FILE),PathsToFiles.GENERATOR_FILE);
		}
		return launcher;
	}
	private FillerRepositories fillerRepositories;
	private GeneratorId generatorId;

	private ICoursesRepository repoCourses;
	private IStudentsRepository repoStudents;
	private ILectorsRepository repoLectors;
	private ITimeTable timeTable;
	private IRelationsRepository repoRelations;

	private ICoursesService serviceCource;
	private ILectorsService serviceLect;
	private IStudentsService serviceStud;
	private ITimeTableService serviceTime;

	public Launcher(FillerRepositories parser, String pathToId) {
		this.fillerRepositories = parser;
		generatorId = GeneratorId.restore(pathToId);
		loading();

	}

	public ICoursesService getServiceCource() {
		return serviceCource;
	}

	public GeneratorId getGeneratorId() {
		return generatorId;
	}

	public ILectorsService getServiceLect() {
		return serviceLect;
	}

	public IStudentsService getServiceStud() {
		return serviceStud;
	}

	public ITimeTableService getServiceTime() {
		return serviceTime;
	}

	private void fillCourceRepo() {
		List<Course> courses; 
		if (fillerRepositories != null) {
			courses = fillerRepositories.getCourses();
		} else {
			courses = new ArrayList<>();
		}
		repoCourses = new CoursesRepository(courses);
	}

	private void fillLectorsRepo() {
		List<Lector> lectors;
		if (fillerRepositories != null) {
			lectors = fillerRepositories.getLectors();
		} else {
			lectors = new ArrayList<>();
		}
		repoLectors = new LectorsRepository(lectors);
	}

	private void fillRelationsRepo() {
		List<RelationSC> relations = null;
		if (fillerRepositories != null) {
			relations = fillerRepositories.getRelations();
		} else {
			relations = new ArrayList<>();
		}
		repoRelations = new RelationsRepository(relations);
	}

	private void fillStudentRepo() {
		List<Student> students = null;
		if (fillerRepositories != null) {
			students = fillerRepositories.getStudents();
		} else {
			students = new ArrayList<>();
		}
		repoStudents = new StudentsRepository(students);
	}

	private void fillTimeTable() {
		List<Lesson> lessons = null;
		if (fillerRepositories != null) {
			lessons = fillerRepositories.getTimeTable();
		} else {
			lessons = new ArrayList<>();
		}
		timeTable = new TimeTable(lessons);
	}

	private void fillingRepoes() {
		fillStudentRepo();
		fillCourceRepo();
		fillLectorsRepo();
		fillTimeTable();
		fillRelationsRepo();
	}

	private void buildManagers() {

		serviceStud = new StudentsService(repoStudents, repoCourses, repoRelations);
		serviceLect = new LectorsService(repoLectors, repoCourses);
		serviceTime = new TimeTableService(timeTable, repoCourses);
		serviceCource = new CoursesService(repoCourses, repoStudents, repoRelations, repoLectors);

	}

	public void loading() {
		fillingRepoes();
		buildManagers();
	}

	public void save() {
		fillerRepositories.writeStudentToFile(repoStudents.getListStudents());
		fillerRepositories.writeLectorToFile(repoLectors.getListLectors());
		fillerRepositories.writeCourseToFile(repoCourses.getListCourses());
		fillerRepositories.writeRelationToFile(repoRelations.getListRelations());
		fillerRepositories.writeLessonToFile(timeTable.getListLessons());
		generatorId.saveState();
	}

}
