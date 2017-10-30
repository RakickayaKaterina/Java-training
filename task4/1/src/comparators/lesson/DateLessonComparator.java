package comparators.lesson;

import java.util.Comparator;

import models.Lesson;

public class DateLessonComparator implements Comparator<Lesson> {
	@Override
	public int compare(Lesson o1, Lesson o2) {
		if (o1 != null && o2 != null) {
			return o1.getDate().compareTo(o2.getDate());
		} else if (o1 != null && o2 == null) {
			return 1;
		} else {
			return -1;
		}
	}
}
