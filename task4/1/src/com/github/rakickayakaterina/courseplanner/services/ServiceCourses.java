package com.github.rakickayakaterina.courseplanner.services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryCourses;
import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryLectors;
import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryRelations;
import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryStudents;
import com.github.rakickayakaterina.courseplanner.api.services.IServiceCourses;
import com.github.rakickayakaterina.courseplanner.beans.Course;
import com.github.rakickayakaterina.courseplanner.beans.Lector;
import com.github.rakickayakaterina.courseplanner.beans.Lecture;
import com.github.rakickayakaterina.courseplanner.beans.RelationSC;
import com.github.rakickayakaterina.courseplanner.beans.Student;
import com.github.rakickayakaterina.courseplanner.utils.ArrayWorker;
import com.github.rakickayakaterina.courseplanner.utils.DateWorker;
import com.github.rakickayakaterina.courseplanner.utils.GeneratorId;

public class ServiceCourses implements IServiceCourses {
	private final IRepositoryCourses mRepositoryCourses;
	private final IRepositoryStudents mRepositoryStudents;
	private final IRepositoryRelations mRepositoryRelations;
	private final IRepositoryLectors mRepositoryLectors;

	public ServiceCourses(IRepositoryCourses mRepositoryCourses, IRepositoryStudents mRepositoryStudents,
			IRepositoryRelations mRepositoryRelations, IRepositoryLectors mRepositoryLectors) {
		super();
		this.mRepositoryCourses = mRepositoryCourses;
		this.mRepositoryStudents = mRepositoryStudents;
		this.mRepositoryRelations = mRepositoryRelations;
		this.mRepositoryLectors = mRepositoryLectors;
	}

	@Override
	public void addCourse(Course pCourse) {
		mRepositoryCourses.addCourse(pCourse);

	}

	@Override
	public void removeCourse(long pId) {
		mRepositoryCourses.removeCourse(pId);
		Student[] students = mRepositoryStudents.getListStudent();
		for (int i = 0; i < ArrayWorker.getLenghtArray(students); i++) {
			if (students[i] != null)
				ArrayWorker.removeFromArray(pId, students[i].getCourses());
		}
		RelationSC[] relations = mRepositoryRelations.getListRelations();
		for (int i = 0; i < ArrayWorker.getLenghtArray(relations); i++) {
			if (relations[i] != null && relations[i].getCourse() != null && relations[i].getCourse().getId() == pId) {
				mRepositoryRelations.removeRelation(relations[i].getId());
			}
		}

	}

	@Override
	public void updateCourse(Course pCourse) {
		mRepositoryCourses.updateCourse(pCourse);

	}

	@Override
	public Course getCourse(long pId) {
		return mRepositoryCourses.getCourse(pId);
	}

	@Override
	public Course[] getListCourses() {
		return mRepositoryCourses.getListCourse();
	}

	@Override
	public void addStudentToCourse(long pIdStudent, long pIdCourse) {
		Course course = mRepositoryCourses.getCourse(pIdCourse);
		Student student = mRepositoryStudents.getStudent(pIdStudent);
		course.addStudent(student);
		student.addCourse(course);
		mRepositoryRelations.addRelation(new RelationSC(GeneratorId.getIdRelation(), student, course));
	}

	@Override
	public void removeStudentFromCourse(long pIdStudent, long pIdCourse) {
		Course course = mRepositoryCourses.getCourse(pIdCourse);
		if (course != null) {
			Student[] students = course.getStudents();
			for (int i = 0; i < ArrayWorker.getLenghtArray(students); i++) {
				if (students[i] != null && students[i].getId() == pIdStudent) {
					students[i] = null;
				}
			}
		}
		RelationSC[] relations = mRepositoryRelations.getListRelations();
		for (int i = 0; i < ArrayWorker.getLenghtArray(relations); i++) {
			if (relations[i] != null && relations[i].getStudent() != null
					&& relations[i].getStudent().getId() == pIdStudent) {
				mRepositoryRelations.removeRelation(relations[i].getId());
			}
		}

	}

