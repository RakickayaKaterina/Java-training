package com.github.rakickayakaterina.courseplanner.repositories;

import com.github.rakickayakaterina.courseplanner.api.repositories.IRepositoryRelations;
import com.github.rakickayakaterina.courseplanner.beans.RelationSC;
import com.github.rakickayakaterina.courseplanner.utils.ArrayWorker;

public class RepositoryRelations implements IRepositoryRelations {
	private RelationSC[] mRelation;

	public RepositoryRelations(RelationSC[] mRelation) {
		super();
		this.mRelation = mRelation;
	}

	@Override
	public void addRelation(RelationSC pRelation) {
		mRelation = (RelationSC[]) ArrayWorker.addToArray(pRelation, mRelation);
	}

	@Override
	public RelationSC removeRelation(long pId) {
		return (RelationSC) ArrayWorker.removeFromArray(pId, mRelation);
	}

	@Override
	public void updateRelation(RelationSC pRelation) {
		ArrayWorker.updatePosition(pRelation, mRelation);

	}

	@Override
	public RelationSC getRelation(long pId) {
		int position = ArrayWorker.getPositionById(pId, mRelation);
		if (position >= 0) {
			return mRelation[position];
		}
		return null;
	}

	@Override
	public RelationSC[] getListRelations() {
		return mRelation;
	}

}
