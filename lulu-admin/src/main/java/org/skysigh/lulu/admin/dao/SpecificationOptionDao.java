package org.skysigh.lulu.admin.dao;

import java.util.List;

import org.skysigh.lulu.admin.po.SpecificationOption;

public interface SpecificationOptionDao {
	SpecificationOption getById(long id);

	List<SpecificationOption> getAll();

	void add(SpecificationOption specification);

	void update(SpecificationOption specification);

	void delete(long[] ids);

	List<SpecificationOption> getSpeOption(long id);

	void deleteBySpecId(long[] specIds);
}
