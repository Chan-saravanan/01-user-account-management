package com.app.services.contracts;

import java.util.List;

public interface CRUDService<T,P> {
	List<?> listAll();
	T getById(P id);
	T saveOrUpdate(T domainObject);
	void delete(T domainObject);
}
