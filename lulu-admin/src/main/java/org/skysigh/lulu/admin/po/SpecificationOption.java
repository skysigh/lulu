package org.skysigh.lulu.admin.po;

import java.io.Serializable;

public class SpecificationOption implements Serializable {

	private static final long serialVersionUID = 3018173441935176327L;

	private long id;
	private String optionName;
	private long specId;
	private int orders;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public long getSpecId() {
		return specId;
	}

	public void setSpecId(long specId) {
		this.specId = specId;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "SpecificationOption [id=" + id + ", optionName=" + optionName + ", specId=" + specId + ", order="
				+ orders + "]";
	}

}
