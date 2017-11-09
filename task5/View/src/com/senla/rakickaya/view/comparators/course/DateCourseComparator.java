package  com.senla.rakickaya.view.comparators.course;

import java.util.Comparator;

import com.senla.rakickaya.model.beans.Course;


public class DateCourseComparator implements Comparator<Course> {
	@Override
	public int compare(Course o1, Course o2) {
		if (o1 != null && o2 != null) {
			return o1.getStartDate().compareTo(o2.getStartDate());
		} else if (o1 != null && o2 == null) {
			return 1;
		} else {
			return -1;
		}
	}

}
