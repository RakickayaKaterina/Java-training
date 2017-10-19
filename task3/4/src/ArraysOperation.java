
public class ArraysOperation {
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

	public static int getLastPosition(Object[] objects) {
		for (int i = objects.length - 1; i >= 0; i--) {
			if (objects[i] != null)
				return i;
		}
		return -1;
	}

	public static void swap(Object[] obj, int first, int second) {
		if (first < 0 || second < 0 || first > obj.length - 1 || second > obj.length - 1)
			return;
		Object temp = obj[first];
		obj[first] = obj[second];
		obj[second] = temp;
	}

	public static int getCountFreePosition(Object[] objects) {
		int count = 0;
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null)
				count++;
		}
		return count;
	}
}
