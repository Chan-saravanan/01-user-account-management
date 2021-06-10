package com.app.entities;

import java.io.Serializable;

public interface BasicEntityAcccessContract<T extends Serializable> {
	T getId();
	void setId(T t);
}
