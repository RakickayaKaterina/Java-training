package com.senla.rakickaya.courseplanner.ui;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.data_exchange.IRequest;
import com.senla.rakickaya.courseplanner.api.data_exchange.IResponse;
import com.senla.rakickaya.courseplanner.ui.actions.ExitAction;
import com.senla.rakickaya.courseplanner.ui.actions.PrinterInfo;
import com.senla.rakickaya.courseplanner.ui.actions.PrinterInfoParam;
import com.senla.rakickaya.courseplanner.ui.actions.courses.CourseAddition;
import com.senla.rakickaya.courseplanner.ui.actions.courses.CourseRemoval;
import com.senla.rakickaya.courseplanner.ui.actions.courses.DetailedDescription;
import com.senla.rakickaya.courseplanner.ui.actions.courses.LectorAdditionToCourse;
import com.senla.rakickaya.courseplanner.ui.actions.courses.LectorRemovalFromCourse;
import com.senla.rakickaya.courseplanner.ui.actions.courses.LectureAddition;
import com.senla.rakickaya.courseplanner.ui.actions.courses.LectureRemoval;
import com.senla.rakickaya.courseplanner.ui.actions.courses.StudentAdditionToCourse;
import com.senla.rakickaya.courseplanner.ui.actions.courses.StudentRemovalFromCourse;
import com.senla.rakickaya.courseplanner.ui.actions.courses.TotalCountCourses;
import com.senla.rakickaya.courseplanner.ui.actions.lectors.LectorAddition;
import com.senla.rakickaya.courseplanner.ui.actions.lectors.LectorRemoval;
import com.senla.rakickaya.courseplanner.ui.actions.lectors.SortLectorsByCountCourse;
import com.senla.rakickaya.courseplanner.ui.actions.lectors.TotalCountLectors;
import com.senla.rakickaya.courseplanner.ui.actions.students.StudentAddition;
import com.senla.rakickaya.courseplanner.ui.actions.students.StudentRemoval;
import com.senla.rakickaya.courseplanner.ui.actions.students.TotalCountSt;
import com.senla.rakickaya.courseplanner.ui.actions.timetable.LessonAddition;
import com.senla.rakickaya.courseplanner.ui.actions.timetable.LessonRemoval;
import com.senla.rakickaya.courseplanner.ui.assembly_request.AssemblyDateRequest;
import com.senla.rakickaya.courseplanner.ui.assembly_request.AssemblyIntervalRequest;
import com.senla.rakickaya.view.facade.Facade;

public class Buider {
	private Menu rootMenu;

	private Menu buildStudentMenu(Menu prevMenu) {
		MenuItem backItem = new MenuItem();
		backItem.setTitle("back");
		backItem.setNextMenu(prevMenu);
		
		Menu studentMenu = new Menu();
		studentMenu.setName("Students");
		List<MenuItem> list = new ArrayList<>();
		MenuItem addItem = new MenuItem();
		addItem.setTitle("Add student");
		addItem.setAction(new StudentAddition());
		MenuItem remItem = new MenuItem();
		remItem.setTitle("Remove student");
		remItem.setAction(new StudentRemoval());
		MenuItem countItem = new MenuItem();
		countItem.setTitle("Show count of students");
		countItem.setAction(new TotalCountSt());
		list.add(backItem);
		list.add(addItem);
		list.add(remItem);
		list.add(countItem);
		studentMenu.setListItems(list);
		return studentMenu;
	}

	private Menu buildLectorMenu(Menu prevMenu) {
		MenuItem backItem = new MenuItem();
		backItem.setTitle("back");
		backItem.setNextMenu(prevMenu);
		Menu lectorMenu = new Menu();
		lectorMenu.setName("Lector");
		List<MenuItem> list = new ArrayList<>();
		MenuItem addItem = new MenuItem();
		addItem.setTitle("Add lector");
		addItem.setAction(new LectorAddition());
		MenuItem remItem = new MenuItem();
		remItem.setTitle("Remove lector");
		remItem.setAction(new LectorRemoval());
		MenuItem countItem = new MenuItem();
		countItem.setTitle("Show count of lectors");
		countItem.setAction(new TotalCountLectors());

		MenuItem sortLectorByAlfItem = new MenuItem();
		sortLectorByAlfItem.setTitle("Show sorted lectors by alphabet");
		sortLectorByAlfItem.setAction(new PrinterInfo(new PrinterInfo.IListResponse() {

			@Override
			public IResponse getResponse() {
				return Facade.getInstance().getSortedLectorsByAlphabet();
			}
		}));
		MenuItem sortLectorByCountCourseItem = new MenuItem();
		sortLectorByCountCourseItem.setTitle("Show sorted lectors by count courses");
		sortLectorByCountCourseItem.setAction(new SortLectorsByCountCourse());

		list.add(backItem);
		list.add(addItem);
		list.add(remItem);
		list.add(countItem);
		list.add(sortLectorByAlfItem);
		list.add(sortLectorByCountCourseItem);
		lectorMenu.setListItems(list);
		return lectorMenu;

	}

