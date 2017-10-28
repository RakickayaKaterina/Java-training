package repositories;

import models.RelationStudentCourse;
import utils.ArrayWorker;

public class RepositoryStudentCourse implements IRepositoryStudentCourse {
	private RelationStudentCourse[] mRelations;

	public RepositoryStudentCourse(int countRelations) {
		super();
		mRelations = new RelationStudentCourse[countRelations];
	}

	@Override
	public void addStudentToCourse(RelationStudentCourse pRelation) {
		ArrayWorker.addToArray(pRelation, mRelations);

	}

	@Override
	public RelationStudentCourse removeStudentFromCourse(long pId) {
		return (RelationStudentCourse) ArrayWorker.removeFromArray(pId, mRelations);
	}

	@Override
	public void updateStudentFromCourse(RelationStudentCourse pRelation) {
		ArrayWorker.updatePosition(pRelation, mRelations);

	}

	@Override
	public RelationStudentCourse getRelationStudentCourse(long pId) {
		int position = ArrayWorker.getPositionById(pId, mRelations);
		if (position > 0) {
			return mRelations[position];
		}
		return null;
	}

	@Override
	public RelationStudentCourse[] getListRelationStudentCourse() {
		return mRelations;
	}

	@Override
	public void saveState() {
		// TODO Auto-generated method stub

	}

}
