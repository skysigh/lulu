package org.skysigh.lulu.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.skysigh.lulu.admin.po.Brand;
import org.skysigh.lulu.admin.result.QueryParam;
import org.skysigh.lulu.admin.result.QueryResult;
import org.skysigh.lulu.admin.result.ResultModel;
import org.skysigh.lulu.admin.service.BrandService;
import org.skysigh.lulu.admin.service.impl.BrandServiceImpl;

@WebServlet(urlPatterns = { "/brand/*" })
public class BrandController extends BaseServlet {

	private BrandService brandService;

	private static final long serialVersionUID = 136920397888395772L;

	@Override
	public void init() throws ServletException {
		brandService = new BrandServiceImpl(BaseServlet.session);
	}

	private void getBrandById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultModel rm = new ResultModel();
		String id = getParam("id", request);
		checkParamNull(id);
		Brand brand = brandService.getBrandById(Long.parseLong(id));
		if (brand == null) {
			rm.setCode(ResultModel.ERROR).setMsg("数据丢失");
		} else {
			rm.setData(brand);
		}
		writeJsonToResp(response, rm);
	}

	private void getAllBrand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultModel rm = new ResultModel();
		List<Brand> brands = brandService.getAllBrand();
		if (brands == null) {
			rm.setCode(ResultModel.ERROR).setMsg("数据丢失");
		} else {
			rm.setData(brands);
		}
		writeJsonToResp(response, rm);
	}

	private void addBrand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = getParam("name", request);
		checkParamNull(name);
		String firstChar = getParam("firstChar", request);
		checkParamNull(firstChar);

		Brand brand = new Brand();
		brand.setName(name);
		brand.setFirstChar(firstChar);
		ResultModel rm = new ResultModel();
		try {
			brandService.addBrand(brand);
		} catch (Exception e) {
			rm.setCode(ResultModel.ERROR).setMsg(e.getMessage());
		}
		writeJsonToResp(response, rm);
	}

	private void updateBrand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = getParam("name", request);
		checkParamNull(name);
		String firstChar = getParam("firstChar", request);
		checkParamNull(firstChar);
		String id = getParam("id", request);
		checkParamNull(id);

		Brand brand = new Brand();
		brand.setName(name);
		brand.setFirstChar(firstChar);
		brand.setId(Long.parseLong(id));

		ResultModel rm = new ResultModel();
		try {
			brandService.updateBrand(brand);
		} catch (Exception e) {
			rm.setCode(ResultModel.ERROR).setMsg(e.getMessage());
		}
		writeJsonToResp(response, rm);
	}

	private void deleteBrand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
			brandService.deleteBrand(ids);
		} catch (NumberFormatException e) {
			rm.setCode(ResultModel.ERROR).setMsg(e.getMessage());
		}
		writeJsonToResp(response, rm);
	}

	@Override
	protected void handleUri(String method, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch (method) {
		case "getBrandById.do":
			getBrandById(request, response);
			break;
		case "getAllBrand.do":
			getAllBrand(request, response);
			break;
		case "addBrand.do":
			addBrand(request, response);
			break;
		case "updateBrand.do":
			updateBrand(request, response);
			break;
		case "deleteBrand.do":
			deleteBrand(request, response);
			break;
		case "queryBrand.do":
			query(request, response);
			break;
		default:
			throw new RuntimeException("未找到404");
		}
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
		QueryResult<Brand> query = brandService.query(queryParam);
		ResultModel rm = new ResultModel();
		rm.setData(query);
		writeJsonToResp(response, rm);
	}
}
