package org.skysigh.lulu.admin.po;

import java.io.Serializable;

public class TypeTemplate implements Serializable {
	private static final long serialVersionUID = -717846177720401768L;

	private long id;
	private String name;
	private String specIds;
	private String brandIds;
	private String customAttributeItems;

	private String specIdArr;
	private String brandIIdArr;
	private String customArr;

	public String getSpecIdArr() {
		return specIdArr;
	}

	public void setSpecIdArr(String specIdArr) {
		this.specIdArr = specIdArr;
	}

	public String getBrandIIdArr() {
		return brandIIdArr;
	}

	public void setBrandIIdArr(String brandIIdArr) {
		this.brandIIdArr = brandIIdArr;
	}

	public String getCustomArr() {
		return customArr;
	}

	public void setCustomArr(String customArr) {
		this.customArr = customArr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSpecIds() {
		return specIds;
	}

	public void setSpecIds(String specIds) {
		this.specIds = specIds;
	}

	public String getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(String brandIds) {
		this.brandIds = brandIds;
	}

	public String getCustomAttributeItems() {
		return customAttributeItems;
	}

	public void setCustomAttributeItems(String customAttributeItems) {
		this.customAttributeItems = customAttributeItems;
	}

	@Override
	public String toString() {
		return "TypeTemplate [id=" + id + ", specIds=" + specIds + ", brandIds=" + brandIds + ", customAttributeItems="
				+ customAttributeItems + "]";
	}
}
