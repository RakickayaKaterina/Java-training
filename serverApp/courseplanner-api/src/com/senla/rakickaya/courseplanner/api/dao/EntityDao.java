package com.senla.rakickaya.courseplanner.api.dao;

import java.sql.Connection;
import java.util.List;

public interface EntityDao<Entity, Id> {
	void create(Entity entity, Connection connection) throws Exception;

	void delete(Id idEntity, Connection connection) throws Exception;

	void update(Entity entity, Connection connection) throws Exception;

	List<Entity> getAllEntities(Connection connection) throws Exception;

	Entity findById(Id idEntity, Connection connection) throws Exception;
}