	@Override
	public void addLectorToCourse(long pIdLector, long pIdCourse) {
		Course course = mRepositoryCourses.getCourse(pIdCourse);
		Lector lector = mRepositoryLectors.getLector(pIdLector);
		course.setLector(lector);

	}

	@Override
	public void removeLectorFromCourse(long pIdLector, long pIdCourse) {
		Course course = mRepositoryCourses.getCourse(pIdCourse);
		if (course != null) {
			Lector lector = course.getLector();
			if (lector != null && lector.getId() == pIdLector) {
				course.setLector(null);
			}
		}
	}

	@Override
	public void addLectureToCourse(Lecture lecture, long pIdCourse) {
		Course course = mRepositoryCourses.getCourse(pIdCourse);
		if (course != null) {
			course.addLecture(lecture);
		}

	}

	@Override
	public void removeLectureFromCourse(long pIdLecture, long pIdCourse) {
		Course course = mRepositoryCourses.getCourse(pIdCourse);
		if (course != null) {
			Lecture[] lectures = course.getLectures();
			for (int i = 0; i < ArrayWorker.getLenghtArray(lectures); i++) {
				if (lectures[i] != null && lectures[i].getId() == pIdLecture) {
					lectures[i] = null;
				}
			}
		}

	}

	@Override
	public Course[] getSortedList(Comparator<Course> mComparator) {
		Course[] listCourses = mRepositoryCourses.getListCourse();
		Arrays.sort(listCourses, mComparator);
		return listCourses;
	}

	@Override
	public String getDetailDescription(long pIdCourse) {
		Course course = mRepositoryCourses.getCourse(pIdCourse);
		if (course == null)
			return "This course not exist";
		String detailedDescription = String.format("Detailed description:\ndescription=%s\nlector=%s\n",
				course.getDescription(), course.getLector().getName());

		StringBuilder builder = new StringBuilder(detailedDescription);
		builder.append("Students:\n");
		for (Student student : course.getStudents()) {
			if (student != null) {
				builder.append(student.getNameStudent() + "\n");
			}
		}
		return builder.toString();

	}

	@Override
	public Course[] getSortedListCoursesAfterDate(Date pDate, Comparator<Course> pComparator) {
		Course[] resultList = new Course[10];
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			if (courses[i] != null && DateWorker.isAfterDate(pDate, courses[i].getStartDate())) {
				resultList = (Course[]) ArrayWorker.addToArray(courses[i], resultList);
			}
		}
		Arrays.sort(resultList, pComparator);
		return resultList;
	}

	@Override
	public Course[] getSortedListCurrentCourses(Date pCurrentDate, Comparator<Course> pComparator) {
		Course[] resultList = new Course[10];
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			if (courses[i] != null
					&& DateWorker.isBetweenDate(pCurrentDate, courses[i].getStartDate(), courses[i].getEndDate())) {
				resultList = (Course[]) ArrayWorker.addToArray(courses[i], resultList);
			}
		}
		Arrays.sort(resultList, pComparator);
		return resultList;
	}

	@Override
	public int getTotalCountCourses() {
		return ArrayWorker.getLenghtArray(mRepositoryCourses.getListCourse());
	}

	@Override
	public Course[] getPastCourses(Date startDateSub, Date endDateSub) {
		Course[] resultList = new Course[5];
		Course[] courses = mRepositoryCourses.getListCourse();
		for (int i = 0; i < ArrayWorker.getLenghtArray(courses); i++) {
			if (courses[i] != null && DateWorker.isSubInterval(courses[i].getStartDate(), courses[i].getEndDate(),
					startDateSub, endDateSub)) {
				resultList = (Course[]) ArrayWorker.addToArray(courses[i], resultList);
			}
		}
		return resultList;
	}

}
