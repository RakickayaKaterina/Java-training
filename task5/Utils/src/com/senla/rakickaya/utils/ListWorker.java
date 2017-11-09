package com.senla.rakickaya.utils;

import java.util.List;

import com.senla.rakickaya.model.beans.Entity;

public class ListWorker {
	public static <T extends Entity>int getIndexById(List<T> pEntities, long pId) {
		int position = -1;
		for (int i = 0; i < pEntities.size(); i++) {
			if (pEntities.get(i).getId() == pId) {
				position = i;
				break;
			}
		}
		return position;
	}
	public static <T extends Entity>T removeItemById(List<T> pEntities, long pId){
		int index = ListWorker.getIndexById(pEntities, pId);
		T removedEntity = null;
		if (index != -1) {
			pEntities.remove(index);
			removedEntity = pEntities.get(index);
		}
		return removedEntity;
	}
	public static <T extends Entity>void updateItem(List<T> pEntities, T pEntity){
		int position = ListWorker.getIndexById(pEntities, pEntity.getId());
		if (position != -1)
			pEntities.set(position, pEntity);

	}
	public static <T extends Entity>T getItemById(List<T> pEntities, long pId){
		int index = ListWorker.getIndexById(pEntities, pId);
		return pEntities.get(index);
	}
}
