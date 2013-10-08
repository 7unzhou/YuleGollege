package com.yulebaby.project3.model;

import java.util.List;
import java.util.Map;

import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BaseGson<T> {

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

	private Map<String, List<T>> data;

	public Map<String, List<T>> getData() {
		return data;
	}

	public void setData(Map<String, List<T>> data) {
		this.data = data;
	}

}
