package org.skysigh.lulu.admin.dao;

import java.util.List;

import org.skysigh.lulu.admin.po.Brand;
import org.skysigh.lulu.admin.result.QueryParam;

public interface BrandDao {
	Brand getBrandById(long id);

	List<Brand> getAllBrand();

	long addBrand(Brand brand);

	void updateBrand(Brand brand);

	void deleteBrand(long[] ids);

	int count();

	List<Brand> query(QueryParam queryParam);
}
