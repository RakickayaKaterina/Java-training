package com.senla.rakickaya.courseplanner.api.beans;

import java.io.Serializable;

public interface IEntity extends Serializable {
	abstract long getId();

	abstract void setId(long id);
}
