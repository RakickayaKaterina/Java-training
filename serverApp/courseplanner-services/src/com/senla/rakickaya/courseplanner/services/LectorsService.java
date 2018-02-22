package com.senla.rakickaya.courseplanner.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.beans.ILector;
import com.senla.rakickaya.courseplanner.api.dao.ILectorDao;
import com.senla.rakickaya.courseplanner.api.dao.enums.SortColumnLector;
import com.senla.rakickaya.courseplanner.api.services.ILectorsService;
import com.senla.rakickaya.courseplanner.beans.Lector;
import com.senla.rakickaya.courseplanner.csv.converters.ConverterFromCsv;
import com.senla.rakickaya.courseplanner.csv.converters.ConverterToCsv;
import com.senla.rakickaya.courseplanner.csv.converters.entities.CsvResponse;
import com.senla.rakickaya.courseplanner.dao.connection.DbConnector;
import com.senla.rakickaya.courseplanner.dependency.ServiceDI;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;
import com.senla.rakickaya.courseplanner.utils.FileWorker;

public class LectorsService implements ILectorsService {
	private static final Logger logger = Logger.getLogger(LectorsService.class.getName());

	private ILectorDao lectorDao = (ILectorDao) ServiceDI.getInstance().getObject(ILectorDao.class);

	public LectorsService() {
		super();
	}

	@Override
	public void addLector(ILector pLector) {
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			lectorDao.create(pLector, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void removeLector(long pId) throws EntityNotFoundException {
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			lectorDao.delete(pId, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void updateLector(ILector pLector) {
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			lectorDao.update(pLector, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public ILector getLector(long id) {
		Connection connection = DbConnector.getInstance().getConnection();
		ILector lector = null;
		try {
			lector = lectorDao.findById(id, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return lector;
	}

	@Override
	public List<ILector> getLectors() {
		Connection connection = DbConnector.getInstance().getConnection();
		List<ILector> lectors = null;
		try {
			lectors = lectorDao.getAllEntities(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return lectors;
	}

	@Override
	public List<ILector> getLectorsWithCourses() {
		Connection connection = DbConnector.getInstance().getConnection();
		List<ILector> lectors = null;
		try {
			lectors = lectorDao.getLectorsWithCourses(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return lectors;
	}

	@Override
	public int getCountCoursesByLector(long idLector) {
		Connection connection = DbConnector.getInstance().getConnection();
		int countCourses = 0;
		try {
			countCourses = lectorDao.getCountCoursesByLector(idLector, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return countCourses;
	}

	@Override
	public List<ILector> getSortedList(SortColumnLector column) {
		/*
		 * List<ILector> listLectors = mRepositoryLectors.getLectors();
		 * listLectors.sort(comparator); return listLectors;
		 */
		Connection connection = DbConnector.getInstance().getConnection();
		List<ILector> lectors = null;
		try {
			lectors = lectorDao.getSortedLectors(column, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return lectors;
	}

	@Override
	public Map<ILector, Integer> getLectorsInformation() {
		Map<ILector, Integer> map = new LinkedHashMap<>();
		List<ILector> lectors = getLectorsWithCourses();
		lectors.sort(new Comparator<ILector>() {
			@Override
			public int compare(ILector o1, ILector o2) {
				Integer countCources = o1.getCourses().size();
				return countCources.compareTo(o2.getCourses().size());
			}
		});
		for (ILector lector : lectors) {
			map.put(lector, getCountCoursesByLector(lector.getId()));

		}
		return map;
	}

	@Override
	public void exportCSV(String path) throws Exception {
		FileWorker worker = new FileWorker(path);
		List<String> csvEntities = new ArrayList<>();
		List<ILector> lectors = getLectors();
		for (ILector lector : lectors) {
			try {
				String csvString;
				csvString = ConverterToCsv.convert(lector);
				csvEntities.add(csvString);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		worker.write(csvEntities);
	}

	@Override
	public void importCSV(String path) throws SQLException {
		List<ILector> csvReadLectors = new ArrayList<>();
		ServiceDI serviceDI = ServiceDI.getInstance();
		ILectorDao lectorDao = (ILectorDao) serviceDI.getObject(ILectorDao.class);
		Connection connection = DbConnector.getInstance().getConnection();

		try {
			connection.setAutoCommit(false);
			FileWorker worker = new FileWorker(path);
			List<String> list = worker.read();
			for (String str : list) {
				CsvResponse response = ConverterFromCsv.convert(str, Lector.class);
				ILector lector = (ILector) response.getEntity();
				csvReadLectors.add(lector);
			}

			Set<Long> maps = new HashSet<>();
			List<ILector> existLectors = lectorDao.getAllEntities(connection);
			existLectors.forEach(new Consumer<ILector>() {
				@Override
				public void accept(ILector t) {
					maps.add(t.getId());
				}
			});

			for (ILector lector : csvReadLectors) {
				if (maps.contains(lector.getId())) {
					lectorDao.update(lector, connection);
				} else {
					lectorDao.create(lector, connection);
				}
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			logger.error(e.getMessage());
			connection.rollback();
		}

	}

	@Override
	public int getTotalCountLectors() {
		Connection connection = DbConnector.getInstance().getConnection();
		int result = 0;
		try {
			result = lectorDao.getCount(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
