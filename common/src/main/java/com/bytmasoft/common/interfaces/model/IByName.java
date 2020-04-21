package com.bytmasoft.common.interfaces.model;

import java.io.Serializable;

public interface IByName<T> extends Serializable{
	
	T findByName(final String name);

}
