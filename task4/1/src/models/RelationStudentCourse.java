package models;

public class RelationStudentCourse extends Entity {
	private long id;
	private Course course;
	private Student student;

	public RelationStudentCourse(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public Course getCourse() {
		return course;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

}
