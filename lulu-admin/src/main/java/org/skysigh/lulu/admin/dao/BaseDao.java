package org.skysigh.lulu.admin.dao;

import java.util.List;

public interface BaseDao<T> {
	T getById(long id);

	List<T> getAll();

	void add(T specification);

	void update(T specification);

	void delete(long[] ids);
}
