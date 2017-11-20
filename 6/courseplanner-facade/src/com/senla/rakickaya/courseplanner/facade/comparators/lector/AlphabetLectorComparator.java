package  com.senla.rakickaya.courseplanner.facade.comparators.lector;

import java.util.Comparator;

import com.senla.rakickaya.courseplanner.api.beans.ILector;



public class AlphabetLectorComparator implements Comparator<ILector> {
	@Override
	public int compare(ILector arg0, ILector arg1) {
		if (arg0 != null && arg1 != null) {
			return arg0.getName().compareTo(arg1.getName());
		} else if (arg0 != null && arg1 == null) {
			return 1;
		} else {
			return -1;
		}
	}

}
