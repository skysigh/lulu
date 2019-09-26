package org.skysigh.lulu.admin.result;

import java.io.Serializable;

public class QueryParam implements Serializable {

	private static final long serialVersionUID = 1498647101229282606L;

	private String search;
	// ÐèÒªÅÅÐòµÄ×Ö¶Î
	private String sort;
	// ascÉýÐò desc½µÐò
	private String order;
	private int offset;
	private int limit;

	public QueryParam(String search, String sort, String order, int offset, int limit) {
		this.search = search;
		this.sort = sort;
		this.order = order;
		this.offset = offset;
		this.limit = limit;
	}

	public String getSearch() {
		return search;
	}

	public QueryParam setSearch(String search) {
		this.search = search;
		return this;
	}

	public String getSort() {
		return sort;
	}

	public QueryParam setSort(String sort) {
		this.sort = sort;
		return this;
	}

	public String getOrder() {
		return order;
	}

	public QueryParam setOrder(String order) {
		this.order = order;
		return this;
	}

	public int getOffset() {
		return offset;
	}

	public QueryParam setOffset(int offset) {
		this.offset = offset;
		return this;
	}

	public int getLimit() {
		return limit;
	}

	public QueryParam setLimit(int limit) {
		this.limit = limit;
		return this;
	}

	@Override
	public String toString() {
		return "QueryParam [search=" + search + ", sort=" + sort + ", order=" + order + ", offset=" + offset
				+ ", limit=" + limit + "]";
	}

}
