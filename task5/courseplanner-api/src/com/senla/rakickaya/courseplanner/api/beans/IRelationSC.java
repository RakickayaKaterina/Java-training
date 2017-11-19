package com.senla.rakickaya.courseplanner.api.beans;

public interface IRelationSC extends IEntity {
	public IStudent getStudent();

	public void setStudent(IStudent pStudent);

	public ICourse getCourse();

	public void setCourse(ICourse mCourse);
}
