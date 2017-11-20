package com.senla.rakickaya.courseplanner.api.beans;

import java.io.Serializable;

public interface IEntity extends Serializable{
	public abstract long getId();

	public abstract void setId(long id);
}
