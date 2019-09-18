package org.skysigh.lulu.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected static SqlSession session;
	private String resourcePath = "mybatis-config.xml";

	@Override
	public void init() throws ServletException {
		if (session != null) {
			return;
		}
		try {
			InputStream resourceAsStream = Resources.getResourceAsStream(resourcePath);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
			session = factory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		session.close();
	}

	protected void sendMsgForResponse(HttpServletResponse response, String info) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.append(info);
	}

	String getParam(String key, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String parameter = request.getParameter(key);
		if (parameter == null) {
			return null;
		}
		return new String(parameter.getBytes("ISO8859-1"), "UTF-8");
	}

}
