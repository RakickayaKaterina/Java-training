
public class Checker {
	public static boolean existPosition(Object[] pObjects) {
		return pObjects[pObjects.length - 1] == null;
	}

	public static int getFreePosition(Object[] pObjects) {
		for (int i = 0; i < pObjects.length; i++) {
			if (pObjects[i] == null)
				return i;
		}
		return -1;
	}
}
