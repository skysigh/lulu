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
}
