package comparators.lesson;

import java.util.Comparator;

import models.Lesson;

public class AlphabetLessonComparator implements Comparator<Lesson> {
	@Override
	public int compare(Lesson o1, Lesson o2) {
		if (o1 != null && o2 != null) {
			return o1.getLecture().getName().compareTo(o2.getLecture().getName());
		} else if (o1 != null && o2 == null) {
			return 1;
		} else {
			return -1;
		}
	}

}
