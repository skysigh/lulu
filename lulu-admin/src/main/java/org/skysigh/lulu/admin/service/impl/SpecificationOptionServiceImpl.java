package org.skysigh.lulu.admin.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.skysigh.lulu.admin.dao.SpecificationOptionDao;
import org.skysigh.lulu.admin.po.SpecificationOption;
import org.skysigh.lulu.admin.service.SpecificationOptionService;

public class SpecificationOptionServiceImpl implements SpecificationOptionService {

	private SqlSession sqlSession;
	private SpecificationOptionDao specificationOptionDao;

	public SpecificationOptionServiceImpl(SqlSession sqlSession) {
		specificationOptionDao = sqlSession.getMapper(SpecificationOptionDao.class);
		this.sqlSession = sqlSession;
	}

	@Override
	public SpecificationOption getById(long id) {
		return specificationOptionDao.getById(id);
	}

	@Override
	public List<SpecificationOption> getAll() {
		return specificationOptionDao.getAll();
	}

	@Override
	public void add(SpecificationOption specification) {
		specificationOptionDao.add(specification);
		sqlSession.commit();
	}

	@Override
	public void update(SpecificationOption specification) {
		specificationOptionDao.update(specification);
		sqlSession.commit();
	}

	@Override
	public void delete(long[] ids) {
		specificationOptionDao.delete(ids);
		sqlSession.commit();
	}

}
