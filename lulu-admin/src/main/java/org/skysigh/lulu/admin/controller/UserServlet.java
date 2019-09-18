package org.skysigh.lulu.admin.controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skysigh.lulu.admin.po.User;
import org.skysigh.lulu.admin.service.UserService;
import org.skysigh.lulu.admin.service.impl.UserServiceImpl;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;

	public UserServlet() {
		userService = new UserServiceImpl(BaseServlet.session);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleUri(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void getUserById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Objects.requireNonNull(id);
		User user = userService.getUserById(Long.parseLong(id));
		if(user == null) {
			sendMsgForResponse(response, null);
		} else {
			sendMsgForResponse(response, user.toString());
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account = getParam("account", request);
		String password = getParam("password", request);
		if (account == null || "".equals(account)) {
			throw new IllegalArgumentException("�˺Ų���Ϊ��");
		}
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("���벻��Ϊ��");
		}
		userService.add(new User(account, password));
		sendMsgForResponse(response, "�ɹ�");
	}

	// �ַ�����
	private void handleUri(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] split = uri.split("/");
		String method = split[split.length - 1];
		if ("getUserById".equals(method)) {
			getUserById(request, response);
		} else if ("add".equals(method)) {
			addUser(request, response);
		} else if ("delete".equals(method)) {
			deleteUser(request, response);
		} else {
			throw new RuntimeException("δ�ҵ�404");
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids = request.getParameter("ids");
		if (ids == null) {
			throw new IllegalArgumentException("idsΪ��");
		}
		String[] split = ids.split(",");
		int length = split.length;
		long[] userIds = new long[length];
		for (int i = 0; i < length; i++) {
			userIds[i] = Long.parseLong(split[i]);
		}
		userService.delete(userIds);
		sendMsgForResponse(response, "�ɹ�");
	}

}
