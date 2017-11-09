package com.senla.rakickaya.controller.api.repositories;

import java.util.List;

import com.senla.rakickaya.model.beans.Lector;

public interface ILectorsRepository {
	public void addLector(Lector pLector);

	public Lector removeLector(long pId);

	public void updateLector(Lector pLector);

	public Lector getLector(long pId);

	public List<Lector> getListLectors();

}
