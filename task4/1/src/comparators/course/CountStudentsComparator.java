package comparators.course;

import java.util.Comparator;

import models.Course;
import utils.ArrayWorker;

public class CountStudentsComparator implements Comparator<Course>{

	@Override
	public int compare(Course o1, Course o2) {
		Integer count = ArrayWorker.getLenghtArray(o1.getStudents());
		return count.compareTo(ArrayWorker.getLenghtArray(o2.getStudents()));
	}
	
}
