package org.skysigh.lulu.admin.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.skysigh.lulu.admin.dao.BrandDao;
import org.skysigh.lulu.admin.po.Brand;

public class BrandDaoTest {
	private BrandDao brandDao;
	private SqlSession session;

	@Before
	public void setUp() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(in);
		session = factory.openSession();
		brandDao = session.getMapper(BrandDao.class);
	}
	@Test
	public void getBrandById_test() {
		Brand brand = brandDao.getBrandById(1);
		System.err.println(brand);
	}
	
	@Test
	public void addBrand_test() {
		Brand addBrand = new Brand();
		addBrand.setName("SkySigh");
		addBrand.setFirstChar("S");
		brandDao.addBrand(addBrand);
		Brand brand = brandDao.getBrandById(addBrand.getId());
		Assert.assertEquals(addBrand.getName(), brand.getName());
	}
	
	@Test
	public void updateBrand_test() {
//		Brand brand = brandDao.getBrandById(38);
//		brand.setName("CK");
//		brand.setFirstChar("C");
//		brandDao.updateBrand(brand);
//		Brand b = brandDao.getBrandById(brand.getId());
//		System.err.println(b);
//		Assert.assertEquals("CK", b.getName());
	}
	
	@Test
	public void deleteBrand_test() {
		int length = 50;
		long[] ids = new long[length];
		for(int i=0; i<length; i++) {
			ids[i] = i;
		}
		brandDao.deleteBrand(ids);
	}
	
	@Test
	public void getAllBrand_test() {
		List<Brand> allBrand = brandDao.getAllBrand();
		System.err.println(allBrand);
	}
}
