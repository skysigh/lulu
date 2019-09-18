package org.skysigh.lulu.admin.service;

import org.apache.ibatis.session.SqlSession;
import org.skysigh.lulu.admin.po.User;

public interface UserService {
	User getUserById(long id);

	void setSqlSession(SqlSession sqlSession);

	void add(User user);

	void delete(long[] userIds);
}
