package org.skysigh.lulu.admin.po;

import java.io.Serializable;

public class Brand implements Serializable {
	private static final long serialVersionUID = 8334214958438114139L;

	private Long id;
	private String name;
	private String firstChar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstChar() {
		return firstChar;
	}

	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", first_char=" + firstChar + "]";
	}

}
