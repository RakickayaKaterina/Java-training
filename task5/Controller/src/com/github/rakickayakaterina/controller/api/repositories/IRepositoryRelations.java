package com.github.rakickayakaterina.controller.api.repositories;

import java.util.List;

import com.github.rakickayakaterina.model.beans.RelationSC;

public interface IRepositoryRelations {
	public void addRelation(RelationSC pRelation);

	public RelationSC removeRelation(long pId);

	public void updateRelation(RelationSC pRelation);

	public RelationSC getRelation(long pId);

	public List<RelationSC> getListRelations();

}
