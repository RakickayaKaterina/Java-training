package com.senla.rakickaya.controller.api.repositories;

import java.util.List;

import com.senla.rakickaya.model.beans.RelationSC;

public interface IRelationsRepository {
	public void addRelation(RelationSC pRelation);

	public RelationSC removeRelation(long pId);

	public void updateRelation(RelationSC pRelation);

	public RelationSC getRelation(long pId);

	public List<RelationSC> getListRelations();

}
