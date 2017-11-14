package com.senla.rakickaya.courseplanner.repositories;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.repositories.ILectorsRepository;
import com.senla.rakickaya.courseplanner.repositories.filler.FillerRepositories;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class LectorsRepository implements ILectorsRepository {
	private List<ILector> mLectors;
	private static LectorsRepository lectorsRepository;

	public static LectorsRepository getInstance() {
		if (lectorsRepository == null) {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			lectorsRepository = new LectorsRepository(fillerRepositories.getLectors());
		}
		return lectorsRepository;
	}

	public LectorsRepository() {
		super();
		mLectors = new ArrayList<>();
	}

	public LectorsRepository(List<ILector> mLectors) {
		super();
		this.mLectors = mLectors;
	}

	@Override
	public void addLector(ILector pLector) {
		mLectors.add(pLector);

	}

	@Override
	public ILector removeLector(long pId) {
		return ListWorker.removeItemById(mLectors, pId);
	}

	@Override
	public void updateLector(ILector pLector) {
		ListWorker.updateItem(mLectors, pLector);

	}

	@Override
	public ILector getLector(long pId) {
		return (ILector) ListWorker.getItemById(mLectors, pId);
	}

	@Override
	public List<ILector> getListLectors() {
		return mLectors;
	}

	@Override
	public void save() {
		FillerRepositories fillerRepositories = FillerRepositories.getInstance();
		fillerRepositories.writeLectorToFile(mLectors);

	}

}
