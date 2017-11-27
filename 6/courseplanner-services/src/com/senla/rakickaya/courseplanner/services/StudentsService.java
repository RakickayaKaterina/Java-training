package com.senla.rakickaya.courseplanner.services;

import java.util.ArrayList;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;
import com.senla.rakickaya.courseplanner.api.repositories.ICoursesRepository;
import com.senla.rakickaya.courseplanner.api.repositories.IStudentsRepository;
import com.senla.rakickaya.courseplanner.api.services.IStudentsService;
import com.senla.rakickaya.courseplanner.csv.CSVConverter;
import com.senla.rakickaya.courseplanner.csv.CSVObject;
import com.senla.rakickaya.courseplanner.csv.CSVWorker;
import com.senla.rakickaya.courseplanner.exception.EntityNotFoundException;
import com.senla.rakickaya.courseplanner.repositories.CoursesRepository;
import com.senla.rakickaya.courseplanner.repositories.StudentsRepository;
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
		List<ICourse> courses = mRepositoryCourses.getListCourses();
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
	public List<IStudent> getListStudents() {
		return mRepositoryStudents.getListStudents();
	}

	@Override
	public void exportCSV(String path) {
		CSVWorker worker = new CSVWorker(path);
		List<IStudent> students = mRepositoryStudents.getListStudents();
		List<CSVObject> objects = new ArrayList<CSVObject>();
		for (IStudent student : students) {
			objects.add(CSVObject.valueOf(student));
		}
		worker.writeCSV(objects);
	}

	@Override
	public void importCSV(String path) {
		CSVWorker worker = new CSVWorker(path);
		List<CSVObject> objects = worker.readCSV();
		List<IStudent> students = new ArrayList<>();
		for (CSVObject obj : objects) {
			students.add(CSVConverter.parseStudent(obj, mRepositoryCourses.getListCourses()));
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
		return mRepositoryStudents.getListStudents().size();
	}
}
