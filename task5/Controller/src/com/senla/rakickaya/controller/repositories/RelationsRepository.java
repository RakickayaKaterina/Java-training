package com.senla.rakickaya.controller.repositories;

import java.util.List;

import com.senla.rakickaya.controller.api.repositories.IRelationsRepository;
import com.senla.rakickaya.model.beans.RelationSC;
import com.senla.rakickaya.utils.ListWorker;

public class RelationsRepository implements IRelationsRepository {
	private List<RelationSC> mRelations;

	public RelationsRepository(List<RelationSC> mRelations) {
		super();
		this.mRelations = mRelations;
	}

	@Override
	public void addRelation(RelationSC pRelation) {
		mRelations.add(pRelation);
	}

	@Override
	public RelationSC removeRelation(long pId) {
		return (RelationSC) ListWorker.removeItemById(mRelations, pId);
	}

	@Override
	public void updateRelation(RelationSC pRelation) {
		ListWorker.updateItem(mRelations, pRelation);
	}

	@Override
	public RelationSC getRelation(long pId) {
		return (RelationSC) ListWorker.getItemById(mRelations, pId);
	}

	@Override
	public List<RelationSC> getListRelations() {
		return mRelations;
	}

}
