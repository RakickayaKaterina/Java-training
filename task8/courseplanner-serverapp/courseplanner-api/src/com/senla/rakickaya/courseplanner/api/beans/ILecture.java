package com.senla.rakickaya.courseplanner.api.beans;

public interface ILecture extends IEntity, Cloneable {
	String getName();

	ILecture clone() throws CloneNotSupportedException;
}
