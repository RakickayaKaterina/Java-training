package comparators.lector;

import java.util.Comparator;

import models.Lector;
import utils.ArrayWorker;

public class CountCoursesComparator implements Comparator<Lector> {

	@Override
	public int compare(Lector arg0, Lector arg1) {
		if (arg0 != null && arg1 != null) {
			Integer count = ArrayWorker.getLenghtArray(arg0.getCourses());
			return count.compareTo(ArrayWorker.getLenghtArray(arg1.getCourses()));
		} else if (arg0 != null && arg1 == null) {
			return 1;
		} else {
			return -1;
		}
	}

}
