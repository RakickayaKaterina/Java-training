package comparators.lector;

import java.util.Comparator;

import models.Lector;

public class AlphabetLectorComparator implements Comparator<Lector> {
	@Override
	public int compare(Lector arg0, Lector arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}

}
