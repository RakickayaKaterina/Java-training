package com.senla.rakickaya.courseplanner.api.beans;

import java.util.Date;

public interface ILesson extends IEntity {
	ILecture getLecture();

	void setLecture(ILecture mLecture);

	Date getDate();

	int getCountStudent();

	void setCountStudent(int countStudent);
}
