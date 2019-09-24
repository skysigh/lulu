package org.skysigh.lulu.admin.dao;

import java.util.List;

import org.skysigh.lulu.admin.po.TypeTemplate;

public interface TypeTemplateDao {
	TypeTemplate getById(long id);

	List<TypeTemplate> getAll();

	void add(TypeTemplate specification);

	void update(TypeTemplate specification);

	void delete(long[] ids);
}
