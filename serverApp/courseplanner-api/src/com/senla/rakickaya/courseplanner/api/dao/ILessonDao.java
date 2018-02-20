package com.senla.rakickaya.courseplanner.api.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.senla.rakickaya.courseplanner.api.beans.ILesson;
import com.senla.rakickaya.courseplanner.api.dao.enums.SortColumnLesson;

public interface ILessonDao extends EntityDao<ILesson, Long>{

	void deleteLessonByLecture(Long idLecture, Connection connection) throws Exception;

	List<ILesson> getSortedLessons(SortColumnLesson column, Connection connection) throws Exception;

	List<ILesson> getAllEntities(Date date, Connection connection) throws Exception;

}
