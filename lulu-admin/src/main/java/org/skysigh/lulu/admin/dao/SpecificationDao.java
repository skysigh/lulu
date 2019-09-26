package org.skysigh.lulu.admin.dao;

import java.util.List;

import org.skysigh.lulu.admin.po.Specification;
import org.skysigh.lulu.admin.result.QueryParam;

public interface SpecificationDao {
	Specification getById(long id);

	List<Specification> getAll();

	void add(Specification specification);

	void update(Specification specification);

	void delete(long[] ids);

	List<Specification> query(QueryParam queryParam);

	int count();
}
