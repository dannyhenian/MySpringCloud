package com.danny.cloud.utils;

import com.google.gson.Gson;

public class JsonObject {

	private static Gson gson = new Gson();

	/** 状态 */
	Object state;
	/** 数据 */
	Object result;
	/** 状态描述 */
	Object message;

	public JsonObject() {}
	
	public JsonObject(Object state, Object message) {
		this.state = state;
		this.message = message;
	}

	public JsonObject(Object state, Object result, Object message) {
		this.state = state;
		this.result = result;
		this.message = message;
	}

	public JsonObject(Object state, Object result, Object message, boolean flag) {
		this.state = state;
		if (null != result && !(result instanceof String) && flag) {
			this.result = gson.toJson(result);
		} else {
			this.result = result;
		}
		this.message = message;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public Object getState() {
		return state;
	}

	public void setState(Object state) {
		this.state = state;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "JsonObject [state=" + state + ", result=" + result + ", message=" + message + "]";
	}

}
