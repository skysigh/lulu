package org.skysigh.lulu.admin.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.skysigh.lulu.admin.dao.UserDao;
import org.skysigh.lulu.admin.po.User;
import org.skysigh.lulu.admin.service.UserService;

public class UserServiceImpl implements UserService {
	
	
	private UserDao userDao;
	
	private SqlSession sqlSession;
	
	public UserServiceImpl(SqlSession sqlSession) {
		this.userDao = sqlSession.getMapper(UserDao.class);
		this.sqlSession = sqlSession;
	}

	@Override
	public User getUserById(long id) {
		return userDao.getUserById(id);
	}

	@Override
	public void add(User user) {
		userDao.add(user);
		sqlSession.commit();
	}

	@Override
	public void delete(long[] userIds) {
		userDao.delete(userIds);
		sqlSession.commit();
	}

	@Override
	public User checkUser(String account, String password) {
		User user = userDao.findUserByAccount(account);
		if(user == null) {
			return null;
		}
		User clone = user.clone();
		if(password.equals(clone.getPassword())) {
			clone.setPassword("");
			return clone;
		}
		return null;
	}

}
