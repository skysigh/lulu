package org.skysigh.lulu.admin.service;

import org.skysigh.lulu.admin.po.User;

public interface UserService extends SqlService {
	User getUserById(long id);

	void add(User user);

	void delete(long[] userIds);

	User checkUser(String account, String password);
}
