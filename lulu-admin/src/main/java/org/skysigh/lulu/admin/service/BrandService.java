package org.skysigh.lulu.admin.service;

import java.util.List;

import org.skysigh.lulu.admin.po.Brand;

public interface BrandService extends SqlService {
	Brand getBrandById(long id);

	List<Brand> getAllBrand();

	void addBrand(Brand brand);

	void updateBrand(Brand brand);

	void deleteBrand(long[] ids);
}
