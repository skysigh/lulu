package org.skysigh.lulu.admin.service;

import java.util.List;

public interface BaseService<T> extends SqlService {

	T getById(long id);

	List<T> getAll();

	void add(T t);

	void update(T t);

	void delete(long[] ids);

}
