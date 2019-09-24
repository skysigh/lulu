package org.skysigh.lulu.admin.po;

import java.io.Serializable;

public class Specification implements Serializable {
	private static final long serialVersionUID = 7149819400244916496L;
	private long id;
	private String specName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSpecName() {
		return specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	@Override
	public String toString() {
		return "Specification [id=" + id + ", specName=" + specName + "]";
	}

}
