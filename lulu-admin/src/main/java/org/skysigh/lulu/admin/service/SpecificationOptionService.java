package org.skysigh.lulu.admin.service;

import java.util.List;

import org.skysigh.lulu.admin.po.SpecificationOption;

public interface SpecificationOptionService extends SqlService {
	SpecificationOption getById(long id);

	List<SpecificationOption> getAll();

	void add(SpecificationOption specification);

	void update(SpecificationOption specification);

	void delete(long[] ids);
}
