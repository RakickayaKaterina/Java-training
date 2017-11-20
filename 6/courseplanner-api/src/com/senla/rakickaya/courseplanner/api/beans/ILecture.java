package com.senla.rakickaya.courseplanner.api.beans;

public interface ILecture extends IEntity, Cloneable {
	public String getName();
	
	public ILecture clone() throws CloneNotSupportedException ;
}
