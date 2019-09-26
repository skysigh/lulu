package org.skysigh.lulu.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "*.do" })
public class PageController extends BaseServlet {

	private static final long serialVersionUID = 4819080878675512459L;

	// 分发请求
	protected void handleUri(HttpServletRequest request, HttpServletResponse response)
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
