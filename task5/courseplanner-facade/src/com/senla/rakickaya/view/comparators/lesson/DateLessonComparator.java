package  com.senla.rakickaya.view.comparators.lesson;

import java.util.Comparator;

import com.senla.rakickaya.courseplanner.api.beans.ILesson;



public class DateLessonComparator implements Comparator<ILesson> {
	@Override
	public int compare(ILesson o1, ILesson o2) {
		if (o1 != null && o2 != null) {
			return o1.getDate().compareTo(o2.getDate());
		} else if (o1 != null && o2 == null) {
			return 1;
		} else {
			return -1;
		}
	}
}
