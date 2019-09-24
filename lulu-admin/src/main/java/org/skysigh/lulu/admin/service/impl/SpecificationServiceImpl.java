package org.skysigh.lulu.admin.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.skysigh.lulu.admin.dao.SpecificationDao;
import org.skysigh.lulu.admin.dao.SpecificationOptionDao;
import org.skysigh.lulu.admin.po.Specification;
import org.skysigh.lulu.admin.po.SpecificationOption;
import org.skysigh.lulu.admin.service.SpecificationService;
import org.skysigh.lulu.admin.vo.SpecificationVo;

public class SpecificationServiceImpl implements SpecificationService {
	private SqlSession sqlSession;
	private SpecificationDao specificationDao;
	
	private SpecificationOptionDao specificationOptionDao;

	public SpecificationServiceImpl(SqlSession sqlSession) {
		specificationDao = sqlSession.getMapper(SpecificationDao.class);
		specificationOptionDao = sqlSession.getMapper(SpecificationOptionDao.class);
		this.sqlSession = sqlSession;
	}

	@Override
	public Specification getById(long id) {
		return specificationDao.getById(id);
	}

	@Override
	public List<Specification> getAll() {
		return specificationDao.getAll();
	}

	@Override
	public void add(Specification specification) {
		specificationDao.add(specification);
		sqlSession.commit();
	}

	@Override
	public void update(Specification specification) {
		specificationDao.update(specification);
		sqlSession.commit();
	}

	@Override
	public void delete(long[] ids) {
		specificationDao.delete(ids);
		specificationOptionDao.deleteBySpecId(ids);
		sqlSession.commit();
	}

	@Override
	public List<SpecificationOption> getSpeOption(long id) {
		List<SpecificationOption> speOption = specificationOptionDao.getSpeOption(id);
		if(speOption == null) return Collections.emptyList();
		return speOption;
	}

	@Override
	public void add(SpecificationVo specificationVo) {
		Specification spec = specificationVo.getSpec();
		List<SpecificationOption> specOptions = specificationVo.getSpecOptions();
		specificationDao.add(spec);
		long id = spec.getId();
		for (SpecificationOption specificationOption : specOptions) {
			specificationOption.setSpecId(id);
			specificationOptionDao.add(specificationOption);
		}
		sqlSession.commit();
	}
	
	public void update(SpecificationVo specificationVo) {
		Specification spec = specificationVo.getSpec();
		List<SpecificationOption> specOptions = specificationVo.getSpecOptions();
		specificationDao.update(spec);
		specificationOptionDao.deleteBySpecId(new long[]{spec.getId()});
		long id = spec.getId();
		for (SpecificationOption specificationOption : specOptions) {
			specificationOption.setSpecId(id);
			specificationOptionDao.add(specificationOption);
		}
		sqlSession.commit();
	}

}
