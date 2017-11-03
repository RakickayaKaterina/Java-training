package com.github.rakickayakaterina.courseplanner.api.repositories;

import com.github.rakickayakaterina.courseplanner.beans.RelationSC;

public interface IRepositoryRelations {
	public void addRelation(RelationSC pRelation);

	public RelationSC removeRelation(long pId);

	public void updateRelation(RelationSC pRelation);

	public RelationSC getRelation(long pId);

	public RelationSC[] getListRelations();

}
