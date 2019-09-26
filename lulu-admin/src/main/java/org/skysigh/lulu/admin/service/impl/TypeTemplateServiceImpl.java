package org.skysigh.lulu.admin.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.skysigh.lulu.admin.dao.TypeTemplateDao;
import org.skysigh.lulu.admin.po.TypeTemplate;
import org.skysigh.lulu.admin.result.QueryParam;
import org.skysigh.lulu.admin.result.QueryResult;
import org.skysigh.lulu.admin.service.TypeTemplateService;

public class TypeTemplateServiceImpl implements TypeTemplateService {
	private SqlSession sqlSession;
	private TypeTemplateDao typeTemplateDao;

	public TypeTemplateServiceImpl(SqlSession sqlSession) {
		typeTemplateDao = sqlSession.getMapper(TypeTemplateDao.class);
		this.sqlSession = sqlSession;
	}

	@Override
	public TypeTemplate getById(long id) {
		return typeTemplateDao.getById(id);
	}

	@Override
	public List<TypeTemplate> getAll() {
		return typeTemplateDao.getAll();
	}

	@Override
	public void add(TypeTemplate specification) {
		typeTemplateDao.add(specification);
		sqlSession.commit();
	}

	@Override
	public void update(TypeTemplate specification) {
		typeTemplateDao.update(specification);
		sqlSession.commit();
	}

	@Override
	public void delete(long[] ids) {
		typeTemplateDao.delete(ids);
		sqlSession.commit();
	}

	@Override
	public QueryResult<TypeTemplate> query(QueryParam queryParam) {
		int count = typeTemplateDao.count();
		List<TypeTemplate> rows = typeTemplateDao.query(queryParam);
		QueryResult<TypeTemplate> queryResult = new QueryResult<>();
		queryResult.setAllSize(count);
		queryResult.setData(rows);
		return queryResult;
	}

}
