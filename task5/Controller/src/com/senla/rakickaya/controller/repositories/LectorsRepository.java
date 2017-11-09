package com.senla.rakickaya.controller.repositories;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.controller.api.repositories.ILectorsRepository;
import com.senla.rakickaya.model.beans.Lector;
import com.senla.rakickaya.utils.ListWorker;

public class LectorsRepository implements ILectorsRepository {
	private List<Lector> mLectors;

	public LectorsRepository() {
		super();
		mLectors = new ArrayList<Lector>();
	}

	public LectorsRepository(List<Lector> mLectors) {
		super();
		this.mLectors = mLectors;
	}

	@Override
	public void addLector(Lector pLector) {
		mLectors.add(pLector);

	}

	@Override
	public Lector removeLector(long pId) {
		return (Lector)ListWorker.removeItemById(mLectors, pId);
	}

	@Override
	public void updateLector(Lector pLector) {
		ListWorker.updateItem(mLectors,pLector);

	}

	@Override
	public Lector getLector(long pId) {
		return (Lector)ListWorker.getItemById(mLectors, pId);
	}

	@Override
	public List<Lector> getListLectors() {
		return mLectors;
	}

}
