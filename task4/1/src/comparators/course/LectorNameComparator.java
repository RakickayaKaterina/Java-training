package comparators.course;

import java.util.Comparator;

import models.Course;

public class LectorNameComparator implements Comparator<Course> {

	@Override
	public int compare(Course o1, Course o2) {
		if (o1 != null && o2 != null) {
			return o1.getLector().getName().compareTo(o2.getLector().getName());
		} else if (o1 != null && o2 == null) {
			return 1;
		} else {
			return -1;
		}
	}

}
