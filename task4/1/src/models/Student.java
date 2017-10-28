package models;

public class Student extends Entity {
	private long id;
	private String nameStudent;

	public Student(long id, String nameStudent) {
		super();
		this.id = id;
		this.nameStudent = nameStudent;
	}
	@Override
	public long getId() {
		return id;
	}
	@Override
	public void setId(long id) {
		this.id = id;
	}


	public String getNameStudent() {
		return nameStudent;
	}

}
