package comparators.lesson;

import java.util.Comparator;

import models.Lesson;

public class AlphabetLessonComparator implements Comparator<Lesson>{
	@Override
	public int compare(Lesson o1, Lesson o2) {
		return o1.getLecture().getName().compareTo(o2.getLecture().getName());
	}

}
