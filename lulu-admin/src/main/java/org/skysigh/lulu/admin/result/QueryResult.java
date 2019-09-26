package org.skysigh.lulu.admin.result;

import java.io.Serializable;
import java.util.List;

public class QueryResult<T> implements Serializable {
	private static final long serialVersionUID = 5138557585537416012L;
	private int allSize;
	private List<T> data;

	public int getAllSize() {
		return allSize;
	}

	public void setAllSize(int allSize) {
		this.allSize = allSize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
