package repositories;

import models.Course;
import utils.ArrayWorker;

public class RepositoryCourses implements IRepositoryCourses{
	private Course[] mCourses;

	public RepositoryCourses(int countCourses) {
		super();
		mCourses = new Course[countCourses];
	}

	@Override
	public void addCourse(Course pCourse) {
		ArrayWorker.addToArray(pCourse, mCourses);
		
	}

	@Override
	public Course removeCourse(long pId) {
		return (Course)ArrayWorker.removeFromArray(pId, mCourses);
	}

	@Override
	public void updateCourse(Course pCourse) {
		ArrayWorker.updatePosition(pCourse, mCourses);
		
	}

	@Override
	public Course getCourse(long pId) {
		int position = ArrayWorker.getPositionById(pId, mCourses);
		if(position>0){
		return mCourses[position];
		}
		return null;
	}

	@Override
	public Course[] getListCourse() {
		return mCourses;
	}

	@Override
	public void saveState() {
		// TODO save
		
	}

}
