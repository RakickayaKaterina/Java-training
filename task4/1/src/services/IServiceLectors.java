package services;

import java.util.Comparator;

import models.Lector;

public interface IServiceLectors {
	public void addLector(Lector pLector);

	public void removeLector(long pId);

	public void updateLector(Lector pLector);

	public Lector getLector(long id);
	public Lector[] getListLectors();

	public Lector[] sort(Comparator<Lector> pComparator);

	public void saveState();
}
