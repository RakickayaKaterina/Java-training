package com.github.rakickayakaterina.controller.repositories;

import java.util.ArrayList;
import java.util.List;

import com.github.rakickayakaterina.controller.api.repositories.IRepositoryLectors;
import com.github.rakickayakaterina.model.beans.Lector;
import com.github.rakickayakaterina.utils.ListWorker;

public class RepositoryLectors implements IRepositoryLectors {
	private List<Lector> mLectors;

	public RepositoryLectors() {
		super();
		mLectors = new ArrayList<Lector>();
	}

	public RepositoryLectors(List<Lector> mLectors) {
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
