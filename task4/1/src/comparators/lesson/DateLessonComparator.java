package comparators.lesson;

import java.util.Comparator;

import models.Lesson;

public class DateLessonComparator implements Comparator<Lesson> {
	@Override
	public int compare(Lesson o1, Lesson o2) {
		return o1.getDate().compareTo(o2.getDate());
	}
}
