package com.yulebaby.project3.model;

import java.util.Map;



public class BaseModelGson<T> {

	private int result;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// public Map getData() {
	// return data;
	// }
	//
	// public void setData(Map data) {
	// this.data = data;
	// }

	private String message;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	private T data;

	

}
