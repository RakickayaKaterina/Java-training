package com.senla.rakickaya.courseplanner.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.log4j.Logger;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.dao.ICourseDao;
import com.senla.rakickaya.courseplanner.api.dao.IStudentDao;
import com.senla.rakickaya.courseplanner.api.services.IStudentsService;
import com.senla.rakickaya.courseplanner.beans.Student;
import com.senla.rakickaya.courseplanner.csv.converters.ConverterFromCsv;
import com.senla.rakickaya.courseplanner.csv.converters.ConverterToCsv;
import com.senla.rakickaya.courseplanner.csv.converters.entities.CsvResponse;
import com.senla.rakickaya.courseplanner.dao.connection.DbConnector;
import com.senla.rakickaya.courseplanner.dependency.ServiceDI;
import com.senla.rakickaya.courseplanner.utils.FileWorker;

public class StudentsService implements IStudentsService {
	private static final Logger logger = Logger.getLogger(StudentsService.class.getName());

	private IStudentDao studentDao;

	public StudentsService() {
		super();
	}

	@Override
	public void addStudent(IStudent pStudent) {
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			studentDao.create(pStudent, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public void removeStudent(long id) {
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			studentDao.delete(id, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void updateStudent(IStudent pStudent) {
		Connection connection = DbConnector.getInstance().getConnection();
		try {
			studentDao.update(pStudent, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public IStudent getStudent(long pId) {
		Connection connection = DbConnector.getInstance().getConnection();
		IStudent student = null;
		try {
			student = studentDao.findById(pId, connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return student;
	}

	@Override
	public List<IStudent> getStudents() {
		Connection connection = DbConnector.getInstance().getConnection();
		List<IStudent> students = null;
		try {
			students = studentDao.getAllEntities(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return students;
	}

	@Override
	public void exportCSV(String path) {
		FileWorker worker = new FileWorker(path);
		List<String> csvEntities = new ArrayList<>();
		List<IStudent> students = getStudents();
		for (IStudent student : students) {
			try {
				String csvString;
				csvString = ConverterToCsv.convert(student);
				csvEntities.add(csvString);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		}
		worker.write(csvEntities);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void importCSV(String path) throws SQLException {
		Connection connection = DbConnector.getInstance().getConnection();
		ICourseDao courseDao = (ICourseDao) ServiceDI.getInstance().getObject(ICourseDao.class);
		final String COURSES = "courses";

		List<IStudent> students = new ArrayList<>();
		try {
			connection.setAutoCommit(false);
			FileWorker worker = new FileWorker(path);
			List<String> list = worker.read();
			for (String str : list) {
				CsvResponse response = ConverterFromCsv.convert(str, Student.class);
				IStudent student = (IStudent) response.getEntity();
				Map<String, Object> map = response.getRelation();
				if (map.containsKey(COURSES)) {
					List<Long> idCourses = (List<Long>) map.get(COURSES);
					List<ICourse> courses = new ArrayList<>();
					for (Long idC : idCourses) {
						ICourse course = courseDao.findById(idC, connection);
						if (course != null) {
							courses.add(course);
						}
					}
					student.setCourses(courses);
				}
				students.add(student);
			}
			Set<Long> maps = new HashSet<>();
			List<IStudent> existStudent = studentDao.getAllEntities(connection);
			existStudent.forEach(new Consumer<IStudent>() {
				@Override
				public void accept(IStudent t) {
					maps.add(t.getId());
				}
			});

			for (IStudent student : students) {
				if (maps.contains(student.getId())) {
					studentDao.update(student, connection);
				} else {
					studentDao.create(student, connection);
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
	public int getTotalCountStudents() {
		Connection connection = DbConnector.getInstance().getConnection();
		int result = 0;
		try {
			result = studentDao.getCount(connection);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}
