package org.skysigh.lulu.admin.dao.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.skysigh.lulu.admin.dao.UserDao;
import org.skysigh.lulu.admin.po.User;

public class UserDaoTest {
	
	private UserDao userDao;
	private SqlSession session;
	
	@Before
	public void setUp() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(in);
		session = factory.openSession();
		userDao = session.getMapper(UserDao.class);
		//userDao = new UserDaoImpl();
		//userDao.setSqlSession(session);
	}
	
	@Test
	public void getUserById_test() {
		long id = 1L;
		User user = userDao.getUserById(id);
		if(user != null) {
			long actualId = user.getId();
			Assert.assertEquals(id, actualId);
		}
	}
	
	@Test
	public void add_test() {
		User user = new User("hf", "123456");
		userDao.add(user);
		session.commit();
	}

}
