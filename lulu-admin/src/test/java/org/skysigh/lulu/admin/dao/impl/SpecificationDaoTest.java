package org.skysigh.lulu.admin.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.skysigh.lulu.admin.dao.SpecificationDao;
import org.skysigh.lulu.admin.po.Specification;
import org.skysigh.lulu.admin.result.QueryParam;

public class SpecificationDaoTest {
	private SpecificationDao specificationDao;
	private SqlSession session;

	@Before
	public void setUp() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(in);
		session = factory.openSession();
		specificationDao = session.getMapper(SpecificationDao.class);
	}

	@Test
	public void getAll_test() throws IOException {
		List<Specification> all = specificationDao.getAll();
		System.out.println(all);
	}

	@Test
	public void count_test() throws IOException {
		specificationDao.count();
	}

	@Test
	public void query_test() throws IOException {
		QueryParam queryParam = new QueryParam("1", "spec_name", "desc", 0, 10);
		List<Specification> query = specificationDao.query(queryParam);
		query.forEach(k -> System.err.println(k));
	}
}
