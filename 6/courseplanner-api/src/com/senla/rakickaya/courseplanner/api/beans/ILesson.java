package com.senla.rakickaya.courseplanner.api.beans;

import java.util.Date;

public interface ILesson extends IEntity {
	public ILecture getLecture();

	public void setLecture(ILecture mLecture);

	public Date getDate();

	public int getCountStudent();

	public void setCountStudent(int countStudent);
}
