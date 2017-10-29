package repositories;

import models.Lector;

public interface IRepositoryLectors {
	public void addLector(Lector pLector);

	public Lector removeLector(long pId);

	public void updateLector(Lector pLector);

	public Lector getLector(long pId);

	public Lector[] getListLectors();

	public void saveState();
}
