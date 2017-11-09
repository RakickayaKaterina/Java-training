package com.senla.rakickaya.view.dataExchange;

import com.senla.rakickaya.model.beans.Student;
import com.senla.rakickaya.view.launcher.Launcher;

public class RequestConverter {
	private Request mRequest;

	public RequestConverter(Request mRequest) {
		super();
		this.mRequest = mRequest;
	}
	public Student convertToStudent(){
		String studentName = mRequest.getObject("studentName");
		long idStudent = Launcher.getInstance().getGeneratorId().getIdStudent();
		return new Student(idStudent, studentName);
	}
}
