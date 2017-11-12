package com.senla.rakickaya.courseplanner.api.repositories;

import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.IRelationSC;

public interface IRelationsRepository {
	public void addRelation(IRelationSC pRelation);

	public IRelationSC removeRelation(long pId);

	public void updateRelation(IRelationSC pRelation);

	public IRelationSC getRelation(long pId);

	public List<IRelationSC> getListRelations();

}
