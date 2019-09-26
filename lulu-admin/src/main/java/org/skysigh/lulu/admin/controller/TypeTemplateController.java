package org.skysigh.lulu.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skysigh.lulu.admin.po.TypeTemplate;
import org.skysigh.lulu.admin.result.QueryParam;
import org.skysigh.lulu.admin.result.QueryResult;
import org.skysigh.lulu.admin.result.ResultModel;
import org.skysigh.lulu.admin.service.TypeTemplateService;
import org.skysigh.lulu.admin.service.impl.TypeTemplateServiceImpl;

import com.alibaba.fastjson.JSON;

@WebServlet(urlPatterns = { "/typeTemplate/*" })
public class TypeTemplateController extends BaseServlet {

	private static final long serialVersionUID = -2784540577732878L;

	private TypeTemplateService typeTemplateService;

	@Override
	public void init() throws ServletException {
		typeTemplateService = new TypeTemplateServiceImpl(BaseServlet.session);
	}

	@Override
	protected void handleUri(String method, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch (method) {
		case "getAllTypeTemplate.do":
			getAll(request, response);
			break;
		case "addTypeTemplate.do":
			add(request, response);
			break;
		case "updateTypeTemplate.do":
			update(request, response);
			break;
		case "deleteTypeTemplate.do":
			delete(request, response);
			break;
		case "queryTypeTemplate.do":
			query(request, response);
			break;

		default:
			break;
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idStr = getParam("idsStr", request);
		checkParamNull(idStr);
		String[] idStrArr = idStr.split(",");
		int length = idStrArr.length;
		long[] ids = new long[length];
		ResultModel rm = new ResultModel();
		try {
			for (int i = 0; i < length; i++) {
				ids[i] = Long.parseLong(idStrArr[i]);
			}
		} catch (NumberFormatException e) {
			rm.setCode(ResultModel.ERROR).setMsg(e.getMessage());
			writeJsonToResp(response, rm);
			return;
		}
		try {
			typeTemplateService.delete(ids);
		} catch (Exception e) {
			rm.setCode(ResultModel.ERROR).setMsg(e.getMessage());
		}
		writeJsonToResp(response, rm);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String typeTemplateStr = getParam("typeTemplateStr", request);
		checkParamNull(typeTemplateStr);
		ResultModel rm = new ResultModel();
		try {
			TypeTemplate parseObject = JSON.parseObject(typeTemplateStr, TypeTemplate.class);
			typeTemplateService.update(parseObject);
		} catch (Exception e) {
			e.printStackTrace();
			rm.setCode(ResultModel.ERROR).setMsg(e.getMessage());
		}
		writeJsonToResp(response, rm);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String typeTemplateStr = getParam("typeTemplateStr", request);
		checkParamNull(typeTemplateStr);
		ResultModel rm = new ResultModel();
		try {
			TypeTemplate parseObject = JSON.parseObject(typeTemplateStr, TypeTemplate.class);
			typeTemplateService.add(parseObject);
		} catch (Exception e) {
			rm.setCode(ResultModel.ERROR).setMsg(e.getMessage());
		}
		writeJsonToResp(response, rm);
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		writeJsonToResp(response, new ResultModel().setData(typeTemplateService.getAll()));
	}
	
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = getParam("search", request);
		String sort = getParam("sort", request);
		String order = getParam("order", request);
		String offset = getParam("offset", request);
		checkParamNull(offset);
		String limit = getParam("limit", request);
		checkParamNull(limit);
		QueryParam queryParam = new QueryParam(search, sort, order, Integer.parseInt(offset), Integer.parseInt(limit));
		System.err.println(queryParam);
		QueryResult<TypeTemplate> query = typeTemplateService.query(queryParam);
		ResultModel rm = new ResultModel();
		rm.setData(query);
		writeJsonToResp(response, rm);
	}
}
