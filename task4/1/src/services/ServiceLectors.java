package services;

import java.util.Arrays;
import java.util.Comparator;

import models.Lector;
import repositories.IRepositoryLectors;
import utils.ArrayWorker;

public class ServiceLectors implements IServiceLectors {
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
	public Lector[] sort(Comparator<Lector> comparator) {
		Lector[] listLectors = mRepositoryLectors.getListLectors();
		Lector[] pListLector = Arrays.copyOf(listLectors, ArrayWorker.getLenghtArray(listLectors));
		Arrays.sort(pListLector, comparator);
		return pListLector;
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

	@Override
	public void addCourseToLector(long pIdCourse, long pIdLector) {
		Lector[] lectors = mRepositoryLectors.getListLectors();
		for (int i = 0; i < ArrayWorker.getLenghtArray(lectors); i++) {
			if (lectors[i].getId() == pIdLector) {
				ArrayWorker.addToArray(mServiceCourses.getCourse(pIdCourse), lectors[i].getCourses());
				break;
			}
		}

	}

	@Override
	public void removeCourseFromLector(long pIdCourse) {
		Lector[] lectors = mRepositoryLectors.getListLectors();
		for (int i = 0; i < ArrayWorker.getLenghtArray(lectors); i++) {
			ArrayWorker.removeFromArray(pIdCourse, lectors[i].getCourses());
		}

	}

}
