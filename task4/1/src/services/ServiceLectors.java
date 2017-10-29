package services;

import java.util.Arrays;
import java.util.Comparator;

import models.Lector;
import repositories.IRepositoryLectors;

public class ServiceLectors implements IServiceLectors{
	private IRepositoryLectors mRepositoryLectors;
	private IServiceCourses mServiceCourses;

	public void setServiceCourses(IServiceCourses mServiceCourses) {
		this.mServiceCourses = mServiceCourses;
	}
	public ServiceLectors(IRepositoryLectors mRepositoryLectors) {
		super();
		this.mRepositoryLectors = mRepositoryLectors;
	}
	@Override
	public Lector[] sort(Comparator<Lector> comparator){
		 Lector[] listLectors = mRepositoryLectors.getListLectors();
		 Arrays.sort(listLectors,comparator);
		 return listLectors;
	}
	@Override
	public void addLector(Lector pLector) {
		mRepositoryLectors.addLector(pLector);
		
	}
	@Override
	public void removeLector(long pId) {
		Lector removedLector = mRepositoryLectors.removeLector(pId);
		mServiceCourses.removeLectorFromCourses(removedLector);
		
	}
	@Override
	public void updateLector(Lector pLector) {
		mRepositoryLectors.updateLector(pLector);
		
	}
	@Override
	public Lector getLector(long id) {
		return mRepositoryLectors.getLector(id);
	}
	@Override
	public void saveState() {
		// TODO save
		
	}
	@Override
	public Lector[] getListLectors() {
		return mRepositoryLectors.getListLectors();
	}
	
}
