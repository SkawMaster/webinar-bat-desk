package com.bat.architecture.common.entity;

import java.io.Serializable;

public abstract class AbstractModelBean implements Serializable {

	private static final long serialVersionUID = -2144792308686825172L;

	@Override
	public abstract int hashCode();
	
	@Override
	public abstract boolean equals(Object object);
	
	@Override
	public abstract String toString();
	
}
