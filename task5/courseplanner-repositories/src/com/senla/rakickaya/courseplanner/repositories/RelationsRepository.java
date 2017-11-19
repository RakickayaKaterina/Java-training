package com.senla.rakickaya.courseplanner.repositories;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IRelationSC;
import com.senla.rakickaya.courseplanner.api.repositories.IRelationsRepository;
import com.senla.rakickaya.courseplanner.repositories.filler.FillerRepositories;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class RelationsRepository implements IRelationsRepository {
	private List<IRelationSC> mRelations;
	private static RelationsRepository relationsRepository;

	public static RelationsRepository getInstance() {
		if (relationsRepository == null) {
			FillerRepositories fillerRepositories = FillerRepositories.getInstance();
			relationsRepository = new RelationsRepository(fillerRepositories.getRelations());
		}
		return relationsRepository;
	}

	public RelationsRepository(List<IRelationSC> mRelations) {
		super();
		this.mRelations = mRelations;
	}

	@Override
	public void addRelation(IRelationSC pRelation) {
		mRelations.add(pRelation);
	}

	@Override
	public IRelationSC removeRelation(long pId) {
		return ListWorker.removeItemById(mRelations, pId);
	}

	@Override
	public void updateRelation(IRelationSC pRelation) {
		ListWorker.updateItem(mRelations, pRelation);
	}

	@Override
	public IRelationSC getRelation(long pId) {
		return (IRelationSC) ListWorker.getItemById(mRelations, pId);
	}

	@Override
	public List<IRelationSC> getListRelations() {
		return mRelations;
	}

	@Override
	public void save() {
		FillerRepositories fillerRepositories = FillerRepositories.getInstance();
		fillerRepositories.writeRelationToFile(mRelations);

	}

}
