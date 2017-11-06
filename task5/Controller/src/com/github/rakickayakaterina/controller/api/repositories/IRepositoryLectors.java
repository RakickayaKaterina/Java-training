package com.github.rakickayakaterina.controller.api.repositories;

import java.util.List;

import com.github.rakickayakaterina.model.beans.Lector;

public interface IRepositoryLectors {
	public void addLector(Lector pLector);

	public Lector removeLector(long pId);

	public void updateLector(Lector pLector);

	public Lector getLector(long pId);

	public List<Lector> getListLectors();

}
