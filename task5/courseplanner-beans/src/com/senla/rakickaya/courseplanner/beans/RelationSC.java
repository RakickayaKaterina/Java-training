package com.senla.rakickaya.courseplanner.beans;

import com.senla.rakickaya.courseplanner.api.beans.ICourse;
import com.senla.rakickaya.courseplanner.api.beans.IRelationSC;
import com.senla.rakickaya.courseplanner.api.beans.IStudent;

public class RelationSC implements IRelationSC {
	private long id;
	private IStudent mStudent;
	private ICourse mCourse;

	public RelationSC(long id, IStudent mStudent, ICourse mCourse) {
		super();
		this.id = id;
		this.mStudent = mStudent;
		this.mCourse = mCourse;
	}

	@Override
	public long getId() {
		return id;

	}

	@Override
	public void setId(long id) {
		this.id = id;

	}

	public IStudent getStudent() {
		return mStudent;
	}

	public void setStudent(IStudent pStudent) {
		this.mStudent = pStudent;
	}

	public ICourse getCourse() {
		return mCourse;
	}

	public void setCourse(ICourse mCourse) {
		this.mCourse = mCourse;
	}
}
