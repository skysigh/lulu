package org.skysigh.lulu.admin.service;

import java.util.List;

import org.skysigh.lulu.admin.po.Specification;
import org.skysigh.lulu.admin.po.SpecificationOption;
import org.skysigh.lulu.admin.result.QueryParam;
import org.skysigh.lulu.admin.result.QueryResult;
import org.skysigh.lulu.admin.vo.SpecificationVo;

public interface SpecificationService extends SqlService {
	Specification getById(long id);

	List<Specification> getAll();

	void add(Specification specification);

	void update(Specification specification);

	void delete(long[] ids);

	List<SpecificationOption> getSpeOption(long parseLong);

	void add(SpecificationVo specificationVo);
	void update(SpecificationVo specificationVo);

	QueryResult<Specification> query(QueryParam queryParam);
}
