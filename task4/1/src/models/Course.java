package models;

import java.util.Date;

public class Course extends Entity {
	private long id;
	private String name;
	private String description;
	private Date startDate;
	private Date endDate;
	private Lector lector;
	private Lecture[] lectures;

	public Course(long id, String name, String description, Date startDate, Date endDate, Lector lector,
			int countLectures) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lector = lector;
		lectures = new Lecture[countLectures];
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public Lector getLector() {
		return lector;
	}

	public Lecture[] getLectures() {
		return lectures;
	}

	

}
