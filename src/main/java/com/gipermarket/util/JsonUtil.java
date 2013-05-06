package com.gipermarket.util;

import com.google.gson.Gson;

/**
 * Util for operations with JSON. Using Gson
 * 
 * @author sergiy.ievluchin
 * 
 */
public class JsonUtil {
	private Gson gson;

	public JsonUtil(Gson gson) {
		this.gson = gson;
	}

	public <T> T fromJson(String s, Class<T> c) {
		return gson.fromJson(s, c);
	}

	public String toJson(Object o) {
		return gson.toJson(o);
	}
}
