package org.skysigh.lulu.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skysigh.lulu.admin.po.Specification;
import org.skysigh.lulu.admin.result.ResultModel;
import org.skysigh.lulu.admin.service.SpecificationService;
import org.skysigh.lulu.admin.service.impl.SpecificationServiceImpl;
import org.skysigh.lulu.admin.vo.SpecificationVo;

import com.alibaba.fastjson.JSON;

@WebServlet(urlPatterns = "/specification/*")
public class SpecificationController extends BaseServlet {

	private static final long serialVersionUID = 2387297742695818289L;

	private SpecificationService specificationService;

	@Override
	public void init() throws ServletException {
		specificationService = new SpecificationServiceImpl(BaseServlet.session);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		handleUri(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private void getById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultModel rm = new ResultModel();
		String id = getParam("id", request);
		checkParamNull(id);
		Specification one = specificationService.getById(Long.parseLong(id));
		if (one == null) {
			rm.setCode(ResultModel.ERROR).setMsg("数据丢失");
		} else {
			rm.setData(one);
		}
		writeJsonToResp(response, rm);
	}

	private void getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResultModel rm = new ResultModel();
		List<Specification> all = specificationService.getAll();
		if (all == null) {
			rm.setCode(ResultModel.ERROR).setMsg("数据丢失");
		} else {
			rm.setData(all);
		}
		writeJsonToResp(response, rm);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String specVoStr = getParam("specVoStr", request);
		checkParamNull(specVoStr);
		

		SpecificationVo parseObject = JSON.parseObject(specVoStr, SpecificationVo.class);
		ResultModel rm = new ResultModel();
		if (parseObject == null) {
			rm.setCode(ResultModel.ERROR).setMsg("json解析错误");
		} else {
			try {
				specificationService.add(parseObject);
			} catch (Exception e) {
				rm.setCode(ResultModel.ERROR).setMsg(e.getMessage());
			}
		}
		writeJsonToResp(response, rm);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String specVoStr = getParam("specVoStr", request);
		checkParamNull(specVoStr);
		

		SpecificationVo parseObject = JSON.parseObject(specVoStr, SpecificationVo.class);
		ResultModel rm = new ResultModel();
		if (parseObject == null) {
			rm.setCode(ResultModel.ERROR).setMsg("json解析错误");
		} else {
			try {
				specificationService.update(parseObject);
			} catch (Exception e) {
				rm.setCode(ResultModel.ERROR).setMsg(e.getMessage());
			}
		}
		writeJsonToResp(response, rm);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idsStr = getParam("idsStr", request);
		checkParamNull(idsStr);

		String[] idsStrArr = idsStr.split(",");
		int length = idsStrArr.length;
		long[] ids = new long[length];
		ResultModel rm = new ResultModel();
		try {
			for (int i = 0; i < length; i++) {
				ids[i] = Long.parseLong(idsStrArr[i]);
			}
			specificationService.delete(ids);
		} catch (NumberFormatException e) {
			rm.setCode(ResultModel.ERROR).setMsg(e.getMessage());
		}
		writeJsonToResp(response, rm);
	}
	
	private void getSpeOption(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = getParam("id", request);
		checkParamNull(id);
		ResultModel rm = new ResultModel();
		rm.setData(specificationService.getSpeOption(Long.parseLong(id)));
		writeJsonToResp(response, rm);
	}

	// 分发请求
	private void handleUri(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] split = uri.split("/");
		String method = split[split.length - 1];

		switch (method) {
		case "getSpecificationById.do":
			getById(request, response);
			break;
		case "getAllSpecification.do":
			getAll(request, response);
			break;
		case "addSpecification.do":
			add(request, response);
			break;
		case "updateSpecification.do":
			update(request, response);
			break;
		case "deleteSpecification.do":
			delete(request, response);
			break;
		case "getSpeOption.do":
			getSpeOption(request, response);
			break;
		default:
			throw new RuntimeException("未找到404");
		}
	}

}
