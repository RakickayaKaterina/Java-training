package com.senla.rakickaya.courseplanner.facade.comparators.course;

import java.util.Comparator;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;



public class AlphabetCourseComparator implements Comparator<ICourse> {
	@Override
	public int compare(ICourse o1, ICourse o2) {
		if (o1 != null && o2 != null) {
			return o1.getName().compareTo(o2.getName());
		} else if (o1 != null && o2 == null) {
			return 1;
		} else {
			return -1;
		}
	}

}
