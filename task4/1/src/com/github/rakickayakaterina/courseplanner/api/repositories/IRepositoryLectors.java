package com.github.rakickayakaterina.courseplanner.api.repositories;

import com.github.rakickayakaterina.courseplanner.beans.Lector;

public interface IRepositoryLectors {
	public void addLector(Lector pLector);

	public Lector removeLector(long pId);

	public void updateLector(Lector pLector);

	public Lector getLector(long pId);

	public Lector[] getListLectors();
	
}
