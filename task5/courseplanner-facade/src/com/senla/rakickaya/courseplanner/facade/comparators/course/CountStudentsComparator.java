package com.senla.rakickaya.courseplanner.facade.comparators.course;

import java.util.Comparator;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;


public class CountStudentsComparator implements Comparator<ICourse> {

	@Override
	public int compare(ICourse o1, ICourse o2) {
		if (o1 != null && o2 != null) {
			Integer count =	o1.getStudents().size(); 
			return count.compareTo(o2.getStudents().size());
		} else if (o1 != null && o2 == null) {
			return 1;
		} else {
			return -1;
		}

	}

}
