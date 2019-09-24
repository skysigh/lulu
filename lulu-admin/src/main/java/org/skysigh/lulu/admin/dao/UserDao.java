package org.skysigh.lulu.admin.dao;

import org.apache.ibatis.session.SqlSession;
import org.skysigh.lulu.admin.po.User;

public interface UserDao {
	User getUserById(long id);

	void setSqlSession(SqlSession sqlSession);

	void add(User user);

	void delete(long[] userIds);

	User findUserByAccount(String account);
}
