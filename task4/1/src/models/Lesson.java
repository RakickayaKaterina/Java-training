package models;

import java.util.Date;

import utils.DateWorker;

public class Lesson extends Entity {
	private long id;
	private Lecture mLecture;
	private Date mDate;

	public Lesson(Lecture pLecture, Date pDate) {
		super();
		this.mLecture = pLecture;
		this.mDate = pDate;
	}
	@Override
	public long getId() {
		return id;
	}
	@Override
	public void setId(long id) {
		this.id = id;
	}

	public Lecture getLecture() {
		return mLecture;
	}

	public Date getDate() {
		return mDate;
	}
	@Override
	public String toString() {
		return String.format("Lesson [id=%s, mLectureName=%s, mDate=%s]", id, mLecture.getName(), DateWorker.dateFormat.format(mDate));
	}
	

}
