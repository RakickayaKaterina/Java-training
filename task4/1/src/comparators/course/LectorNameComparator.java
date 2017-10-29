package comparators.course;

import java.util.Comparator;

import models.Course;

public class LectorNameComparator implements Comparator<Course>{

	@Override
	public int compare(Course o1, Course o2) {
		return o1.getLector().getName().compareTo(o2.getLector().getName());
	}

}
