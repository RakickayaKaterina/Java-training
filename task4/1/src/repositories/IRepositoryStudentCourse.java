package repositories;

import models.RelationStudentCourse;

public interface IRepositoryStudentCourse {
	public void addStudentToCourse(RelationStudentCourse pRelation);

	public RelationStudentCourse removeStudentFromCourse(long pId);

	public void updateStudentFromCourse(RelationStudentCourse pRelation);

	public RelationStudentCourse getRelationStudentCourse(long pId);

	public RelationStudentCourse[] getListRelationStudentCourse();

	public void saveState();
}
