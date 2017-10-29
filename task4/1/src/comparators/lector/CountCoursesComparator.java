package comparators.lector;

import java.util.Comparator;

import models.Lector;
import utils.ArrayWorker;

public class CountCoursesComparator implements Comparator<Lector> {
      
	@Override
	public int compare(Lector arg0, Lector arg1) {
		Integer count = ArrayWorker.getLenghtArray(arg0.getCourses());
		return count.compareTo(ArrayWorker.getLenghtArray(arg1.getCourses()));
	}

}
