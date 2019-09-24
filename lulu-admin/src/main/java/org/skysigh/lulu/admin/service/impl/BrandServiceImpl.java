package org.skysigh.lulu.admin.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.skysigh.lulu.admin.dao.BrandDao;
import org.skysigh.lulu.admin.po.Brand;
import org.skysigh.lulu.admin.service.BrandService;

public class BrandServiceImpl implements BrandService {

	private BrandDao brandDao;
	private SqlSession sqlSession;

	public BrandServiceImpl(SqlSession sqlSession) {
		brandDao = sqlSession.getMapper(BrandDao.class);
		this.sqlSession = sqlSession;
	}

	@Override
	public Brand getBrandById(long id) {
		return brandDao.getBrandById(id);
	}

	@Override
	public List<Brand> getAllBrand() {
		return brandDao.getAllBrand();
	}

	@Override
	public void addBrand(Brand brand) {
		brandDao.addBrand(brand);
		sqlSession.commit();
	}

	@Override
	public void updateBrand(Brand brand) {
		brandDao.updateBrand(brand);
		sqlSession.commit();
	}

	@Override
	public void deleteBrand(long[] ids) {
		brandDao.deleteBrand(ids);
		sqlSession.commit();
	}

}
