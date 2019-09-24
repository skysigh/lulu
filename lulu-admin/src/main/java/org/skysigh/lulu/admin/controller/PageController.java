package org.skysigh.lulu.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "*.do" })
public class PageController extends BaseServlet {

	private static final long serialVersionUID = 4819080878675512459L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleUri(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	// 分发请求
	private void handleUri(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] split = uri.split("/");
		String method = split[split.length - 1];
		if ("index.do".equals(method)) {
			index(request, response);
		} else {
			throw new RuntimeException("为找到对应页面");
		}
	}

	private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/page/index.html").forward(request, response);
	}
}
