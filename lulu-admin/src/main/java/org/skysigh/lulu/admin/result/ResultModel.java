package org.skysigh.lulu.admin.result;

import java.io.Serializable;

public class ResultModel implements Serializable {

	private static final long serialVersionUID = 5760726835397845212L;

	public static final int SUCCESS = 202;
	public static final int ERROR = 404;
	public static final int JUMP = 307;

	private int code = SUCCESS;
	private String msg;
	private Object data;
	private String url;

	public int getCode() {
		return code;
	}

	public ResultModel setCode(int code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ResultModel setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ResultModel setData(Object data) {
		this.data = data;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public ResultModel setUrl(String url) {
		this.url = url;
		return this;
	}

}
