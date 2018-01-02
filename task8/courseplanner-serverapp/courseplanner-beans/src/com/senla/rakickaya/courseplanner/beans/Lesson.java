package com.senla.rakickaya.courseplanner.beans;

import java.util.Date;

import com.senla.rakickaya.courseplanner.api.beans.ILecture;
import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.csv.CsvEntity;
import com.senla.rakickaya.courseplanner.csv.CsvProperty;
import com.senla.rakickaya.courseplanner.csv.PropertyType;
import com.senla.rakickaya.courseplanner.utils.DateWorker;
@CsvEntity(entityId = "id", filename = "lessons.csv")
public class Lesson implements ILesson {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3315388805589371881L;
	@CsvProperty(columnNumber = 0, propertyType = PropertyType.SimpleProperty)
	private long id;
	@CsvProperty(columnNumber = 1, propertyType = PropertyType.CompositeProperty, keyField="idLecture")
	private ILecture mLecture;
	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SimpleProperty)
	private Date mDate;
	@CsvProperty(columnNumber = 3, propertyType = PropertyType.SimpleProperty)
	private int countStudent;

	
	
	public Lesson() {
		super();
	}

	public Lesson(long id, ILecture mLecture, Date pDate, int countStudent) {
		super();
		this.id = id;
		this.mLecture = mLecture;
		this.mDate = pDate;
		this.countStudent = countStudent;
	}

	public int getCountStudent() {
		return countStudent;
	}

	public void setCountStudent(int countStudent) {
		this.countStudent = countStudent;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public ILecture getLecture() {
		return mLecture;
	}

	public void setLecture(ILecture mLecture) {
		this.mLecture = mLecture;
	}

	public Date getDate() {
		return mDate;
	}

	@Override
	public String toString() {
		return String.format("Lesson [id=%s, mLectureName=%s, mDate=%s]", id, mLecture.getName(),
				DateWorker.dateFormat.format(mDate));
	}

}