	private Menu buildCourseMenu(Menu prevMenu) {
		MenuItem backItem = new MenuItem();
		backItem.setTitle("back");
		backItem.setNextMenu(prevMenu);

		Menu courseMenu = new Menu();
		courseMenu.setName("Courses");
		List<MenuItem> list = new ArrayList<>();

		MenuItem addItem = new MenuItem();
		addItem.setTitle("Add course");
		addItem.setAction(new CourseAddition());

		MenuItem remItem = new MenuItem();
		remItem.setTitle("Remove course");
		remItem.setAction(new CourseRemoval());

		MenuItem countItem = new MenuItem();
		countItem.setTitle("Show count of courses");
		countItem.setAction(new TotalCountCourses());

		MenuItem addLectureItem = new MenuItem();
		addLectureItem.setTitle("Add lecture to course");
		addLectureItem.setAction(new LectureAddition());

		MenuItem remLectureItem = new MenuItem();
		remLectureItem.setTitle("Remove lecture from course");
		remLectureItem.setAction(new LectureRemoval());

		MenuItem addStudentItem = new MenuItem();
		addStudentItem.setTitle("Add student to course");
		addStudentItem.setAction(new StudentAdditionToCourse());

		MenuItem remStudentItem = new MenuItem();
		remStudentItem.setTitle("Remove student from course");
		remStudentItem.setAction(new StudentRemovalFromCourse());

		MenuItem addLectorItem = new MenuItem();
		addLectorItem.setTitle("Add lector to course");
		addLectorItem.setAction(new LectorAdditionToCourse());

		MenuItem remLectorItem = new MenuItem();
		remLectorItem.setTitle("Remove lector from course");
		remLectorItem.setAction(new LectorRemovalFromCourse());

		MenuItem itemSortedCourseByAlf = new MenuItem();
		itemSortedCourseByAlf.setTitle("Show sorted coures by alphabet");
		itemSortedCourseByAlf.setAction(new PrinterInfo(new PrinterInfo.IListResponse() {

			@Override
			public IResponse getResponse() {
				return Facade.getInstance().getSortedCoursesByAlphabet();
			}
		}));
		MenuItem itemSortedCourseByDate = new MenuItem();
		itemSortedCourseByDate.setTitle("Show sorted coures by date");
		itemSortedCourseByDate.setAction(new PrinterInfo(new PrinterInfo.IListResponse() {

			@Override
			public IResponse getResponse() {
				return Facade.getInstance().getSortedCoursesByStartDate();
			}
		}));
		MenuItem itemSortedCourseByLector = new MenuItem();
		itemSortedCourseByLector.setTitle("show sorted courses by lector name");
		itemSortedCourseByLector.setAction(new PrinterInfo(new PrinterInfo.IListResponse() {

			@Override
			public IResponse getResponse() {
				return Facade.getInstance().getSortedCoursesByLectorName();
			}
		}));
		MenuItem itemSortedCourseByCountStudent = new MenuItem();
		itemSortedCourseByCountStudent.setTitle("show sorted courses by student's count");
		itemSortedCourseByCountStudent.setAction(new PrinterInfo(new PrinterInfo.IListResponse() {

			@Override
			public IResponse getResponse() {
				return Facade.getInstance().getSortedCoursesByCountStudents();
			}
		}));
		MenuItem itemDetailDescription = new MenuItem();
		itemDetailDescription.setTitle("show detail description by course");
		itemDetailDescription.setAction(new DetailedDescription());

		MenuItem itemSortCourseAfterDateByAlp = new MenuItem();
		itemSortCourseAfterDateByAlp.setTitle("show sorted by alphabet courses after date ");
		itemSortCourseAfterDateByAlp.setAction(new PrinterInfoParam(new PrinterInfoParam.IListResponse() {
			@Override
			public IResponse getResponse(IRequest request) {
				return Facade.getInstance().getSortedCoursesByAlphabet(request);
			}
		}, new AssemblyDateRequest("Input after date")));

		MenuItem itemSortCourseAfterDateByDate = new MenuItem();
		itemSortCourseAfterDateByDate.setTitle("show sorted by start date courses after date ");
		itemSortCourseAfterDateByDate.setAction(new PrinterInfoParam(new PrinterInfoParam.IListResponse() {
			@Override
			public IResponse getResponse(IRequest request) {
				return Facade.getInstance().getSortedCoursesByStartDate(request);
			}
		}, new AssemblyDateRequest("Input after date")));
		MenuItem itemSortCourseAfterDateByLector = new MenuItem();
		itemSortCourseAfterDateByLector.setTitle("show sorted by lector name courses after date ");
		itemSortCourseAfterDateByLector.setAction(new PrinterInfoParam(new PrinterInfoParam.IListResponse() {
			@Override
			public IResponse getResponse(IRequest request) {
				return Facade.getInstance().getSortedCoursesByLectorName(request);
			}
		}, new AssemblyDateRequest("Input after date")));
		MenuItem itemSortCourseAfterDateByCount = new MenuItem();
		itemSortCourseAfterDateByCount.setTitle("show sorted by count student courses after date ");
		itemSortCourseAfterDateByCount.setAction(new PrinterInfoParam(new PrinterInfoParam.IListResponse() {
			@Override
			public IResponse getResponse(IRequest request) {
				return Facade.getInstance().getSortedCoursesByCountStudents(request);
			}
		}, new AssemblyDateRequest("Input after date")));
		MenuItem itemSortCurrentCourseDateByAlp = new MenuItem();
		itemSortCurrentCourseDateByAlp.setTitle("show sorted by alphabet current courses");
		itemSortCurrentCourseDateByAlp.setAction(new PrinterInfoParam(new PrinterInfoParam.IListResponse() {
			@Override
			public IResponse getResponse(IRequest request) {
				return Facade.getInstance().getSortedCurrentCoursesByAlphabet(request);
			}
		}, new AssemblyDateRequest("Input current date")));

		MenuItem itemSortCurrentCourseByDate = new MenuItem();
		itemSortCurrentCourseByDate.setTitle("show sorted by start date current courses");
		itemSortCurrentCourseByDate.setAction(new PrinterInfoParam(new PrinterInfoParam.IListResponse() {
			@Override
			public IResponse getResponse(IRequest request) {
				return Facade.getInstance().getSortedCurrentCoursesByStartDate(request);
			}
		}, new AssemblyDateRequest("Input current date")));
		MenuItem itemSortCurrentCourseByLector = new MenuItem();
		itemSortCurrentCourseByLector.setTitle("show sorted by lector name current courses");
		itemSortCurrentCourseByLector.setAction(new PrinterInfoParam(new PrinterInfoParam.IListResponse() {
			@Override
			public IResponse getResponse(IRequest request) {
				return Facade.getInstance().getSortedCurrentCoursesByLectorName(request);
			}
		}, new AssemblyDateRequest("Input current date")));
		MenuItem itemSortCurrentCourseByCount = new MenuItem();
		itemSortCurrentCourseByCount.setTitle("show sorted by count student current courses");
		itemSortCurrentCourseByCount.setAction(new PrinterInfoParam(new PrinterInfoParam.IListResponse() {
			@Override
			public IResponse getResponse(IRequest request) {
				return Facade.getInstance().getSortedCurrentCoursesByCountStudents(request);
			}
		}, new AssemblyDateRequest("Input current date")));
		MenuItem itemPastCourseInInterval = new MenuItem();
		itemPastCourseInInterval.setTitle("show past courses was been in interval");
		itemPastCourseInInterval.setAction(new PrinterInfoParam(new PrinterInfoParam.IListResponse() {
			@Override
			public IResponse getResponse(IRequest request) {
				return Facade.getInstance().getPastCourses(request);
			}
		}, new AssemblyIntervalRequest("Input interval date")));
		list.add(backItem);
		list.add(addItem);
		list.add(remItem);
		list.add(countItem);
		list.add(addLectureItem);
		list.add(remLectureItem);
		list.add(addStudentItem);
		list.add(remStudentItem);
		list.add(addLectorItem);
		list.add(remLectorItem);
		list.add(itemSortedCourseByAlf);
		list.add(itemSortedCourseByDate);
		list.add(itemSortedCourseByLector);
		list.add(itemSortedCourseByCountStudent);
		list.add(itemDetailDescription);
		list.add(itemSortCourseAfterDateByAlp);
		list.add(itemSortCourseAfterDateByDate);
		list.add(itemSortCourseAfterDateByLector);
		list.add(itemSortCourseAfterDateByCount);
		list.add(itemSortCurrentCourseDateByAlp);
		list.add(itemSortCurrentCourseByDate);
		list.add(itemSortCurrentCourseByLector);
		list.add(itemSortCurrentCourseByCount);
		list.add(itemPastCourseInInterval);
		courseMenu.setListItems(list);
		return courseMenu;

	}

