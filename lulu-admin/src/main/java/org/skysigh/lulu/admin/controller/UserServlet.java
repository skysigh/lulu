package org.skysigh.lulu.admin.controller;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skysigh.lulu.admin.po.User;
import org.skysigh.lulu.admin.result.ResultModel;
import org.skysigh.lulu.admin.service.UserService;
import org.skysigh.lulu.admin.service.impl.UserServiceImpl;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		super.init(config);
	}

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
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserServlet/login?account=" + user.getUsername() + "&password=" + user.getPassword());
		requestDispatcher.forward(request, response);
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account = getParam("account", request);
		String password = getParam("password", request);
		if (account == null || "".equals(account)) {
			throw new IllegalArgumentException("账号不能为空");
		}
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("密码不能为空");
		}
		userService.add(new User(account, password));
		sendMsgForResponse(response, "成功");
	}

	// 分发请求
	private void handleUri(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		System.err.println(contextPath +"*" + servletPath +"*"+ pathInfo);
		String[] split = uri.split("/");
		String method = split[split.length - 1];
		if ("getUserById".equals(method)) {
			getUserById(request, response);
		} else if ("add".equals(method)) {
			addUser(request, response);
		} else if ("delete".equals(method)) {
			deleteUser(request, response);
		} else if ("login.do".equals(method)) {
			loginUser(request, response);
		} else {
			throw new RuntimeException("未找到404");
		}
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String account = getParam("account", request);
		checkParamNull(account);
		String password = getParam("password", request);
		checkParamNull(password);
		User user = userService.checkUser(account, password);
		ResultModel rm = new ResultModel();
		if(user == null) {
			rm.setCode(ResultModel.ERROR).setMsg("密码或者账号错误");
		} else {
			rm.setCode(ResultModel.JUMP).setUrl("page/index.do");
		}
		String jsonString = getJsonString(rm);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append(jsonString);
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids = request.getParameter("ids");
		if (ids == null) {
			throw new IllegalArgumentException("ids为空");
		}
		String[] split = ids.split(",");
		int length = split.length;
		long[] userIds = new long[length];
		for (int i = 0; i < length; i++) {
			userIds[i] = Long.parseLong(split[i]);
		}
		userService.delete(userIds);
		sendMsgForResponse(response, "成功");
	}

}
