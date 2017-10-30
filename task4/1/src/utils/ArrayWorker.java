package utils;

import java.util.Arrays;

import models.Entity;

public class ArrayWorker {
	public static boolean existPosition(Entity[] pEntities) {
		return pEntities[pEntities.length - 1] == null;
	}

	public static int getFreePosition(Entity[] pEntities) {
		for (int i = 0; i < pEntities.length; i++) {
			if (pEntities[i] == null)
				return i;
		}
		return -1;
	}

	public static int getLastPosition(Entity[] pEntities) {
		for (int i = pEntities.length - 1; i >= 0; i--) {
			if (pEntities[i] != null)
				return i;
		}
		return -1;
	}

	public static void resize(Entity[] pEntities) {
		pEntities = Arrays.copyOf(pEntities, pEntities.length * 2);
	}

	public static int getPositionById(long id, Entity[] pEntities) {
		for (int i = 0; i < pEntities.length; i++) {
			if (pEntities[i]!= null && pEntities[i].getId() == id)
				return i;
		}
		return -1;
	}

	public static void addToArray(Entity pEntity, Entity[] pEntities) {
		if (!existPosition(pEntities)) {
			resize(pEntities);
		}
		int position = getFreePosition(pEntities);
		pEntities[position] = pEntity;
	}

	public static Entity removeFromArray(long id, Entity[] pEntities) {
		int position = getPositionById(id, pEntities);
		if (position < 0) {
			// TODO error message
			return null;
		}
		Entity removedEntity = pEntities[position];
		pEntities[position] = null;
		int lastPosition = getLastPosition(pEntities);
		if (position >= lastPosition) {
			return removedEntity;
		}
		swap(pEntities, position, lastPosition);
		return removedEntity;
	}

	public static void updatePosition(Entity pEntity, Entity[] pEntities) {
		int position = getPositionById(pEntity.getId(), pEntities);
		if (position < 0) {
			return;
		}
		pEntities[position] = pEntity;
	}

	public static void swap(Entity[] pEntities, int first, int second) {
		if (first < 0 || second < 0 || first > pEntities.length - 1 || second > pEntities.length - 1)
			return;
		Entity temp = pEntities[first];
		pEntities[first] = pEntities[second];
		pEntities[second] = temp;
	}

	public static int getLenghtArray(Entity[] pEntities) {
		int count = 0;
		for (int i = 0; i < pEntities.length; i++) {
			if (pEntities[i] != null) {
				count++;
			}
		}
		return count;
	}
}
