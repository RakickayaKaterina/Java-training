package com.senla.rakickaya.courseplanner.facade.comparators.lesson;

import java.util.Comparator;

import com.senla.rakickaya.courseplanner.api.beans.ILesson;

public class AlphabetLessonComparator implements Comparator<ILesson> {
	@Override
	public int compare(ILesson o1, ILesson o2) {
		if (o1 != null && o2 != null) {
			return o1.getLecture().getName().compareTo(o2.getLecture().getName());
		} else if (o1 != null && o2 == null) {
			return 1;
		} else {
			return -1;
		}
	}

}
