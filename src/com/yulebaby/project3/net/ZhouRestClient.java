package com.yulebaby.project3.net;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.ParseException;
import org.json.JSONException;

import android.R;

import com.loopj.android.http.*;
import com.yulebaby.project3.comment.App;
import com.yulebaby.project3.comment.Utils;

public class ZhouRestClient {

	private static final String BASE_URL = "http://college.yulebaby.com/s/";

	private static AsyncHttpClient client = new AsyncHttpClient();

	// PersistentCookieStore myCookieStore = new
	// PersistentCookieStore(App.getInstance());
	// client.setCookieStore(myCookieStore);
	public static void get(final String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		// if(params==)

		if (Utils.isNetworkAvailable()) {
			System.out.println("getUrl:" + getAbsoluteUrl(url));
			client.get(getAbsoluteUrl(url), params, responseHandler);
		}

	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		if (Utils.isNetworkAvailable()) {
			System.out.println("postUrl:" + getAbsoluteUrl(url));
			client.post(getAbsoluteUrl(url), params, responseHandler);
		}
	}

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

	public void httpGet() {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("key", "value");
		RequestParams params = new RequestParams(paramMap);
	}
}
