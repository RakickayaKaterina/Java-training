package  com.senla.rakickaya.view.comparators.lector;

import java.util.Comparator;

import com.senla.rakickaya.model.beans.Lector;


public class AlphabetLectorComparator implements Comparator<Lector> {
	@Override
	public int compare(Lector arg0, Lector arg1) {
		if (arg0 != null && arg1 != null) {
			return arg0.getName().compareTo(arg1.getName());
		} else if (arg0 != null && arg1 == null) {
			return 1;
		} else {
			return -1;
		}
	}

}
