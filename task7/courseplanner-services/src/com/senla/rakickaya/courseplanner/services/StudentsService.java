package com.senla.rakickaya.courseplanner.services;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.repositories.ICoursesRepository;
import com.senla.rakickaya.courseplanner.api.repositories.IStudentsRepository;
import com.senla.rakickaya.courseplanner.api.services.IStudentsService;
import com.senla.rakickaya.courseplanner.csv.CsvConverter;
import com.senla.rakickaya.courseplanner.csv.CsvObject;
import com.senla.rakickaya.courseplanner.csv.CsvWorker;
import com.senla.rakickaya.courseplanner.csv.converters.ConverterToCsv;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;
import com.senla.rakickaya.courseplanner.repositories.CoursesRepository;
import com.senla.rakickaya.courseplanner.repositories.StudentsRepository;
import com.senla.rakickaya.courseplanner.utils.FileWorker;
import com.senla.rakickaya.courseplanner.utils.GeneratorId;
import com.senla.rakickaya.courseplanner.utils.ListWorker;

public class StudentsService implements IStudentsService {
	private final IStudentsRepository mRepositoryStudents;
	private final ICoursesRepository mRepositoryCourses;

	public StudentsService() {
		super();
		this.mRepositoryStudents = StudentsRepository.getInstance();
		this.mRepositoryCourses = CoursesRepository.getInstance();
	}

	@Override
	public void addStudent(IStudent pStudent) {
		mRepositoryStudents.addStudent(pStudent);

	}

	@Override
	public void removeStudent(long id) throws EntityNotFoundException {
		IStudent removedStudent = mRepositoryStudents.removeStudent(id);
		if (removedStudent == null) {
			throw new EntityNotFoundException();
		}
		List<ICourse> courses = mRepositoryCourses.getCourses();
		for (int i = 0; i < courses.size(); i++) {
			ListWorker.removeItemById(courses.get(i).getStudents(), id);
		}
	}

	@Override
	public void updateStudent(IStudent pStudent) {
		mRepositoryStudents.updateStudent(pStudent);

	}

	@Override
	public IStudent getStudent(long pId) {
		return mRepositoryStudents.getStudent(pId);
	}

	@Override
	public List<IStudent> getStudents() {
		return mRepositoryStudents.getStudents();
	}

	@Override
	public void exportCSV(String path) {
		FileWorker worker = new FileWorker(path);
		List<String> csvEntities = new ArrayList<>();
		List<IStudent> students = mRepositoryStudents.getStudents();
		for (IStudent student : students) {
			try {
				String csvString;
				csvString = ConverterToCsv.convert(student);
				csvEntities.add(csvString);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		worker.write(csvEntities);
	}

	@Override
	public void importCSV(String path) {
		CsvWorker worker = new CsvWorker(path);
		List<CsvObject> objects = worker.readCSV();
		List<IStudent> students = new ArrayList<>();
		for (CsvObject obj : objects) {
			students.add(CsvConverter.parseStudent(obj, mRepositoryCourses.getCourses()));
		}
		for (IStudent student : students) {
			if (!mRepositoryStudents.addStudent(student)) {
				mRepositoryStudents.updateStudent(student);
			}
			else{
				GeneratorId generatorId = GeneratorId.getInstance();
				long id = generatorId.getIdStudent();
				if(student.getId() > id){
					generatorId.setIdStudent(id);
				}
			}
		}
	}
	@Override
	public int getTotalCountStudents() {
		return mRepositoryStudents.getStudents().size();
	}
}
