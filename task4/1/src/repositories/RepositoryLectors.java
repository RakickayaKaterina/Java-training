package repositories;

import models.Lector;
import utils.ArrayWorker;

public class RepositoryLectors implements IRepositoryLectors {
	private Lector[] mLectors;

	public RepositoryLectors(int countLectors) {
		super();
		mLectors = new Lector[countLectors];
	}

	@Override
	public void addLector(Lector pLector) {
		ArrayWorker.addToArray(pLector, mLectors);

	}

	public RepositoryLectors(Lector[] mLectors) {
		super();
		this.mLectors = mLectors;
	}

	@Override
	public Lector removeLector(long pId) {
		return (Lector) ArrayWorker.removeFromArray(pId, mLectors);
	}

	@Override
	public void updateLector(Lector pLector) {
		ArrayWorker.updatePosition(pLector, mLectors);

	}

	@Override
	public Lector getLector(long pId) {
		int position = ArrayWorker.getPositionById(pId, mLectors);
		if (position >= 0) {
			return mLectors[position];
		}
		return null;
	}

	@Override
	public Lector[] getListLectors() {
		return mLectors;
	}

	@Override
	public void saveState() {
		// TODO save method

	}

}