	private Menu buildTimeTableMenu(Menu prevMenu) {
		MenuItem backItem = new MenuItem();
		backItem.setTitle("back");
		backItem.setNextMenu(prevMenu);
		Menu timeTableMenu = new Menu();
		timeTableMenu.setName("Time Table");
		List<MenuItem> list = new ArrayList<>();
		MenuItem createItem = new MenuItem();
		createItem.setTitle("Create time table for lecture");
		createItem.setAction(new LessonAddition());
		MenuItem remItem = new MenuItem();
		remItem.setTitle("Remove time table for lecture");
		remItem.setAction(new LessonRemoval());
		MenuItem itemLessonsByDate = new MenuItem();
		itemLessonsByDate.setTitle("show lessons by date");
		itemLessonsByDate.setAction(new PrinterInfoParam(new PrinterInfoParam.IListResponse() {
			@Override
			public IResponse getResponse(IRequest request) {
				return Facade.getInstance().getListLessonByDate(request);
			}
		}, new AssemblyDateRequest("Input date")));

		MenuItem itemSortedTimeTableByDate = new MenuItem();
		itemSortedTimeTableByDate.setTitle("Show sorted time table by date");
		itemSortedTimeTableByDate.setAction(new PrinterInfo(new PrinterInfo.IListResponse() {

			@Override
			public IResponse getResponse() {
				return Facade.getInstance().getSortedTimeTableByDate();
			}
		}));
		MenuItem itemSortedTimeTableByAlp = new MenuItem();
		itemSortedTimeTableByAlp.setTitle("Show sorted coures by alphabet");
		itemSortedTimeTableByAlp.setAction(new PrinterInfo(new PrinterInfo.IListResponse() {

			@Override
			public IResponse getResponse() {
				return Facade.getInstance().getSortedTimeTableByAlphabet();
			}
		}));
		list.add(backItem);
		list.add(createItem);
		list.add(remItem);
		list.add(itemLessonsByDate);
		list.add(itemSortedTimeTableByDate);
		list.add(itemSortedTimeTableByAlp);
		timeTableMenu.setListItems(list);
		return timeTableMenu;
	}

	public void buildMenu() {
		MenuItem exitItem = new MenuItem();
		exitItem.setTitle("exit");
		exitItem.setAction(new ExitAction());
		MenuItem studentItem = new MenuItem();
		studentItem.setTitle("Student");
		MenuItem lectorItem = new MenuItem();
		lectorItem.setTitle("Lectors");
		MenuItem courseItem = new MenuItem();
		courseItem.setTitle("Courses");
		MenuItem timeTableItem = new MenuItem();
		timeTableItem.setTitle("Time table");
		List<MenuItem> list = new ArrayList<>();
		list.add(exitItem);
		list.add(studentItem);
		list.add(lectorItem);
		list.add(courseItem);
		list.add(timeTableItem);
		rootMenu = new Menu();
		rootMenu.setListItems(list);
		rootMenu.setName("Course Planner");
		studentItem.setNextMenu(buildStudentMenu(rootMenu));
		lectorItem.setNextMenu(buildLectorMenu(rootMenu));
		courseItem.setNextMenu(buildCourseMenu(rootMenu));
		timeTableItem.setNextMenu(buildTimeTableMenu(rootMenu));

	}

	public Menu getRootMenu() {
		return rootMenu;
	}

}
