package launcher;

import models.*;
import repositories.IRepositoryCourses;
import repositories.IRepositoryLectors;
import repositories.IRepositoryStudents;
import repositories.ITimeTable;
import repositories.RepositoryCourses;
import repositories.RepositoryLectors;
import repositories.RepositoryStudents;
import repositories.TimeTable;
import services.*;

public class Launcher {
		private IRepositoryCourses repoCourses;
		private IRepositoryStudents repoStudents;
		private IRepositoryLectors repoLectors;
		private ITimeTable timeTable;
		
		private ServiceCourses serviceCource;
		private ServiceLectors serviceLect;
		private ServiceStudent serviceStud;
		private ServiceTimeTable serviceTime;
		
		public Launcher() {
			loading();
		}

		

		public IServiceCourses getServiceCource() {
			return serviceCource;
		}



		public IServiceLectors getServiceLect() {
			return serviceLect;
		}



		public IServiceStudent getServiceStud() {
			return serviceStud;
		}



		public IServiceTimeTable getServiceTime() {
			return serviceTime;
		}



		private void fillCourceRepo() {
			// TODO read from file
			Course[] courcesList = new Course[10];
			repoCourses = new RepositoryCourses(courcesList);
		}

		private void fillLectorsRepo() {
			// TODO read from file
			Lector[] lectorsList = new Lector[10];
			repoLectors = new RepositoryLectors(lectorsList);
		}


		private void fillStudentRepo() {
			// TODO read from file
			Student[] studentsList = new Student[10];
			repoStudents = new RepositoryStudents(studentsList);
		}
		
		private void fillTimeTable() {
			// TODO read from file
			Lesson[] lessonsList = new Lesson[10];
			timeTable = new TimeTable(lessonsList);
		}

		private void fillingRepoes() {
			fillStudentRepo();
			fillCourceRepo();
			fillLectorsRepo();
			fillTimeTable();
		}

		private void buildManagers() {

			serviceStud = new ServiceStudent(repoStudents);
			serviceLect = new ServiceLectors(repoLectors);
			serviceTime = new ServiceTimeTable(timeTable);
			serviceCource = new ServiceCourses(repoCourses);
			
			serviceCource.setServiceLectors(serviceLect);
			serviceCource.setServiceStudents(serviceStud);
			serviceCource.setServiceTimeTable(serviceTime);
			
			serviceTime.setServiceCourses(serviceCource);

		}

		public void loading() {
			fillingRepoes();
			buildManagers();
		}

		public void save() {
			// TODO saveToFile
		}

	}
