package com.github.rakickayakaterina.controller.repositories;

import java.util.List;

import com.github.rakickayakaterina.controller.api.repositories.IRepositoryRelations;
import com.github.rakickayakaterina.model.beans.RelationSC;
import com.github.rakickayakaterina.utils.ListWorker;

public class RepositoryRelations implements IRepositoryRelations {
	private List<RelationSC> mRelations;

	public RepositoryRelations(List<RelationSC> mRelations) {
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
