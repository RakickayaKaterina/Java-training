package com.senla.rakickaya.courseplanner.repositories;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.repositories.ILectorsRepository;
import com.senla.rakickaya.courseplanner.repositories.filler.FillerRepositories;
import com.senla.rakickaya.courseplanner.utils.GeneratorId;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class LectorsRepository implements ILectorsRepository {

	private static LectorsRepository lectorsRepository;
	private List<ILector> mLectors;
	private GeneratorId generatorId = GeneratorId.getInstance();

	public LectorsRepository() {
		super();
		mLectors = new ArrayList<>();
	}

	public LectorsRepository(List<ILector> mLectors) {
		super();
		this.mLectors = mLectors;
	}

	public static LectorsRepository getInstance() {
		if (lectorsRepository == null) {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			lectorsRepository = new LectorsRepository(fillerRepositories.getLectors());
		}
		return lectorsRepository;
	}

	@Override
	public boolean addLector(ILector pLector) {
		if (pLector.getId() == 0L) {
			pLector.setId(generatorId.nextIdLector());
		} else {
			if (getLector(pLector.getId()) != null) {
				return false;
			}
		}
		mLectors.add(pLector);
		save();
		return true;
	}

	@Override
	public ILector removeLector(long pId) {
		ILector lector = ListWorker.removeItemById(mLectors, pId);
		if (lector != null) {
			save();
		}
		return lector;
	}

	@Override
	public void updateLector(ILector pLector) {
		ListWorker.updateItem(mLectors, pLector);
		save();

	}

	@Override
	public ILector getLector(long pId) {
		return (ILector) ListWorker.getItemById(mLectors, pId);
	}

	@Override
	public List<ILector> getLectors() {
		return mLectors;
	}

	@Override
	public void save() {
		FillerRepositories fillerRepositories = FillerRepositories.getInstance();
		fillerRepositories.writeLectorToFile(mLectors);
	}

}
