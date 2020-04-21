package com.bytmasoft.domain.model.interfaces;

import java.io.Serializable;

public interface IByName<T> extends Serializable {

	T findByName(final String name);

}
