package com.github.rakickayakaterina.courseplanner.launcher;

import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryCourses;
import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryLectors;
import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryRelations;
import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryStudents;
import com.github.rakickayakaterina.courseplanner.api.repositories.ITimeTable;
import com.github.rakickayakaterina.courseplanner.api.services.IServiceCourses;
import com.github.rakickayakaterina.courseplanner.api.services.IServiceLectors;
import com.github.rakickayakaterina.courseplanner.api.services.IServiceStudents;
import com.github.rakickayakaterina.courseplanner.api.services.IServiceTimeTable;
import com.github.rakickayakaterina.courseplanner.beans.Course;
import com.github.rakickayakaterina.courseplanner.beans.Lector;
import com.github.rakickayakaterina.courseplanner.beans.Lesson;
import com.github.rakickayakaterina.courseplanner.beans.RelationSC;
import com.github.rakickayakaterina.courseplanner.beans.Student;
import com.github.rakickayakaterina.courseplanner.repositories.RepositoryCourses;
import com.github.rakickayakaterina.courseplanner.repositories.RepositoryLectors;
import com.github.rakickayakaterina.courseplanner.repositories.RepositoryRelations;
import com.github.rakickayakaterina.courseplanner.repositories.RepositoryStudents;
import com.github.rakickayakaterina.courseplanner.repositories.TimeTable;
import com.github.rakickayakaterina.courseplanner.services.ServiceCourses;
import com.github.rakickayakaterina.courseplanner.services.ServiceLectors;
import com.github.rakickayakaterina.courseplanner.services.ServiceStudents;
import com.github.rakickayakaterina.courseplanner.services.ServiceTimeTable;
import com.github.rakickayakaterina.courseplanner.utils.GeneratorId;
import com.github.rakickayakaterina.courseplanner.utils.FillerRepositories;

public class Launcher {
	private FillerRepositories fillerRepositories;

	private IRepositoryCourses repoCourses;
	private IRepositoryStudents repoStudents;
	private IRepositoryLectors repoLectors;
	private ITimeTable timeTable;
	private IRepositoryRelations repoRelations;

	private IServiceCourses serviceCource;
	private IServiceLectors serviceLect;
	private IServiceStudents serviceStud;
	private IServiceTimeTable serviceTime;

	public Launcher() {
		this(null);
	}

	public Launcher(FillerRepositories parser) {
		this.fillerRepositories = parser;
		GeneratorId.restore("D:\\java\\wspace64\\CoursePlanner\\res\\id.txt");
		loading();

	}

	public IServiceCourses getServiceCource() {
		return serviceCource;
	}

	public IServiceLectors getServiceLect() {
		return serviceLect;
	}

	public IServiceStudents getServiceStud() {
		return serviceStud;
	}

	public IServiceTimeTable getServiceTime() {
		return serviceTime;
	}

	private void fillCourceRepo() {
		Course[] courses = null;
		if (fillerRepositories != null) {
			courses = fillerRepositories.getCourses();
		} else {
			courses = new Course[10];
		}
		repoCourses = new RepositoryCourses(courses);
	}

	private void fillLectorsRepo() {
		Lector[] lectors = null;
		if (fillerRepositories != null) {
			lectors = fillerRepositories.getLectors();
		} else {
			lectors = new Lector[10];
		}
		repoLectors = new RepositoryLectors(lectors);
	}

	private void fillRelationsRepo() {
		RelationSC[] relations = null;
		if (fillerRepositories != null) {
			relations = fillerRepositories.getRelations();
		} else {
			relations = new RelationSC[10];
		}
		repoRelations = new RepositoryRelations(relations);
	}

	private void fillStudentRepo() {
		Student[] students = null;
		if (fillerRepositories != null) {
			students = fillerRepositories.getStudents();
		} else {
			students = new Student[10];
		}
		repoStudents = new RepositoryStudents(students);
	}

	private void fillTimeTable() {
		Lesson[] lessons = null;
		if (fillerRepositories != null) {
			lessons = fillerRepositories.getTimeTable();
		} else {
			lessons = new Lesson[10];
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

		serviceStud = new ServiceStudents(repoStudents, repoCourses, repoRelations);
		serviceLect = new ServiceLectors(repoLectors, repoCourses);
		serviceTime = new ServiceTimeTable(timeTable, repoCourses);
		serviceCource = new ServiceCourses(repoCourses, repoStudents, repoRelations, repoLectors);

	}

	public void loading() {
		fillingRepoes();
		buildManagers();
	}

	public void save() {
		fillerRepositories.writeStudentToFile(repoStudents.getListStudent());
		fillerRepositories.writeLectorToFile(repoLectors.getListLectors());
		fillerRepositories.writeCourseToFile(repoCourses.getListCourse());
		fillerRepositories.writeRelationToFile(repoRelations.getListRelations());
		fillerRepositories.writeLessonToFile(timeTable.getListLesson());
		GeneratorId.saveState("D:\\java\\wspace64\\CoursePlanner\\res\\id.txt");
	}

}
